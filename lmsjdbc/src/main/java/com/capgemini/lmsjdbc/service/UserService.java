package com.capgemini.lmsjdbc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;
import com.capgemini.lmsjdbc.dto.BookRequestInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

public interface UserService {
	
	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bId,int sId);
	boolean request(int sId, int bId);
	List<BookBorrowedInfo> borrowedBook(int sId);
	ArrayList<BookInfo> searchBookById(int bId);
	ArrayList<BookInfo> searchBookByTitle(String bookName);
	ArrayList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> getBooksInfo();
	boolean returnBook(int bId,int sId,String status);	
	ArrayList<BookIssueInfo> bookHistoryDetails(int sId);
	ArrayList<BookRequestInfo> showRequests();
	ArrayList<BookIssueInfo> showIssuedBooks();
	ArrayList<UserInfo> showUsers();
	boolean updatePassword(String email,String password,String newPassword,String role);
}

