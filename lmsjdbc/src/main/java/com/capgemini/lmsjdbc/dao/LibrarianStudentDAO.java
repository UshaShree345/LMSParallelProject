package com.capgemini.lmsjdbc.dao;

import java.util.ArrayList;

import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

public interface LibrarianStudentDAO {
    
	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	
	ArrayList<BookInfo> searchBookById(int bId);
	ArrayList<BookInfo> searchBookByTitle(String bookName);
	ArrayList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> getBooksInfo();
	boolean updatePassword(String email,String password,String newPassword,String role);

	
}
