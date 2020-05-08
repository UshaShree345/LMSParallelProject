package com.capgemini.lmsjdbc.service;

import java.util.List;

import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;

public interface StudentService {

	boolean request(int sId, int bId);
	boolean returnBook(int bId,int sId,String status);
	List<BookBorrowedInfo> borrowedBook(int sId);
	
}
