package com.capgemini.lmsspring.service;

import java.util.List;

import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.UsersBean;

public interface LibrarianStudentService {
	
	boolean register(UsersBean user);
	UsersBean login(String email,String password);	
	List<BookBean> searchBookById(int bId);
	List<BookBean> searchBookByTitle(String bookName);
	List<BookBean> searchBookByAuthor(String author);
	List<BookBean> getBooksInfo();
	boolean updatePassword(int id,String password,String newPassword,String role);

}
