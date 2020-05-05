package com.capgemini.lmscollection.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.lmscollection.dao.LibrarianDAO;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.LibrarianInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.factory.Factory;

public class LibrarianServiceImplementation implements LibrarianService {
 
	private LibrarianDAO dao=Factory.getLibrarianDAO();

	@Override
	public boolean registerLibrarian(LibrarianInfo librarian) {
		return dao.registerLibrarian(librarian);
	}

	@Override
	public LibrarianInfo authenticateLibrarian(String email, String password) {
		return dao.authenticateLibrarian(email, password);
	}

	@Override
	public boolean addBook(BooksInfo book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int id) {
		return dao.removeBook(id);
	}

	@Override
	public boolean updateBook(BooksInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByTitle(String bookname) {
		return dao.searchBookByTitle(bookname);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public ArrayList<BooksInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public List<StudentInfo> showStudents() {
		return dao.showStudents();
	}

	@Override
	public List<RequestInfo> showRequests() {
		return dao.showRequests();
	}

	@Override
	public boolean bookIssue(StudentInfo student, BooksInfo book) {
		return dao.bookIssue(student, book);
	}

	@Override
	public boolean isBookReceived(StudentInfo student, BooksInfo book) {
		return dao.isBookReceived(student, book);
	}  	
}
