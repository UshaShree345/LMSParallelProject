package com.capgemini.lmsjdbc.dto;

import javax.annotation.Generated;

public class BookInfo {
	
	@Generated(value = { "bId" })
	private int bId;
	private String bookName;
	private String author;
	private String category;
	private String publisher;
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bId, bookName, author, category, publisher);
	}	
}