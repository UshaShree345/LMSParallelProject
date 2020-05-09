package com.capgemini.lmsjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmsjdbc.dao.LibrarianStudentDAO;
import com.capgemini.lmsjdbc.dao.LibrarianStudentDAOImplementation;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

public class LibrarianStudentDAOTest {

private LibrarianStudentDAO dao = new LibrarianStudentDAOImplementation();
	
	@Test
	public void testRegisterValid() {
		UserInfo bean = new UserInfo();
		bean.setSId(100005);
		bean.setFirstName("Sridhar");
		bean.setLastName("Bendhi");
		bean.setEmail("sridhar@gmail.com");
		bean.setPassword("Sridhar@123");
		bean.setRole("student");
		boolean check = dao.register(bean);
		Assertions.assertTrue(check);		
	}
	
	@Test
	public void testRegisterInvalid() {
		UserInfo bean = new UserInfo();
		bean.setSId(100005);
		bean.setFirstName("Sridhar");
		bean.setLastName("Bendhi");
		bean.setEmail("sridhar@gmail.com");
		bean.setPassword("Sridhar@123");
		bean.setRole("student");
		boolean check = dao.register(bean);
		Assertions.assertFalse(check);
	}

	@Test
	public void testLoginValid() {
		UserInfo info = dao.login("ammu@gmail.com", "ammU@123");
		Assertions.assertNotNull(info);
	}
	
	@Test
	public void testLoginInvalid() {
		UserInfo info = dao.login("ammu@gmail.com", "ammU123");
		Assertions.assertNull(info);
	}
	
	@Test
	public void testSearchBookByIdValid() {
		List<BookInfo> info = dao.searchBookById(101);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());
		
	}
	
	@Test
	public void testSearchBookByIdInvalid() {
		List<BookInfo> info = dao.searchBookById(109);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleValid() {
		List<BookInfo> info = dao.searchBookByTitle("MM");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleInvalid() {
		List<BookInfo> info = dao.searchBookByTitle("Maths");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorValid() {
		List<BookInfo> info = dao.searchBookByAuthor("sharma");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorInvalid() {
		List<BookInfo> info = dao.searchBookByAuthor("Jain");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());	
	}
	
	@Test
	public void testBooksInfoValid() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(5, info.size());
	}
	
	@Test
	public void testBooksInfoInvalid() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(6, info.size());
	}
	
	@Test
	public void testUpdatePasswordValid() {
		boolean check = dao.updatePassword("ammu@gmail.com", "ammU@123", "Admin@123", "admin");
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testUpdatePasswordInvalid() {
		boolean check = dao.updatePassword("amm@gmail.com", "ammU@123", "Admin@123", "student");
		Assertions.assertFalse(check);
	}
	
	
	
}
