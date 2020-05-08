package com.capgemini.lmshibernate.factory;

import com.capgemini.lmshibernate.dao.LibrarianDAO;
import com.capgemini.lmshibernate.dao.LibrarianDAOImplementation;
import com.capgemini.lmshibernate.dao.LibrarianStudentDAO;
import com.capgemini.lmshibernate.dao.LibrarianStudentDAOImplementation;
import com.capgemini.lmshibernate.dao.StudentDAO;
import com.capgemini.lmshibernate.dao.StudentDAOImplementation;
import com.capgemini.lmshibernate.service.LibrarianService;
import com.capgemini.lmshibernate.service.LibrarianServiceImplementation;
import com.capgemini.lmshibernate.service.LibrarianStudentService;
import com.capgemini.lmshibernate.service.LibrarianStudentServiceImplementation;
import com.capgemini.lmshibernate.service.StudentService;
import com.capgemini.lmshibernate.service.StudentServiceImplementation;

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
