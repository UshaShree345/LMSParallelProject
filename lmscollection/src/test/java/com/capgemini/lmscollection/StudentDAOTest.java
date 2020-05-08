package com.capgemini.lmscollection;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmscollection.dao.StudentDAO;
import com.capgemini.lmscollection.dao.StudentDAOImplementation;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.StudentInfo;

public class StudentDAOTest {

	private StudentDAO dao=new StudentDAOImplementation();
	BooksInfo info=new BooksInfo();
	
	@Test
	public void testRegisterStudent() {
		StudentInfo info=new StudentInfo();
		info.setId(78965);
		info.setName("shiva");
		info.setMobileNo(987456321);
		info.setEmail("shiva@gmail.com");
		info.setPassword("Shiva@123");
		boolean status=dao.registerStudent(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		StudentInfo status = dao.authenticateStudent("shiva@gmail.com", "Shiva@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		ArrayList<BooksInfo> info = dao.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		ArrayList<BooksInfo> info = dao.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory() {
		ArrayList<BooksInfo> info = dao.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo() {
		ArrayList<BooksInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	/*
	 * @Test public void testRequest() { BookRequestInfo info = dao. }
	 */
	@Test
	public void testRegisterStudent1() {
		StudentInfo info=new StudentInfo();
		info.setId(7412);
		info.setName("Sony");
		info.setMobileNo(987456321);
		info.setEmail("sony2@gmail.com");
		info.setPassword("Sony@123");
		boolean status=dao.registerStudent(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		StudentInfo status = dao.authenticateStudent("sony2@gmail.com", "Sony@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BooksInfo> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BooksInfo> info = dao.searchBookByAuthor("kalyan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BooksInfo> info = dao.searchBookByCategory("maths");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BooksInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}

}
