package com.capgemini.lmscollection.service;

import java.util.ArrayList;

import com.capgemini.lmscollection.dao.StudentDAO;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.BookRequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.factory.Factory;

public class StudentServiceImplementation implements StudentService {
	
	private StudentDAO dao=Factory.getStudentDAO();

	@Override
	public boolean registerStudent(StudentInfo student) {
		return dao.registerStudent(student);
	}

	@Override
	public StudentInfo authenticateStudent(String email, String password) {
		return dao.authenticateStudent(email, password);
	}

	@Override
	public BookRequestInfo bookRequest(StudentInfo student, BooksInfo book) {
		return dao.bookRequest(student, book);
	}

	@Override
	public BookRequestInfo bookReturn(StudentInfo student, BooksInfo book) {
		return dao.bookReturn(student, book);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BooksInfo> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public ArrayList<BooksInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}
}
