package com.capgemini.lmscollection;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.lmscollection.dto.BookRequestInfo;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.LibrarianInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.service.LibrarianService;
import com.capgemini.lmscollection.service.LibrarianServiceImplementation;

public class LibrarianServiceTest {

	private LibrarianService service=new LibrarianServiceImplementation();
	BooksInfo info=new BooksInfo();
	
	@Test
	public void testaddBook() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setBookAuthor("jamesgosling");
		info.setBookCategory("java");
		info.setBookPublisherName("sunmicrosystems");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin() {
		LibrarianInfo adn=new LibrarianInfo();
		adn.setId(12345);
		adn.setName("usha");
		adn.setMobileNo(994920634);
		adn.setEmail("usha@gmail.com");
		adn.setPassword("Usha@123");
		boolean status=service.registerLibrarian(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setBookAuthor("jamesgosling");
		info.setBookCategory("java");
		info.setBookPublisherName("sunmicrosystems");
		boolean status=service.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook() {
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setBookAuthor("rknarayan");
		info.setBookCategory("aptitude");
		info.setBookPublisherName("skpublications");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		LibrarianInfo status = service.authenticateLibrarian("usha@gmail.com", "Usha@123");
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
	@Test
	public void testShowStudents() {
		List<StudentInfo> info = service.showStudents();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests() {
		List<BookRequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testaddBook1() {
		info.setBookId(4567);
		info.setBookName("servlet");
		info.setBookAuthor("GiridharSir");
		info.setBookCategory("java");
		info.setBookPublisherName("servers");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin1() {
		LibrarianInfo adn=new LibrarianInfo();
		adn.setId(12345);
		adn.setName("chary");
		adn.setMobileNo(728598698);
		adn.setEmail("chary@gmail.com");
		adn.setPassword("Chary@123");
		boolean status=service.registerLibrarian(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook1() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setBookAuthor("jamesgosling");
		info.setBookCategory("java");
		info.setBookPublisherName("sunmicrosystems");
		boolean status=service.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook1() {
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setBookAuthor("rknarayan");
		info.setBookCategory("aptitude");
		info.setBookPublisherName("skpublications");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		LibrarianInfo status = service.authenticateLibrarian("usha@gmail.com", "Usha@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BooksInfo> info = service.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BooksInfo> info = service.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BooksInfo> info = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BooksInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowStudents1() {
		List<StudentInfo> info = service.showStudents();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests1() {
		List<BookRequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
	}

}
