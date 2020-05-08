package com.capgemini.lmsjdbc;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmsjdbc.dao.LibrarianStudentDAO;
import com.capgemini.lmsjdbc.dao.LibrarianStudentDAOImplementation;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

public class LibrarianStudentDAOTest {

	private LibrarianStudentDAO dao = new LibrarianStudentDAOImplementation();
	
	@Test
	public void testRegister() {
		UserInfo info = new UserInfo();
		info.setSId(951753);
		info.setFirstName("ShobhaRani");
		info.setLastName("Rudrojuu");
		info.setMobile(728598698);
		info.setPassword("Shobha@123");
		info.setRole("student");
		boolean status = dao.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UserInfo status = dao.login("usha345@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		ArrayList<BookInfo> info = dao.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = dao.updatePassword("usha@gmail.com", "Ush@1234", "Usha@1234", "librarian");
		Assertions.assertTrue(status);
	}
	@Test
	public void testRegister1() {
		UserInfo info = new UserInfo();
		info.setSId(951753);
		info.setFirstName("ShobhaRani");
		info.setLastName("Rudrojuu");
		info.setMobile(728598698);
		info.setPassword("Shobha@123");
		info.setRole("student");
		boolean status = dao.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UserInfo status = dao.login("usha@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		ArrayList<BookInfo> info = dao.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = dao.updatePassword("shiva@gmail.com", "shivakumar", "Shiv123@", "librarian");
		Assertions.assertTrue(status);
	}

	
	
}
