package com.capgemini.lmsjdbc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BorrowedBooks implements Serializable {
	
	private int sId;
	private int bId;
	private String email;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
