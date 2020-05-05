package com.capgemini.lmscollection.db;

import java.util.ArrayList;

import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.LibrarianInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;

public class LibraryDB {
	
	public static final ArrayList<BooksInfo> BOOKS=new ArrayList<BooksInfo>();
	public static final ArrayList<StudentInfo> STUDENT=new ArrayList<StudentInfo>();
	public static final ArrayList<LibrarianInfo> LIBRARIAN=new ArrayList<LibrarianInfo>();
	public static final ArrayList<RequestInfo> REQUEST = new ArrayList<RequestInfo>();
}
