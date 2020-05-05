package com.capgemini.libraryManagementSystemJdbc.factory;

import com.capgemini.libraryManagementSystemJdbc.dao.StudentDAO;
import com.capgemini.libraryManagementSystemJdbc.dao.StudentDAOImplementation;
import com.capgemini.libraryManagementSystemJdbc.service.StudentService;
import com.capgemini.libraryManagementSystemJdbc.service.StudentServiceImplementation;

public class Factory {
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
}
