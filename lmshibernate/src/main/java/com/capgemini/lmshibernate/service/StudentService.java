package com.capgemini.lmshibernate.service;

import java.util.List;

import com.capgemini.lmshibernate.dto.BookBorrowedInfo;

public interface StudentService {

	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
	List<BookBorrowedInfo> borrowedBook(int userId);

}
