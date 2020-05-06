package com.capgemini.lmsjdbc.factory;

import com.capgemini.lmsjdbc.dao.UserDAO;
import com.capgemini.lmsjdbc.dao.UserDAOImplementation;
import com.capgemini.lmsjdbc.service.UserService;
import com.capgemini.lmsjdbc.service.UserServiceImplementation;

public class Factory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOImplementation();
	}
	
	public static UserService getUserService() {
		return new UserServiceImplementation();
	}
}
