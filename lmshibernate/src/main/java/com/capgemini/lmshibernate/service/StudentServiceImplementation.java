package com.capgemini.lmshibernate.service;

import java.util.List;

import com.capgemini.lmshibernate.dao.StudentDAO;
import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.factory.Factory;

public class StudentServiceImplementation implements StudentService {

	private StudentDAO dao = Factory.getStudentDAO();
	
	@Override
	public boolean request(int userId, int bookId) {
		return dao.request(userId, bookId);
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		return dao.returnBook(bookId, userId, status);
	}

	@Override
	public List<BookBorrowedInfo> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}

}
