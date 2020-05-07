package com.capgemini.lmsspring.dao;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.lmsspring.dto.BookBorrowedInfo;
import com.capgemini.lmsspring.dto.BookInfo;
import com.capgemini.lmsspring.dto.BookIssueInfo;
import com.capgemini.lmsspring.dto.RequestsInfo;
import com.capgemini.lmsspring.dto.UserInfo;
import com.capgemini.lmsspring.exceptions.LMSException;

public class UserDAOImplementation implements UserDAO {
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(UserInfo user) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			return true;
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
	public UserInfo login(String email, String password) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql="select u from UserInfo u where u.email=:email and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfo bean = query.getSingleResult();
			return bean;
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean addBook(BookInfo book) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
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
	public boolean removeBook(int bId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class,bId);
			manager.remove(record);
			transaction.commit();
			return true;
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
	public boolean updateBook(BookInfo book) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class, book.getbId());
			record.setBookName(book.getBookName());
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override 
	public boolean issueBook(int bId, int uId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bId=:bId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select r from RequestsInfo r where r.uId=:uId and r.bId=:bId";
				TypedQuery<RequestsInfo> query1 = manager.createQuery(jpql1,RequestsInfo.class);
				query1.setParameter("uId", uId);
				query1.setParameter("bId", bId);
				List<RequestsInfo> rs1 = query1.getResultList();
				if(!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueInfo issueBook = new BookIssueInfo();
					issueBook.setuId(uId);
					issueBook.setbId(bId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if(!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bId=:bId");
						bookName.setParameter("bId", bId);
						List book = bookName.getResultList();
						BookBorrowedInfo borrowedBooks = new BookBorrowedInfo();
						borrowedBooks.setuId(uId);
						borrowedBooks.setbId(bId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					}else {
						throw new LMSException("Book Not issued");
					}
				}else {
					throw new LMSException("The respective user have not placed any request");
				}
			}else {
				throw new LMSException("There is no book exist with bookId"+bId);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean request(int uId, int bId) {
		int count=0;
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bId=:bId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bId);
			List rs = query.getResultList();
			if(rs != null) {
				String jpql1 = "select b from BookBorrowedInfo b where b.uId=:uId and b.bId=:bId";
				TypedQuery<BookBorrowedInfo> query1 = (TypedQuery<BookBorrowedInfo>) manager.createQuery(jpql1,BookBorrowedInfo.class);
				query1.setParameter("uId", uId);
				query1.setParameter("bId", bId);
				List rs1 = query1.getResultList();
				if( rs1.isEmpty() || rs1==null ) {
					String jpql2 = "select b from BookIssueInfo b where b.uId=:uId";
					TypedQuery<BookIssueInfo> query2 = (TypedQuery<BookIssueInfo>) manager.createQuery(jpql2,BookIssueInfo.class);
					query2.setParameter("uId", uId);
					List<BookIssueInfo> rs2 = query2.getResultList();
					for(BookIssueInfo p : rs2) {
						noOfBooks = count++;
					}
					if(noOfBooks<3) {
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bId=:bookId");
						bookName.setParameter("bookId", bId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from UserInfo u where u.uId=:user_Id");
						email.setParameter("user_Id", uId);
						List userEmail = email.getResultList();
						transaction.begin();
						RequestsInfo request = new RequestsInfo();
						request.setuId(uId);
						request.setbId(bId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					}else {
						throw new LMSException("You have crossed the book limit");
					}
				}else {
					throw new LMSException("You have already borrowed the requested book");
				}
			}else {
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
	public List<BookBorrowedInfo> borrowedBook(int uId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookBorrowedInfo b where b.uId=:uId";
			TypedQuery<BookBorrowedInfo> query = manager.createQuery(jpql,BookBorrowedInfo.class);
			query.setParameter("uId", uId);
			List<BookBorrowedInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookById(int bId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bId=:bId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bId);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookName=:bookName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookName", bookName);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.author=:author";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("author", author);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookInfo b";
		TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
		List<BookInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bId=:bId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select b from BookIssueInfo b where b.bId=:bId and b.uId=:uId ";
				TypedQuery<BookIssueInfo> query1 = manager.createQuery(jpql1,BookIssueInfo.class);
				query1.setParameter("bId", bId);
				query1.setParameter("uId", uId);
				BookIssueInfo rs1 = query1.getSingleResult();
				if(rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if(daysBetween>7.0) {
						//transaction.begin();
						float fine = daysBetween*5;
						System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
						if(status=="yes") {
							transaction.begin();
							/*
							String jpql2 = "select b from BookIssueInfo b where b.bId=:bId and b.uId=:uId";
							Query query2 = manager.createQuery(jpql2);
							query2.setParameter("bId", bId);
							query2.setParameter("uId", uId);
							BookIssueInfo bib = (BookIssueInfo) query2.getSingleResult();
							 */
							//int bib_Id = rs1.getId();
							manager.remove(rs1);
							transaction.commit();


							transaction.begin();
							String jpql3 = "select b from BookBorrowedInfo b  where b.bId=:bId and b.uId=:uId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bId", bId);
							query3.setParameter("uId", uId);
							BookBorrowedInfo bbb = (BookBorrowedInfo) query3.getSingleResult();
							//int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestsInfo r where r.bId=:bId and r.uId=:uId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bId", bId);
							query4.setParameter("uId", uId);
							RequestsInfo rdb = (RequestsInfo) query4.getSingleResult();
							//int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						}else {
							throw new LMSException("The User has to pay fine for delaying book return");
						}
					}else {
						transaction.begin();
						/*
						String jpql2 = "select b from BookIssueInfo b where b.bId=:bId and b.uId=:uId";
						Query query2 = manager.createQuery(jpql2);
						query2.setParameter("bId", bId);
						query2.setParameter("uId", uId);
						BookIssueInfo bib = (BookIssueInfo) query2.getSingleResult();
						 */
						//int bib_Id = rs1.getId();
						manager.remove(rs1);
						transaction.commit();


						transaction.begin();
						String jpql3 = "select b from BookBorrowedInfo b  where b.bId=:bId and b.uId=:uId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bId", bId);
						query3.setParameter("uId", uId);
						BookBorrowedInfo bbb = (BookBorrowedInfo) query3.getSingleResult();
						//int bbb_Id = bbb.getId();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestsInfo r where r.bId=:bId and r.uId=:uId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bId", bId);
						query4.setParameter("uId", uId);
						RequestsInfo rdb = (RequestsInfo) query4.getSingleResult();
						//int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				}else {
					throw new LMSException("This respective user hasn't borrowed any book");
				}
			}else {
				throw new LMSException("book doesnt exist");
			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}


	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		int count=0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueInfo b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		for(BookIssueInfo p : recordList) {
			noOfBooks = count++;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		return list;
	}

	@Override
	public List<RequestsInfo> showRequests() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestsInfo r";
		TypedQuery<RequestsInfo> query = manager.createQuery(jpql,RequestsInfo.class);
		List<RequestsInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssueInfo> showIssuedBooks() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueInfo b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<UserInfo> showUsers() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from UserInfo u";
		TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
		List<UserInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UserInfo u where u.uId=:uId and u.role=:role and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfo rs = query.getSingleResult();
			if(rs != null) {
				UserInfo record = manager.find(UserInfo.class,id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;			
			}else {
				throw new LMSException("User doesnt exist");
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
}
