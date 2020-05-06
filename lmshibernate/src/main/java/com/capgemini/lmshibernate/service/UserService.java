package com.capgemini.lmshibernate.service;

import java.util.List;

import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.dto.RequestsInfo;
import com.capgemini.lmshibernate.dto.UserInfo;

public interface UserService {
	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bId,int uId);
	boolean request(int uId, int bId);
	List<BookBorrowedInfo> borrowedBook(int uId);
	List<BookInfo> searchBookById(int bId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean returnBook(int bId,int uId,String status);
	List<Integer> bookHistoryDetails(int uId);
	List<RequestsInfo> showRequests();
	List<BookIssueInfo> showIssuedBooks();
	List<UserInfo> showUsers();
	boolean updatePassword(int id, String password, String newPassword, String role);
}
