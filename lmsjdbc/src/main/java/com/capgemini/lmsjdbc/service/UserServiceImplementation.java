package com.capgemini.lmsjdbc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.lmsjdbc.dao.UserDAO;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BorrowedBooksInfo;
import com.capgemini.lmsjdbc.dto.RequestsInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.factory.Factory;

public class UserServiceImplementation implements UserService{
	
	private UserDAO dao=Factory.getUserDAO();
	
	@Override
	public boolean register(UserInfo user) {
		return dao.register(user);
	}

	@Override
	public UserInfo login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	@Override
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
	public boolean request(int sId, int bId) {
		return dao.request(sId, bId);
	}

	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public ArrayList<BookIssueInfo> bookHistoryDetails(int sId) {
		return dao.bookHistoryDetails(sId);
	}

	@Override
	public List<BorrowedBooksInfo> borrowedBook(int sId) {
		return dao.borrowedBook(sId);
	}

	@Override
	public ArrayList<BookInfo> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}

	@Override
	public boolean returnBook(int bId, int sId, String status) {
		return dao.returnBook(bId, sId, status);
	}

	@Override
	public ArrayList<RequestsInfo> showRequests() {
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

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}
}
