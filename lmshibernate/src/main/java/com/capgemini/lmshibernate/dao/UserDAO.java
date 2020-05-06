package com.capgemini.lmshibernate.dao;

import java.util.List;

import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.dto.BookRequestInfo;
import com.capgemini.lmshibernate.dto.UserInfo;

public interface UserDAO {
	
	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bookId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bookId,int userId);
	boolean request(int userId, int bookId);
	List<BookBorrowedInfo> borrowedBook(int userId);
	List<BookInfo> searchBookById(int bookId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean returnBook(int bookId,int userId,String status);
	List<Integer> bookHistoryDetails(int userId);
	List<BookRequestInfo> showRequests();
	List<BookIssueInfo> showIssuedBooks();
	List<UserInfo> showUsers();
	boolean updatePassword(int id, String password, String newPassword, String role);

}
