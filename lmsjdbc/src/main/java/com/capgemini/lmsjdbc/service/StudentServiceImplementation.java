package com.capgemini.lmsjdbc.service;

import java.util.List;

import com.capgemini.lmsjdbc.dao.StudentDAO;
import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;
import com.capgemini.lmsjdbc.factory.Factory;

public class StudentServiceImplementation implements StudentService {
	
	private StudentDAO dao=Factory.getStudentDAO();
	
	@Override
	public boolean request(int sId, int bId) {
		return dao.request(sId, bId);
	}

	@Override
	public List<BookBorrowedInfo> borrowedBook(int sId) {
		return dao.borrowedBook(sId);
	}
	
	@Override
	public boolean returnBook(int bId, int sId, String status) {
		return dao.returnBook(bId, sId, status);
	}

}
