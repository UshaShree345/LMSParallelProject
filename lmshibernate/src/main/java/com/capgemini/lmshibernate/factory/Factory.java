package com.capgemini.lmshibernate.factory;

import com.capgemini.lmshibernate.dao.UserDAO;
import com.capgemini.lmshibernate.dao.UserDAOImplementation;
import com.capgemini.lmshibernate.service.UserService;
import com.capgemini.lmshibernate.service.UserServiceImplementation;

public class Factory {
	
	public static UserDAO getUserDao() {
		return new UserDAOImplementation();
	}
	public static UserService getUserService() {
		return new UserServiceImplementation();
	}
}
