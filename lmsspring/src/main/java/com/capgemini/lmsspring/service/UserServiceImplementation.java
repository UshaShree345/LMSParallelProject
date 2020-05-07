package com.capgemini.lmsspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspring.dao.UserDAO;
import com.capgemini.lmsspring.dto.BookBorrowedInfo;
import com.capgemini.lmsspring.dto.BookInfo;
import com.capgemini.lmsspring.dto.BookIssueInfo;
import com.capgemini.lmsspring.dto.RequestsInfo;
import com.capgemini.lmsspring.dto.UserInfo;


public class UserServiceImplementation implements UserService {

	@Autowired
	private UserDAO dao;

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
	public boolean issueBook(int bId, int uId) {
		return dao.issueBook(bId, uId);
	}

	@Override
	public boolean request(int uId, int bId) {
		return dao.request(uId, bId);
	}

	@Override
	public List<BookBorrowedInfo> borrowedBook(int uId) {
		return dao.borrowedBook(uId);
	}

	@Override
	public List<BookInfo> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		return dao.returnBook(bId, uId, status);
	}

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<RequestsInfo> showRequests() {
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

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		return dao.updatePassword(id, password, newPassword, role);
	}
	
}
