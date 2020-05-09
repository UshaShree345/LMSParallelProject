package com.capgemini.lmsjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmsjdbc.dao.StudentDAO;
import com.capgemini.lmsjdbc.dao.StudentDAOImplementation;
import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;

public class StudentDAOTest {
 
private StudentDAO dao = new StudentDAOImplementation();
	
	@Test
	public void testRequestValid() {
		boolean check = dao.request(100002, 105);
		Assertions.assertTrue(check);		
	}
	
	@Test
	public void testRequestInvalid() {
		boolean check = dao.request(100002, 109);
		Assertions.assertFalse(check);		
	}
	
	@Test
	public void testBookBorrowedInfoValid() {
		List<BookBorrowedInfo> info = dao.borrowedBook(100002);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());
	}
	
	@Test
	public void testBookBorrowedInfoInvalid() {
		List<BookBorrowedInfo> info = dao.borrowedBook(100002);
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(6, info.size());
	}
	
	@Test
	public void testReturnBookValid() {
		boolean check = dao.returnBook(105, 100002, "yes");
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testReturnBookInvalid() {
		boolean check = dao.returnBook(105, 100002, "yes");
		Assertions.assertFalse(check);
	}

}
