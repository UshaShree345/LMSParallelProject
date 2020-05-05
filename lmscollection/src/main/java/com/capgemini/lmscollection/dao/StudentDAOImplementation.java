package com.capgemini.lmscollection.dao;

import java.util.ArrayList;

import com.capgemini.lmscollection.db.LibraryDB;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.exception.CommonException;

public class StudentDAOImplementation implements StudentDAO {

	@Override
	public boolean registerStudent(StudentInfo student) {
		for (StudentInfo u : LibraryDB.STUDENT) {
			if ((u.getEmail()).equals(student.getEmail())) {
				return false;
			}
		}
		LibraryDB.STUDENT.add(student);
		return true;
	}
	
	@Override
	public StudentInfo authenticateStudent(String email, String password) {
		for (StudentInfo student : LibraryDB.STUDENT) {
			if ((student.getEmail().equals(email)) && (student.getPassword().equals(password))) {
				return student;
			}
		}
		throw new CommonException("Invalid Credentials");
	}
	
	@Override
	public ArrayList<BooksInfo> searchBookByTitle(String bookName) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBname = retrievedBook.getBookName();
			if (bookName.equals(retrievedBname)) {
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new CommonException ("Book is not found");
		} else {
			return searchList;
		}
	}

	@Override
	public ArrayList<BooksInfo> searchBookByAuthor(String author) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (author.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new CommonException ("Book is not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BooksInfo> searchBookByCategory(String category) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBookType=retrievedBook.getCategory();
			if (category.equals(retrievedBookType)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new CommonException("Book not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BooksInfo> getBooksInfo() {
			return LibraryDB.BOOKS;
	}

	@Override
	public RequestInfo bookRequest(StudentInfo student, BooksInfo book) {
		boolean flag = false, 
		isRequestExists = false;
		RequestInfo requestInfo = new RequestInfo();
		StudentInfo StudentInfo2 = new StudentInfo();
		BooksInfo bookInfo2 = new BooksInfo();
		for (RequestInfo requestInfo2 : LibraryDB.REQUEST) {
			if (book.getBookId() == requestInfo2.getBookInfo().getBookId()) {
				isRequestExists = true;
			}
		}
		if (!isRequestExists) {
			for (StudentInfo studentBean : LibraryDB.STUDENT) {
				if (student.getId() == studentBean.getId()) {
					for (BooksInfo book1 : LibraryDB.BOOKS) {
						if (book1.getBookId() == book1.getBookId()) {
							StudentInfo2 = studentBean;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setStudentInfo(StudentInfo2);;
				LibraryDB.REQUEST.add(requestInfo);
				return requestInfo;
			}
		}
		throw new CommonException("Invalid request or you cannot request that book");
	}
	
	@Override
	public RequestInfo bookReturn(StudentInfo student, BooksInfo book) {
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			if (requestInfo.getBookInfo().getBookId() == book.getBookId() &&
					requestInfo.getStudentInfo().getId() == student.getId() &&
					requestInfo.isIssued() == true) {
				System.out.println("Returning Issued book only");
				requestInfo.setReturned(true);
				return requestInfo;
			}
		}
		throw new  CommonException("Invalid return ");
	}
}
	


