package com.capgemini.lmsjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;
import com.capgemini.lmsjdbc.service.StudentService;
import com.capgemini.lmsjdbc.service.StudentServiceImplementation;

public class StudentServiceTest {

private StudentService service = new StudentServiceImplementation();
	
	@Test
	public void testRequestValid() {
		boolean check = service.request(100002, 105);
		Assertions.assertTrue(check);		
	}
	
	@Test
	public void testRequestInvalid() {
		boolean check = service.request(100002, 109);
		Assertions.assertFalse(check);		
	}
	
	@Test
	public void testBookBorrowedInfoValid() {
		List<BookBorrowedInfo> info = service.borrowedBook(100002);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());
	}
	
	@Test
	public void testBookBorrowedInfoInvalid() {
		List<BookBorrowedInfo> info = service.borrowedBook(100002);
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(6, info.size());
	}
	
	@Test
	public void testReturnBookValid() {
		boolean check = service.returnBook(105, 100002, "yes");
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testReturnBookInvalid() {
		boolean check = service.returnBook(105, 100002, "yes");
		Assertions.assertFalse(check);
	}


}
