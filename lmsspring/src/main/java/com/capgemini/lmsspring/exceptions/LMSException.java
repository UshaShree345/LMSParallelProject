package com.capgemini.lmsspring.exceptions;

@SuppressWarnings("serial")
public class LMSException extends RuntimeException{
	
		public  LMSException(String msg) {
		super(msg);
	}
}
