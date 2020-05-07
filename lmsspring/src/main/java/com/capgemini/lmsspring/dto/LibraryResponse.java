package com.capgemini.lmsspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponse {
	
	private boolean error;
	private String message;
	
	private UserInfo userInfo;
	private BookInfo bookInfo;
	private BookIssueInfo bookIssueInfo;
	private BookBorrowedInfo bookBorrowedInfo;
	private RequestsInfo requestInfo;
	
	private List<UserInfo> userInfo2;
	private List<BookInfo> bookInfo2;
	private List<BookIssueInfo> bookIssueInfo2;
	private List<BookBorrowedInfo> bookBorrowedInfo2;
	private List<RequestsInfo> requestInfo2;
	
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
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public BookIssueInfo getBookIssueInfo() {
		return bookIssueInfo;
	}
	public void setBookIssueInfo(BookIssueInfo bookIssueInfo) {
		this.bookIssueInfo = bookIssueInfo;
	}
	public BookBorrowedInfo getBookBorrowedInfo() {
		return bookBorrowedInfo;
	}
	public void setBookBorrowedInfo(BookBorrowedInfo bookBorrowedInfo) {
		this.bookBorrowedInfo = bookBorrowedInfo;
	}
	public RequestsInfo getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(RequestsInfo requestInfo) {
		this.requestInfo = requestInfo;
	}
	public List<UserInfo> getUserInfo2() {
		return userInfo2;
	}
	public void setUserInfo2(List<UserInfo> userInfo2) {
		this.userInfo2 = userInfo2;
	}
	public List<BookInfo> getBookInfo2() {
		return bookInfo2;
	}
	public void setBookInfo2(List<BookInfo> bookInfo2) {
		this.bookInfo2 = bookInfo2;
	}
	public List<BookIssueInfo> getBookIssueInfo2() {
		return bookIssueInfo2;
	}
	public void setBookIssueInfo2(List<BookIssueInfo> bookIssueInfo2) {
		this.bookIssueInfo2 = bookIssueInfo2;
	}
	public List<BookBorrowedInfo> getBookBorrowedInfo2() {
		return bookBorrowedInfo2;
	}
	public void setBookBorrowedInfo2(List<BookBorrowedInfo> bookBorrowedInfo2) {
		this.bookBorrowedInfo2 = bookBorrowedInfo2;
	}
	public List<RequestsInfo> getRequestInfo2() {
		return requestInfo2;
	}
	public void setRequestInfo2(List<RequestsInfo> requestInfo2) {
		this.requestInfo2 = requestInfo2;
	}
	
}