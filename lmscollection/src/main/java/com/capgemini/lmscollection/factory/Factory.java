package com.capgemini.lmscollection.factory;

import com.capgemini.lmscollection.dao.LibrarianDAO;
import com.capgemini.lmscollection.dao.LibrarianDAOImplementation;
import com.capgemini.lmscollection.dao.StudentDAO;
import com.capgemini.lmscollection.dao.StudentDAOImplementation;
import com.capgemini.lmscollection.service.LibrarianService;
import com.capgemini.lmscollection.service.LibrarianServiceImplementation;
import com.capgemini.lmscollection.service.StudentService;
import com.capgemini.lmscollection.service.StudentServiceImplementation;

public class Factory {
	
	public static LibrarianDAO getLibrarianDAO() {
		return new LibrarianDAOImplementation();
	}
	
	public static LibrarianService getLibrarianService() {
		return new LibrarianServiceImplementation();
	}
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
}
