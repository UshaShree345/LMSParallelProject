package com.capgemini.lmshibernate.service;

import java.util.List;

import com.capgemini.lmshibernate.dao.LibrarianDAO;
import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookRequestInfo;
import com.capgemini.lmshibernate.dto.UserInfo;
import com.capgemini.lmshibernate.factory.Factory;

public class LibrarianServiceImplementation implements LibrarianService {

	private LibrarianDAO dao = Factory.getLibrarianDAO();
	
	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bookId) {
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		return dao.issueBook(bookId, userId);
	}
	@Override
	public List<Integer> bookHistoryDetails(int userId) {
		return dao.bookHistoryDetails(userId);
	}

	@Override
	public List<BookRequestInfo> showRequests() {
		return dao.showRequests();
	}

	@Override
	public List<BookIssueInfo> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public List<UserInfo> showUsers() {
		return dao.showUsers();
	}
	

}
