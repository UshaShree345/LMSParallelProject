package com.capgemini.lmshibernate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.UserInfo;
import com.capgemini.lmshibernate.service.LibrarianStudentService;
import com.capgemini.lmshibernate.service.LibrarianStudentServiceImplementation;

public class LibrarianStudentServiceTest {

private LibrarianStudentService service = new LibrarianStudentServiceImplementation();
	
	@Test
	public void testRegister() {
		UserInfo info = new UserInfo();
		info.setUserId(951753);
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
		UserInfo status = service.login("usha345@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		List<BookInfo> info = service.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		List<BookInfo> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<BookInfo> info = service.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = service.updatePassword(7412, "Ush@1234", "Usha@1234", "librarian");
		Assertions.assertTrue(status);
	}
	@Test
	public void testRegister1() {
		UserInfo info = new UserInfo();
		info.setUserId(951753);
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
		UserInfo status = service.login("usha@gmail.com", "Ush@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		List<BookInfo> info = service.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		List<BookInfo> info = service.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<BookInfo> info = service.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = service.updatePassword(8521, "shivakumar", "Shiv123@", "librarian");
		Assertions.assertTrue(status);
	}

}
