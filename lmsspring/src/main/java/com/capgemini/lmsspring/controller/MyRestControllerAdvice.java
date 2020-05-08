package com.capgemini.lmsspring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.lmsspring.dto.LibraryResponse;
import com.capgemini.lmsspring.exceptions.LMSException;

@RestControllerAdvice
public class MyRestControllerAdvice {

	@ExceptionHandler
	public LibraryResponse myExceptionHandler(LMSException lmsException) {
		
		LibraryResponse response = new LibraryResponse();
		response.setError(true);
		response.setMessage(lmsException.getMessage());
		return response;
		
	}
}
