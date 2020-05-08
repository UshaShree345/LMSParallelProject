package com.capgemini.lmsspring.service;

import java.util.List;

import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.BookIssueBean;
import com.capgemini.lmsspring.dto.RequestDetailsBean;
import com.capgemini.lmsspring.dto.UsersBean;

public interface LibrarianService {
	
	boolean addBook(BookBean book);
	boolean removeBook(int bId);
	boolean updateBook(BookBean book);
	boolean issueBook(int bId,int uId);
	List<Integer> bookHistoryDetails(int uId);
	List<RequestDetailsBean> showRequests();
	List<BookIssueBean> showIssuedBooks();
	List<UsersBean> showUsers();

}
