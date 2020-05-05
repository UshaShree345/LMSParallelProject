package com.capgemini.lmsjdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.lmsjdbc.dao.StudentDAO;
import com.capgemini.lmsjdbc.dto.BookBean;
import com.capgemini.lmsjdbc.dto.BookIssueDetails;
import com.capgemini.lmsjdbc.dto.BorrowedBooks;
import com.capgemini.lmsjdbc.dto.RequestDetails;
import com.capgemini.lmsjdbc.dto.StudentsBean;
import com.capgemini.lmsjdbc.factory.Factory;

public class StudentServiceImplementation implements StudentService{
	
	private StudentDAO dao=Factory.getStudentDAO();
	
	@Override
	public boolean register(StudentsBean student) {
		return dao.register(student);
	}

	@Override
	public StudentsBean login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean addBook(BookBean book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(BookBean book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId,int sId) {
		return dao.issueBook(bId,sId);
	}

	@Override
	public boolean request(int sId, int bId) {
		return dao.request(sId, bId);
	}

	@Override
	public LinkedList<BookBean> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<BookBean> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int sId) {
		return dao.bookHistoryDetails(sId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int sId) {
		return dao.borrowedBook(sId);
	}

	@Override
	public LinkedList<BookBean> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}

	@Override
	public boolean returnBook(int bId, int sId, String status) {
		return dao.returnBook(bId, sId, status);
	}

	@Override
	public LinkedList<RequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public LinkedList<BookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public LinkedList<StudentsBean> showStudents() {
		return dao.showStudents();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}
}
