package com.capgemini.lmsspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.UsersBean;
import com.capgemini.lmsspring.service.LibrarianStudentService;

public class LibrarianStudentServiceTest {

	@Autowired
    private LibrarianStudentService service;
	
	@Test
	public void testRegister() {
		UsersBean info = new UsersBean();
		info.setuId(951753);
		info.setFirstName("ShobhaRani");
		info.setLastName("Rudrojuu");
		info.setMobile(728598698);
		info.setPassword("Shobha@123");
		info.setRole("student");
		boolean status = service.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UsersBean status = service.login("usha345@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		List<BookBean> info = service.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		List<BookBean> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<BookBean> info = service.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		List<BookBean> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = service.updatePassword(7412, "Ush@1234", "Usha@1234", "librarian");
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
		boolean status = service.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UsersBean status = service.login("usha@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		List<BookBean> info = service.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		List<BookBean> info = service.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<BookBean> info = service.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		List<BookBean> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = service.updatePassword(8521, "shivakumar", "Shiv123@", "librarian");
		Assertions.assertTrue(status);
	}

}
