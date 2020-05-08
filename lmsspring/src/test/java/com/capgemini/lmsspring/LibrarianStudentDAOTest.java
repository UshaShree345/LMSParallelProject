package com.capgemini.lmsspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspring.dao.LibrarianStudentDAO;
import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.UsersBean;

public class LibrarianStudentDAOTest {

	@Autowired
	private LibrarianStudentDAO dao;

	@Test
	public void testRegister() {
		UsersBean info = new UsersBean();
		info.setuId(951753);
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
		UsersBean status = dao.login("usha345@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testSearchBookById() {
		List<BookBean> info = dao.searchBookById(123123);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchBookByTitle() {
		List<BookBean> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchBookByAuthor() {
		List<BookBean> info = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testGetBooksInfo() {
		List<BookBean> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testUpdatePassword() {
		boolean status = dao.updatePassword(1234, "Ush@1234", "Usha@1234", "librarian");
		Assertions.assertTrue(status);
	}

	@Test
	public void testRegister1() {
		UsersBean info = new UsersBean();
		info.setuId(951753);
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
		UsersBean status = dao.login("usha@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testSearchBookById1() {
		List<BookBean> info = dao.searchBookById(852852);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchBookByTitle1() {
		List<BookBean> info = dao.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchBookByAuthor1() {
		List<BookBean> info = dao.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testGetBooksInfo1() {
		List<BookBean> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testUpdatePassword1() {
		boolean status = dao.updatePassword(8522, "shivakumar", "Shiv123@", "librarian");
		Assertions.assertTrue(status);
	}

}
