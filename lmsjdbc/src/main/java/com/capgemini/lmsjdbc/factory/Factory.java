package com.capgemini.lmsjdbc.factory;

import com.capgemini.lmsjdbc.dao.LibrarianDAO;
import com.capgemini.lmsjdbc.dao.LibrarianDAOImplementation;
import com.capgemini.lmsjdbc.dao.LibrarianStudentDAO;
import com.capgemini.lmsjdbc.dao.LibrarianStudentDAOImplementation;
import com.capgemini.lmsjdbc.dao.StudentDAO;
import com.capgemini.lmsjdbc.dao.StudentDAOImplementation;
import com.capgemini.lmsjdbc.service.LibrarianService;
import com.capgemini.lmsjdbc.service.LibrarianServiceImplementation;
import com.capgemini.lmsjdbc.service.LibrarianStudentService;
import com.capgemini.lmsjdbc.service.LibrarianStudentServiceImplementation;
import com.capgemini.lmsjdbc.service.StudentService;
import com.capgemini.lmsjdbc.service.StudentServiceImplementation;

public class Factory {
	
	public static LibrarianDAO getLibrarianDAO() {
		return new LibrarianDAOImplementation();
	}
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	public static LibrarianStudentDAO getLibrarianStudentDAO() {
		return new LibrarianStudentDAOImplementation();
	}
	public static LibrarianService getLibrarianService() {
		return new LibrarianServiceImplementation();
	}
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
	public static LibrarianStudentService getLibrarianStudentService() {
		return new LibrarianStudentServiceImplementation();
	}
	
}
