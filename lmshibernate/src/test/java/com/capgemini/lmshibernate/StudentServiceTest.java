package com.capgemini.lmshibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.service.StudentService;
import com.capgemini.lmshibernate.service.StudentServiceImplementation;

public class StudentServiceTest {

private StudentService service = new StudentServiceImplementation();
	
	@Test
	public void testRequest() {
		boolean status = service.request(852852, 852852);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook() {
		boolean status = service.returnBook(1234, 123123, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook() {
		List<BookBorrowedInfo> info = service.borrowedBook(159753);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testRequest1() {
		boolean status = service.request(852852, 987654);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook1() {
		boolean status = service.returnBook(123123, 123456, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook1() {
		List<BookBorrowedInfo> info = service.borrowedBook(741852);
		Assertions.assertNotNull(info);
	}

}
