package com.capgemini.lmscollection.db;

import java.util.ArrayList;

import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.LibrarianInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;

public class LibraryDB {

	public static final ArrayList<BooksInfo> BOOKS = new ArrayList<BooksInfo>();
	public static final ArrayList<StudentInfo> STUDENT = new ArrayList<StudentInfo>();
	public static final ArrayList<LibrarianInfo> LIBRARIAN = new ArrayList<LibrarianInfo>();
	public static final ArrayList<RequestInfo> REQUEST = new ArrayList<RequestInfo>();

	public static void addToDB() {

		LIBRARIAN.add(new LibrarianInfo(123123, "usha", "usha@gmail.com", 728598698, "Usha@1234"));
		LIBRARIAN.add(new LibrarianInfo(345345, "sony", "sony@gmail.com", 728598298, "Sony@7285"));

		// STUDENT.add(new StudentInfo(987987, "shiva", "shiva@gmail.com", "Shiv@12345",
		// "ece", 7788997788, 07-08-2020, 02-02-2020, 0);

		BOOKS.add(new BooksInfo(123123, "Ramayanam", "Valmiki", "Ethics", "Ancients"));
		BOOKS.add(new BooksInfo(456456, "jdbc", "Jon", "Api", "SunMicroSystems"));
		BOOKS.add(new BooksInfo(789789, "Java", "JamesGosling", "Programming", "SunMicroSystems"));
		

	}
}
