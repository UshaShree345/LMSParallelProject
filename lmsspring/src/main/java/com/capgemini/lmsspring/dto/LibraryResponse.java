package com.capgemini.lmsspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponse {

	private boolean error;
	private String message;

	private BookBean bookInfo;
	private List<BookBean> booksInfo;
	
	private UsersBean userInfo;
	private List<UsersBean> usersInfo;

	private BookIssueBean bookIssueInfo;
	private List<BookIssueBean> issueInfo;
	
	private BorrowedBooksBean borrowedBooksInfo;
	private List<BorrowedBooksBean> borrowedBooks;
	
	private RequestDetailsBean requestInfo;
	private List<RequestDetailsBean> requests;
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BookBean getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookBean bookInfo) {
		this.bookInfo = bookInfo;
	}
	public List<BookBean> getBooksInfo() {
		return booksInfo;
	}
	public void setBooksInfo(List<BookBean> booksInfo) {
		this.booksInfo = booksInfo;
	}
	public UsersBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UsersBean userInfo) {
		this.userInfo = userInfo;
	}
	public List<UsersBean> getUsersInfo() {
		return usersInfo;
	}
	public void setUsersInfo(List<UsersBean> usersInfo) {
		this.usersInfo = usersInfo;
	}
	public BookIssueBean getBookIssueInfo() {
		return bookIssueInfo;
	}
	public void setBookIssueInfo(BookIssueBean bookIssueInfo) {
		this.bookIssueInfo = bookIssueInfo;
	}
	public List<BookIssueBean> getIssueInfo() {
		return issueInfo;
	}
	public void setIssueInfo(List<BookIssueBean> issueInfo) {
		this.issueInfo = issueInfo;
	}
	public BorrowedBooksBean getBorrowedBooksInfo() {
		return borrowedBooksInfo;
	}
	public void setBorrowedBooksInfo(BorrowedBooksBean borrowedBooksInfo) {
		this.borrowedBooksInfo = borrowedBooksInfo;
	}
	public List<BorrowedBooksBean> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<BorrowedBooksBean> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public RequestDetailsBean getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(RequestDetailsBean requestInfo) {
		this.requestInfo = requestInfo;
	}
	public List<RequestDetailsBean> getRequests() {
		return requests;
	}
	public void setRequests(List<RequestDetailsBean> requests) {
		this.requests = requests;
	}
	
	
}
