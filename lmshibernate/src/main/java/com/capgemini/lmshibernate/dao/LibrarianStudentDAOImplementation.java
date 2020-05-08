package com.capgemini.lmshibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.UserInfo;
import com.capgemini.lmshibernate.exception.LMSException;

public class LibrarianStudentDAOImplementation implements LibrarianStudentDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(UserInfo UserInfo) {

		boolean isRegistered = false;
		try {

			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(UserInfo);
			transaction.commit();
			isRegistered = true;
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			// return false;
		}
		return isRegistered;
	}

	@Override
	public UserInfo login(String email, String password) {

		try {

			manager = factory.createEntityManager();
			String jpql = "select u from UserInfo u where u.email=:email and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql, UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfo bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {

		try {

			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookName=:bookName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bookName", bookName);
			List<BookInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String authorName) {

		try {

			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.authorName=:authorName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("authorName", authorName);
			List<BookInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookInfo> searchBookById(int bookId) {

		try {

			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bookId", bookId);
			List<BookInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		manager = factory.createEntityManager();
		String jpql = "select b from BookInfo b";
		TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
		List<BookInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {

		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UserInfo u where u.id=:id and u.role=:role and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql, UserInfo.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfo rs = query.getSingleResult();
			if (rs != null) {
				UserInfo record = manager.find(UserInfo.class, id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;
			} else {
				throw new LMSException("UserInfo doesnt exist");
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
