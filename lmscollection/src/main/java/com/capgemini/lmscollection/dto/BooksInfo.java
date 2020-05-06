package com.capgemini.lmscollection.dto;

import java.io.Serializable;
import java.lang.Math;

@SuppressWarnings("serial")
public class BooksInfo implements Serializable {

	private int bookId = (int)Math.random();
	private String bookName;
	private String bookAuthor;
	private String bookCategory;
	private String bookPublisherName;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookPublisherName() {
		return bookPublisherName;
	}

	public void setBookPublisherName(String bookPublisherName) {
		this.bookPublisherName = bookPublisherName;
	}

    public BooksInfo() {
		
	}
	
	public BooksInfo(int bookId, String bookName, String bookAuthor, String bookCategory, String bookPublisherName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookPublisherName = bookPublisherName;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bookId, bookName, bookAuthor, bookCategory, bookPublisherName);
	}

}