package com.capgemini.lmshibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmshibernate.dao.LibrarianDAO;
import com.capgemini.lmshibernate.dao.LibrarianDAOImplementation;
import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookRequestInfo;
import com.capgemini.lmshibernate.dto.UserInfo;

//jdbc test cases
public class LibrarianDAOTest {

	private LibrarianDAO dao = new LibrarianDAOImplementation();

	@Test
	public void testAddBook() {
		BookInfo info = new BookInfo();
		info.setBookId(101010);
		info.setBookName("javajava");
		info.setBookAuthor("jamesgosling");
		info.setBookCategory("javaprogramming");
		info.setBookPublisher("SunMicroSystem");
		boolean status = dao.addBook(info);
		Assertions.assertTrue(status);
	}

	@Test
	public void testRemoveBook() {
		boolean status = dao.removeBook(1234);
		Assertions.assertTrue(status);
	}

	@Test
	public void testUpdateBook() {
		BookInfo info = new BookInfo();
		info.setBookId(123458);
		info.setBookName("jdbc");
		boolean status = dao.updateBook(info);
		Assertions.assertTrue(status);
	}

	@Test
	public void testIssueBook() {
		boolean status = dao.issueBook(123123, 102102);
		Assertions.assertTrue(status);
	}

	@Test
	public void testBookHistroyDetails() {
		List<Integer> info = dao.bookHistoryDetails(102102);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests() {
		List<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks() {
		List<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers() {
		List<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testAddBook1() {
		BookInfo info = new BookInfo();
		info.setBookId(101010);
		info.setBookName("javajava");
		info.setBookAuthor("jamesgosling");
		info.setBookCategory("javaprogramming");
		info.setBookPublisher("SunMicroSystem");
		boolean status = dao.addBook(info);
		Assertions.assertFalse(status);
	}

	@Test
	public void testRemoveBook1() {
		boolean status = dao.removeBook(1234);
		Assertions.assertFalse(status);
	}

	@Test
	public void testUpdateBook1() {
		BookInfo info = new BookInfo();
		info.setBookId(123458);
		info.setBookName("jdbc");
		boolean status = dao.updateBook(info);
		Assertions.assertFalse(status);
	}

	@Test
	public void testIssueBook1() {
		boolean status = dao.issueBook(123123, 102102);
		Assertions.assertFalse(status);
	}

	@Test
	public void testBookHistroyDetails1() {
		List<Integer> info = dao.bookHistoryDetails(123456);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests1() {
		List<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks1() {
		List<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers1() {
		List<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

}
