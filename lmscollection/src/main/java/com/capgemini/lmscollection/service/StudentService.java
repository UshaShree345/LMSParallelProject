package com.capgemini.lmscollection.service;

import java.util.ArrayList;

import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;

public interface StudentService {
	
	boolean registerStudent(StudentInfo student);
	StudentInfo authenticateStudent(String email,String password);
	public RequestInfo bookRequest(StudentInfo student, BooksInfo book);
	public RequestInfo bookReturn(StudentInfo student, BooksInfo book);
	ArrayList<BooksInfo> searchBookByTitle(String bookName);
	ArrayList<BooksInfo> searchBookByAuthor(String author);
	ArrayList<BooksInfo> searchBookByCategory(String category);
	ArrayList<BooksInfo> getBooksInfo();
}
