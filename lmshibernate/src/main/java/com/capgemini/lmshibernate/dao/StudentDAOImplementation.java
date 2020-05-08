package com.capgemini.lmshibernate.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookRequestInfo;
import com.capgemini.lmshibernate.exception.LMSException;

public class StudentDAOImplementation implements StudentDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean request(int bookId, int id) {

		int count = 0;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bId", bookId);
			List rs = query.getResultList();
			if (rs != null) {
				String jpql1 = "select b from BookBorrowedInfo b where b.id=:id and b.bookId=:bookId";
				TypedQuery<BookBorrowedInfo> query1 = (TypedQuery<BookBorrowedInfo>) manager.createQuery(jpql1,
						BookBorrowedInfo.class);
				//
				query1.setParameter("Id", id);
				query1.setParameter("bId", bookId);
				List rs1 = query1.getResultList();
				if (rs1.isEmpty() || rs1 == null) {
					String jpql2 = "select b from BookIssueInfo b where b.id=:id";
					TypedQuery<BookIssueInfo> query2 = (TypedQuery<BookIssueInfo>) manager.createQuery(jpql2, BookIssueInfo.class);
					query2.setParameter("id", id);
					List<BookIssueInfo> rs2 = query2.getResultList();
					for (BookIssueInfo p : rs2) {
						noOfBooks = count++;
					}
					if (noOfBooks < 3) {
						Query bookName = manager
								.createQuery("select b.bookName from BookInfo b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from User u where u.id=:id");
						email.setParameter("id", id);
						List userEmail = email.getResultList();
						transaction.begin();
						BookRequestInfo request = new BookRequestInfo();
						//
						request.setId(id);
						request.setBookId(bookId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					} else {
						throw new LMSException("You have crossed the book limit");
					}
				} else {
					throw new LMSException("You have already borrowed the requested book");
				}
			} else {
				throw new LMSException("The book with requested id is not present");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}



	@Override
	public boolean returnBook(int bookId, int id, String status) {

		try {

			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bookId", bookId);
			BookInfo rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select b from BookIssueInfo b where b.bookId=:bookId and b.id=:id ";
				TypedQuery<BookIssueInfo> query1 = manager.createQuery(jpql1, BookIssueInfo.class);
				query1.setParameter("bookId", bookId);
				//
				query1.setParameter("id", id);
				BookIssueInfo rs1 = query1.getSingleResult();
				if (rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000 * 60 * 60 * 24));
					if (daysBetween > 7.0) {
						// transaction.begin();
						float fine = daysBetween * 5;
						System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
						if (status == "yes") {
							transaction.begin();
							manager.remove(rs1);
							transaction.commit();
							transaction.begin();
							String jpql3 = "select b from BookBorrowedInfo b  where b.bookId=:bookId and b.id=:id";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bookId", bookId);
							query3.setParameter("id", id);
							BookBorrowedInfo bbb = (BookBorrowedInfo) query3.getSingleResult();
							// int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from BookRequestInfo r where r.bookId=:bookId and r.id=:id";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bookId", bookId);
							query4.setParameter("id", id);
							BookRequestInfo rdb = (BookRequestInfo) query4.getSingleResult();
							// int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						} else {
							throw new LMSException("The User has to pay fine for delaying book return");
						}
					} else {
						transaction.begin();

						manager.remove(rs1);
						transaction.commit();

						transaction.begin();
						String jpql3 = "select b from BookBorrowedInfo b  where b.bookId=:bId and b.id=:uid";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bookId", bookId);
						//
						query3.setParameter("id", id);
						BookBorrowedInfo bbb = (BookBorrowedInfo) query3.getSingleResult();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from BookRequestInfo r where r.bookId=:bookId and r.uId=:uId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bookId", bookId);
						//
						query4.setParameter("id", id);
						BookRequestInfo rdb = (BookRequestInfo) query4.getSingleResult();
						// int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				} else {
					throw new LMSException("This respective user hasn't borrowed any book");
				}
			} else {
				throw new LMSException("book doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookBorrowedInfo> borrowedBook(int id) {

		try {
			manager = factory.createEntityManager();
			String jpql = "select b from BookBorrowedInfo b where b.uId=:uId";
			TypedQuery<BookBorrowedInfo> query = manager.createQuery(jpql, BookBorrowedInfo.class);
			//
			query.setParameter("uId", id);
			List<BookBorrowedInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}
}
