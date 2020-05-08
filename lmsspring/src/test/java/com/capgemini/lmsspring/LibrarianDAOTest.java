package com.capgemini.lmsspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspring.dao.LibrarianDAO;
import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.BookIssueBean;
import com.capgemini.lmsspring.dto.RequestDetailsBean;
import com.capgemini.lmsspring.dto.UsersBean;

//jdbc test cases
public class LibrarianDAOTest {

	@Autowired
	private LibrarianDAO dao;

	@Test
	public void testAddBook() {
		BookBean info = new BookBean();
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
		BookBean info = new BookBean();
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
		List<Integer> info = dao.bookHistoryDetails(102102);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests() {
		List<RequestDetailsBean> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks() {
		List<BookIssueBean> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers() {
		List<UsersBean> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testAddBook1() {
		BookBean info = new BookBean();
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
		BookBean info = new BookBean();
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
		List<Integer> info = dao.bookHistoryDetails(123456);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests1() {
		List<RequestDetailsBean> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowIssuedBooks1() {
		List<BookIssueBean> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers1() {
		List<UsersBean> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

}
