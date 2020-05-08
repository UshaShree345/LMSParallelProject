package com.capgemini.lmsspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lmsspring.dao.StudentDAO;
import com.capgemini.lmsspring.dto.BorrowedBooksBean;
@Service
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	private StudentDAO dao;

	@Override
	public List<BorrowedBooksBean> borrowedBook(int uId) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(uId);
	}

	@Override
	public boolean request(int uId, int bId) {
		// TODO Auto-generated method stub
		return dao.request(uId, bId);
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bId, uId, status);
	}

}
