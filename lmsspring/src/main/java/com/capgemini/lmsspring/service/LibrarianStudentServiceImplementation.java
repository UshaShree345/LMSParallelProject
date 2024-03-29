package com.capgemini.lmsspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lmsspring.dao.LibrarianStudentDAO;
import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.UsersBean;

@Service
public class LibrarianStudentServiceImplementation implements LibrarianStudentService {

	@Autowired
	private LibrarianStudentDAO dao;

	@Override
	public boolean register(UsersBean user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	@Override
	public UsersBean login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

	@Override
	public List<BookBean> searchBookById(int bId) {
		// TODO Auto-generated method stub
		return dao.searchBookById(bId);
	}

	@Override
	public List<BookBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookBean> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookBean> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(id, password, newPassword, role);
	}

	
}
