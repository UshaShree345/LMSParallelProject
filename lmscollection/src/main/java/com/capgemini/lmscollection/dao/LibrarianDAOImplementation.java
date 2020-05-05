package com.capgemini.lmscollection.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.lmscollection.db.LibraryDB;
import com.capgemini.lmscollection.dto.BooksInfo;
import com.capgemini.lmscollection.dto.LibrarianInfo;
import com.capgemini.lmscollection.dto.RequestInfo;
import com.capgemini.lmscollection.dto.StudentInfo;
import com.capgemini.lmscollection.exception.CommonException;

public class LibrarianDAOImplementation implements LibrarianDAO {
	
	@Override
	public boolean registerLibrarian(LibrarianInfo librarian) {
		for (LibrarianInfo ai1 : LibraryDB.LIBRARIAN) {
		     if ((ai1.getEmail()) .equals(librarian.getEmail())) {
		         return false;
	          }
		}
		LibraryDB.LIBRARIAN.add(librarian);
		return true;
	} 
	
	@Override
	public LibrarianInfo authenticateLibrarian(String email, String password) {
		for (LibrarianInfo librarian : LibraryDB.LIBRARIAN) {
			if ((librarian.getEmail().equals(email)) && (librarian.getPassword().equals(password))) {
				return librarian;
			}
		}
		throw new CommonException("Invalid Credentials");
	}
	
	@Override
	public boolean addBook(BooksInfo book) {
		for (BooksInfo b : LibraryDB.BOOKS) {
			if (b.getBookId() == book.getBookId()) {
				return false;
			}
		}
		LibraryDB.BOOKS.add(book);
		return true;
	}
	
	@Override
	public boolean removeBook(int id) {
		boolean removeStatus=false;
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
		    BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (id == retrievedId) {
				removeStatus = true;
				LibraryDB.BOOKS.remove(i);
				break;
			}
		}
		return removeStatus;
    }
	
	@Override
	public ArrayList<BooksInfo> searchBookByTitle(String bookName) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBname = retrievedBook.getBookName();
			if (bookName.equals(retrievedBname)) {
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new CommonException("Book not found");
		} else {
		    return searchList;
		}
	}

	@Override
	public ArrayList<BooksInfo> searchBookByAuthor(String author) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (author.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
				throw new CommonException("Book not found");
		} else {
			return searchList;
		}		
	}
	
	@Override
	public ArrayList<BooksInfo> searchBookByCategory(String category) {
		ArrayList<BooksInfo> searchList = new ArrayList<BooksInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BooksInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBookType = retrievedBook.getCategory();
			if(category.equals(retrievedBookType))
			{
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new CommonException("Book not found");
		} else {
			return searchList;
		}
     }

	@Override
	public ArrayList<BooksInfo> getBooksInfo() {
		return LibraryDB.BOOKS;
	}

	@SuppressWarnings("unused")
	@Override
    public boolean updateBook(BooksInfo book) {
		
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			if (retrievedBook.getBookId() == book.getBookId()) {
				retrievedBook.setBookName(book.getBookName());
				retrievedBook.setAuthor(book.getAuthor());
				retrievedBook.setCategory(book.getCategory());
				return true;
			}
			else {
				throw new CommonException("Invalid Book");
			}
		} 
		throw new CommonException("Book not updated");
	}
	
	@Override
	public List<StudentInfo> showStudents() {
		List<StudentInfo> studentsList = new ArrayList<StudentInfo>();
		for (StudentInfo studentBean : LibraryDB.STUDENT) {
			studentBean.getId();
			studentBean.getName();
			studentBean.getEmail();
			studentBean.getBooksBorrowed();
			studentsList.add(studentBean);
		}
		return studentsList;
	}
	
	@Override
	public List<RequestInfo> showRequests() {
		List<RequestInfo> info = new ArrayList<RequestInfo>();
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			requestInfo.getBookInfo();
			requestInfo.getStudentInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}
	
	@Override
	public boolean bookIssue(StudentInfo student, BooksInfo book) {
		boolean isValid = false;
		RequestInfo requestInfo = new RequestInfo();
		int noOfBooksBorrowed = student.getBooksBorrowed();
		for (RequestInfo info : LibraryDB.REQUEST) {
			if (info.getStudentInfo().getId() == student.getId()) {
				if (info.getBookInfo().getBookId() == book.getBookId()) {
					requestInfo = info;
					isValid = true;
				}
			}
		}
		if (isValid) {
			for (BooksInfo info2 : LibraryDB.BOOKS) {
				if (info2.getBookId() == book.getBookId()) {
					book = info2;
				}
			}
			for (StudentInfo StudentInfo : LibraryDB.STUDENT) {
				if (StudentInfo.getId() == student.getId()) {
					student = StudentInfo;
					noOfBooksBorrowed = student.getBooksBorrowed();
				}
			}
			if (noOfBooksBorrowed < 3) {
				boolean isRemoved = LibraryDB.BOOKS.remove(book);
				if (isRemoved) {
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					student.setBooksBorrowed(noOfBooksBorrowed);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new CommonException("Book can't be borrowed");
				}
			} else {
				throw new CommonException("Student Exceeds maximum limit");
			}
		} else {
			throw new CommonException("Book data or Student data is incorrect");
		}
	}
	
	@Override
	public boolean isBookReceived(StudentInfo student, BooksInfo book) {
		boolean isValid = false;
		RequestInfo requestInfo1 = new RequestInfo();
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			if ((requestInfo.getBookInfo().getBookId()) == (book.getBookId())
					&& (requestInfo.getStudentInfo().getId()) == (student.getId()) 
					&& (requestInfo.isReturned()) == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {
			book.setAuthor(requestInfo1.getBookInfo().getAuthor());
			book.setBookName(requestInfo1.getBookInfo().getBookName());
			LibraryDB.BOOKS.add(book);
			LibraryDB.REQUEST.remove(requestInfo1);
			for (StudentInfo StudentInfo2 : LibraryDB.STUDENT) {
				if (StudentInfo2.getId() == student.getId()) {
					student = StudentInfo2;
				}
			}
			int noOfBooksBorrowed = student.getBooksBorrowed();
			noOfBooksBorrowed--;
			student.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;
	}
	}


