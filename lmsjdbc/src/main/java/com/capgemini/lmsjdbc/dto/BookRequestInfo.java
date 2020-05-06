package com.capgemini.lmsjdbc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookRequestInfo implements Serializable {
	
	private int sId;
	private String fullName;
	private int bId;
	private String bookName;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
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
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %s", sId, fullName, bId, bookName); 
	}
	
	
}