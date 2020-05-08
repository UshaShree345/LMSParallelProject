package com.capgemini.lmsjdbc.service;

import java.util.ArrayList;

import com.capgemini.lmsjdbc.dao.LibrarianDAO;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BookRequestInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.factory.Factory;

public class LibrarianServiceImplementation implements LibrarianService {

	private LibrarianDAO dao=Factory.getLibrarianDAO();
	
	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId,int sId) {
		return dao.issueBook(bId,sId);
	}

	@Override
	public ArrayList<BookIssueInfo> bookHistoryDetails(int sId) {
		return dao.bookHistoryDetails(sId);
	}
	
	@Override
	public ArrayList<BookRequestInfo> showRequests() {
		return dao.showRequests();
	}

	@Override
	public ArrayList<BookIssueInfo> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public ArrayList<UserInfo> showUsers() {
		return dao.showUsers();
	}

}
