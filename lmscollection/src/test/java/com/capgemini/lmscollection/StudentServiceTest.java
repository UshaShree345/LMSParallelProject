package com.capgemini.lmscollection;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.service.StudentService;
import com.capgemini.lmscollection.service.StudentServiceImplementation;

public class StudentServiceTest {

	private StudentService service=new StudentServiceImplementation();
	BooksInfo info=new BooksInfo();
	
	@Test
	public void testRegisterStudent() {
		StudentInfo info=new StudentInfo();
		info.setId(78965);
		info.setName("shiva");
		info.setMobileNo(987456321);
		info.setEmail("shiva@gmail.com");
		info.setPassword("Shiva@123");
		boolean status=service.registerStudent(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		StudentInfo status = service.authenticateStudent("shiva@gmail.com", "Shiva@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		ArrayList<BooksInfo> info = service.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		ArrayList<BooksInfo> info = service.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory() {
		ArrayList<BooksInfo> info = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo() {
		ArrayList<BooksInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	/*
	 * @Test public void testRequest() { BookRequestInfo info = service. }
	 */
	@Test
	public void testRegisterStudent1() {
		StudentInfo info=new StudentInfo();
		info.setId(7412);
		info.setName("Sony");
		info.setMobileNo(987456321);
		info.setEmail("sony2@gmail.com");
		info.setPassword("Sony@123");
		boolean status=service.registerStudent(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		StudentInfo status = service.authenticateStudent("sony2@gmail.com", "Sony@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BooksInfo> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BooksInfo> info = service.searchBookByAuthor("kalyan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BooksInfo> info = service.searchBookByCategory("maths");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BooksInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}

}
