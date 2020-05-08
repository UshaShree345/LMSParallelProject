package com.capgemini.lmsspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspring.dao.StudentDAO;
import com.capgemini.lmsspring.dto.BorrowedBooksBean;

public class StudentDAOTest {
    
	@Autowired
	private StudentDAO dao;
	
	@Test
	public void testRequest() {
		boolean status = dao.request(852852, 852852);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook() {
		boolean status = dao.returnBook(1234, 123123, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook() {
		List<BorrowedBooksBean> info = dao.borrowedBook(159753);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testRequest1() {
		boolean status = dao.request(852852, 987654);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook1() {
		boolean status = dao.returnBook(123123, 123456, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooksBean> info = dao.borrowedBook(741852);
		Assertions.assertNotNull(info);
	}
}
