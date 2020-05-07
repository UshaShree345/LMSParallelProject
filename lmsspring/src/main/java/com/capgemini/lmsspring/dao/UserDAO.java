package com.capgemini.lmsspring.dao;

import java.util.List;

import com.capgemini.lmsspring.dto.BookBorrowedInfo;
import com.capgemini.lmsspring.dto.BookInfo;
import com.capgemini.lmsspring.dto.BookIssueInfo;
import com.capgemini.lmsspring.dto.RequestsInfo;
import com.capgemini.lmsspring.dto.UserInfo;

public interface UserDAO {
	
	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bId,int uId);
	boolean request(int uId, int bId);
	List<RequestsInfo> showRequests();
	List<BookIssueInfo> showIssuedBooks();
	List<UserInfo> showUsers();
	List<BookBorrowedInfo> borrowedBook(int uId);
	List<BookInfo> searchBookById(int bId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean updatePassword(int id, String password, String newPassword, String role);
	boolean returnBook(int bId,int uId,String status);
	List<Integer> bookHistoryDetails(int uId);
	

}
