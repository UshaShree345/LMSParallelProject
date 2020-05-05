package com.capgemini.lmscollection.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BooksInfo implements Serializable {

	private int bookId;
	private String bookName;
	private String author;
	private String category;
	private String publishername;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bookId, bookName, author, category, publishername);
	}

}