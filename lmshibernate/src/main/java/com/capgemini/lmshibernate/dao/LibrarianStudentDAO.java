package com.capgemini.lmshibernate.dao;

import java.util.List;

import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.UserInfo;

public interface LibrarianStudentDAO {

	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	List<BookInfo> searchBookById(int bookId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean updatePassword(int id, String password, String newPassword, String role);

	
}
