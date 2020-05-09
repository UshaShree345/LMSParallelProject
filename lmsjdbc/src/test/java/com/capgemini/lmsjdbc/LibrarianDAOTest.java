package com.capgemini.lmsjdbc;

import java.util.List;

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
	public void testAddBookValid() {
		BookInfo bean = new BookInfo();
		bean.setbId(105);
		bean.setBookName("Java");
		bean.setAuthor("James");
		bean.setCategory("Programing");
		bean.setPublisher("Arihent");
		boolean check = dao.addBook(bean);
		Assertions.assertTrue(check);
	}
	@Test
	public void testAddBookInvalid() {
		BookInfo bean = new BookInfo();
		bean.setbId(105);
		bean.setBookName("Java");
		bean.setAuthor("James");
		bean.setCategory("Programing");
		bean.setPublisher("Arihent");
		boolean check = dao.addBook(bean);
		Assertions.assertFalse(check);	
	} 
	
	@Test
	public void testRemoveBookValid() {
		boolean check = dao.removeBook(105);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testRemoveBookInvalid() {
		boolean check = dao.removeBook(107);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testUpdateBookValid() {
		BookInfo book = new BookInfo();
		book.setbId(104);
		book.setBookName("Maths");
		boolean check = dao.updateBook(book);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testUpdateBookInvalid() {
		BookInfo book = new BookInfo();
		book.setbId(109);
		book.setBookName("Maths");
		boolean check = dao.updateBook(book);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testIssueBookValid() {
		boolean check = dao.issueBook(105, 100002);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testIssueBookInvalid() {
		boolean check = dao.issueBook(109, 100002);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testBookHistoryDetailsValid() {
		List<BookIssueInfo> info = dao.bookHistoryDetails(100002);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testBookHistoryDetailsInvalid() {
		List<BookIssueInfo> info = dao.bookHistoryDetails(100003);
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(0, info.size());		
	}
	
	@Test
	public void testShowRequestsValid() {
		List<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testShowRequestsInvalid() {
		List<BookRequestInfo> info = dao.showRequests();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}
	
	@Test
	public void testShowIssuedBooksValid() {
		List<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testShowIssuedBooksInvalid() {
		List<BookIssueInfo> info = dao.showIssuedBooks();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}
	
	@Test
	public void testShowUsersValid() {
		List<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(4, info.size());		
	}
	
	@Test
	public void testShowUsersInvalid() {
		List<UserInfo> info = dao.showUsers();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}

}
