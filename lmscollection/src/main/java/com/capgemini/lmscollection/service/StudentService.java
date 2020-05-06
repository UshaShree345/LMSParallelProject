package com.capgemini.lmscollection.service;

import java.util.ArrayList;

import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.BookRequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;

public interface StudentService {
	
	boolean registerStudent(StudentInfo student);
	StudentInfo authenticateStudent(String email,String password);
	public BookRequestInfo bookRequest(StudentInfo student, BooksInfo book);
	public BookRequestInfo bookReturn(StudentInfo student, BooksInfo book);
	ArrayList<BooksInfo> searchBookByTitle(String bookName);
	ArrayList<BooksInfo> searchBookByAuthor(String author);
	ArrayList<BooksInfo> searchBookByCategory(String category);
	ArrayList<BooksInfo> getBooksInfo();
}
