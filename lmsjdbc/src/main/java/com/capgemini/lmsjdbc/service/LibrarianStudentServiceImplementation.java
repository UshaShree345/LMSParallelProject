package com.capgemini.lmsjdbc.service;

import java.util.ArrayList;

import com.capgemini.lmsjdbc.dao.LibrarianStudentDAO;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.factory.Factory;

public class LibrarianStudentServiceImplementation implements LibrarianStudentService {

    private LibrarianStudentDAO dao=Factory.getLibrarianStudentDAO();
	
	@Override
	public boolean register(UserInfo user) {
		return dao.register(user);
	}

	@Override
	public UserInfo login(String email, String password) {
		return dao.login(email, password);
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
	public ArrayList<BookInfo> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}
     
	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}
	
	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

	

}
