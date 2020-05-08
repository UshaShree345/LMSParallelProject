package com.capgemini.lmsjdbc;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmsjdbc.dao.LibrarianDAO;
import com.capgemini.lmsjdbc.dao.LibrarianDAOImplementation;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BookRequestInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

//jdbc test cases
public class LibrarianDAOTest {

	private LibrarianDAO dao = new LibrarianDAOImplementation();

	@Test
	public void testAddBook() {
		BookInfo info = new BookInfo();
		info.setbId(101010);
		info.setBookName("javajava");
		info.setAuthor("jamesgosling");
		info.setCategory("javaprogramming");
		info.setPublisher("SunMicroSystem");
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
		info.setbId(123458);
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
		ArrayList<BookIssueInfo> info = dao.bookHistoryDetails(102102);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests() {
		ArrayList<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks() {
		ArrayList<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers() {
		ArrayList<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testAddBook1() {
		BookInfo info = new BookInfo();
		info.setbId(101010);
		info.setBookName("javajava");
		info.setAuthor("jamesgosling");
		info.setCategory("javaprogramming");
		info.setPublisher("SunMicroSystem");
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
		info.setbId(123458);
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
		ArrayList<BookIssueInfo> info = dao.bookHistoryDetails(123456);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests1() {
		ArrayList<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks1() {
		ArrayList<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers1() {
		ArrayList<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

}
