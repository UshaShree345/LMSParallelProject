package com.capgemini.lmsjdbc.dao;

import java.util.ArrayList;
import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BookRequestInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;

public interface LibrarianDAO {
    
	boolean addBook(BookInfo book);
	
	boolean removeBook(int bId);
	
	boolean updateBook(BookInfo book);
	
	boolean issueBook(int bId,int sId);
	
	ArrayList<BookIssueInfo> bookHistoryDetails(int sId);
	
	ArrayList<BookRequestInfo> showRequests();
	
	ArrayList<BookIssueInfo> showIssuedBooks();
	
	ArrayList<UserInfo> showUsers();
	
}
