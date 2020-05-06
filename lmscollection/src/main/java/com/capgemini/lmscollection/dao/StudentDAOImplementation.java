package com.capgemini.lmscollection.dao;

import java.util.ArrayList;

import com.capgemini.lmscollection.db.LibraryDB;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.BookRequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.exception.LMSException;

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
		throw new LMSException("Invalid Credentials");
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
			throw new LMSException ("Book is not found");
		} else {
			return searchList;
		}
	}

	@Override
	public ArrayList<BooksInfo> searchBookByAuthor(String author) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBAuthor = retrievedBook.getBookAuthor();
			if (author.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException ("Book is not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BooksInfo> searchBookByCategory(String category) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBookType=retrievedBook.getBookCategory();
			if (category.equals(retrievedBookType)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BooksInfo> getBooksInfo() {
			return LibraryDB.BOOKS;
	}

	@Override
	public BookRequestInfo bookRequest(StudentInfo student, BooksInfo book) {
		boolean flag = false, 
		isRequestExists = false;
		BookRequestInfo BookRequestInfo = new BookRequestInfo();
		StudentInfo StudentInfo2 = new StudentInfo();
		BooksInfo bookInfo2 = new BooksInfo();
		for (BookRequestInfo BookRequestInfo2 : LibraryDB.REQUEST) {
			if (book.getBookId() == BookRequestInfo2.getBookInfo().getBookId()) {
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
				BookRequestInfo.setBookInfo(bookInfo2);
				BookRequestInfo.setStudentInfo(StudentInfo2);;
				LibraryDB.REQUEST.add(BookRequestInfo);
				return BookRequestInfo;
			}
		}
		throw new LMSException("Invalid request or you cannot request that book");
	}
	
	@Override
	public BookRequestInfo bookReturn(StudentInfo student, BooksInfo book) {
		for (BookRequestInfo BookRequestInfo : LibraryDB.REQUEST) {
			if (BookRequestInfo.getBookInfo().getBookId() == book.getBookId() &&
					BookRequestInfo.getStudentInfo().getId() == student.getId() &&
					BookRequestInfo.isIssued() == true) {
				System.out.println("Returning Issued book only");
				BookRequestInfo.setReturned(true);
				return BookRequestInfo;
			}
		}
		throw new  LMSException("Invalid return ");
	}
}
	


