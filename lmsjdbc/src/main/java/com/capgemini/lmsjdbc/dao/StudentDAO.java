package com.capgemini.lmsjdbc.dao;

import java.util.List;

import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;

public interface StudentDAO {
    
	boolean request(int sId, int bId);
	boolean returnBook(int bId,int sId,String status);
	List<BookBorrowedInfo> borrowedBook(int sId);
	
}
