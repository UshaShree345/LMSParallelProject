package com.capgemini.lmsjdbc.factory;

import com.capgemini.lmsjdbc.dao.StudentDAO;
import com.capgemini.lmsjdbc.dao.StudentDAOImplementation;
import com.capgemini.lmsjdbc.service.StudentService;
import com.capgemini.lmsjdbc.service.StudentServiceImplementation;

public class Factory {
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
}
