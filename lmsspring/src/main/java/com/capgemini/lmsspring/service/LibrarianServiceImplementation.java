package com.capgemini.lmsspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lmsspring.dao.LibrarianDAO;
import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.BookIssueBean;
import com.capgemini.lmsspring.dto.RequestDetailsBean;
import com.capgemini.lmsspring.dto.UsersBean;

@Service
public class LibrarianServiceImplementation implements LibrarianService {
	
	@Autowired
	private LibrarianDAO dao;

	@Override
	public boolean addBook(BookBean book) {
		// TODO Auto-generated method stub
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(BookBean book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId, int uId) {
		// TODO Auto-generated method stub
		return dao.issueBook(bId, uId);
	}

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestDetailsBean> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public List<BookIssueBean> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

	@Override
	public List<UsersBean> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

}
