package com.capgemini.lmsspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lmsspring.dto.BookBean;
import com.capgemini.lmsspring.dto.BookIssueBean;
import com.capgemini.lmsspring.dto.BorrowedBooksBean;
import com.capgemini.lmsspring.dto.LibraryResponse;
import com.capgemini.lmsspring.dto.RequestDetailsBean;
import com.capgemini.lmsspring.dto.UsersBean;
import com.capgemini.lmsspring.service.LibrarianService;
import com.capgemini.lmsspring.service.LibrarianStudentService;
import com.capgemini.lmsspring.service.StudentService;

@RestController
public class LibraryRestContoller {

	@Autowired
	private LibrarianStudentService service1;
	
	@Autowired
	private LibrarianService service2;
	
	@Autowired
	private StudentService service3;

	@PostMapping(path = "/addUser", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse addUser(@RequestBody UsersBean user) {
		boolean isAdded = service1.register(user);

		LibraryResponse response = new LibraryResponse();
		if (isAdded) {
			response.setMessage("Record is inserted Successfully");
		} else {
			response.setError(true);
			response.setMessage("Record is not inserted");
		}
		return response;
	}
	
	
	@PostMapping(path = "/login", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse authentication(String email, String password) {
		UsersBean userLogin = service1.login(email, password);
		LibraryResponse response = new LibraryResponse();
		if (userLogin != null) {
			response.setMessage("Login succesfull");
		} else {
			response.setError(true);
			response.setMessage("Invalid credentials,Please try again");
		}
		return response;
	}

	@PostMapping(path = "/addBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse addBook(@RequestBody BookBean bean) {
		boolean isAdded = service2.addBook(bean);

		LibraryResponse response = new LibraryResponse();
		if (isAdded) {
			response.setMessage("Record is inserted Successfully");
		} else {
			response.setError(true);
			response.setMessage("Record is not inserted");
		}
		return response;	
	}

	@DeleteMapping(path="/deleteBook/{bId}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse removeBook(@PathVariable(name="bId") int bId ) {
		boolean isDeleted = service2.removeBook(bId);
		LibraryResponse response = new LibraryResponse();
		if (isDeleted) {
			response.setMessage("Record deleted");
		} else {
			response.setError(true);
			response.setMessage("Record not deleted");
		}
		return response;		
	}
	
	@PutMapping(path = "/updateBook",
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse updateBook(@RequestBody BookBean bean) {
		boolean isUpdated = service2.updateBook(bean);
		LibraryResponse response = new LibraryResponse();
		if (isUpdated) {
			response.setMessage("Record is updated");
		} else {
			response.setError(true);
			response.setMessage("Record not updated");
		}
		return response;		
	}
	
	@GetMapping(path="/getBooksById",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksById(int bId) {
		List<BookBean> recordList = service1.searchBookById(bId);
		LibraryResponse response = new LibraryResponse();
		if (recordList != null && !recordList.isEmpty()) {
			response.setBooksInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Id not present");
		}
		return response;		
	}
	
	@GetMapping(path="/getBooksByTitle",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksByTitle(String bookName) {
		List<BookBean> recordList = service1.searchBookByTitle(bookName);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBooksInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Title not present");
		}
		return response;
				
	}
	
	@GetMapping(path="/getBooksByAuthor",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksByAuthor(String author) {
		List<BookBean> recordList = service1.searchBookByAuthor(author);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBooksInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Author not present");
		}
		return response;
				
	}
	
	@GetMapping(path="/getBooksInfo",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksInfo() {
		List<BookBean> recordList = service1.getBooksInfo();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBooksInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Author not present");
		}
		return response;
	}
	
	@GetMapping(path="/getBorrowedBooks",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBorrowedBooks(int uId) {
		List<BorrowedBooksBean> recordList = service3.borrowedBook(uId);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBorrowedBooks(recordList);
		} else {
			response.setError(true);
			response.setMessage("The respective user hasn't borrowed any books");
		}
		return response;
	}
	
	@GetMapping(path="/showRequests",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showRequests() {
		List<RequestDetailsBean> recordList = service2.showRequests();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setRequests(recordList);
		} else {
			response.setError(true);
			response.setMessage("No requests has been received");
		}
		return response;
	}
	
	@GetMapping(path="/showIssuedBooks",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showIssuedBooks() {
		List<BookIssueBean> recordList = service2.showIssuedBooks();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setIssueInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("No Books has been issued");
		}
		return response;
	}
	
	@GetMapping(path="/showUsers",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showUsers() {
		List<UsersBean> recordList = service2.showUsers();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setUsersInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("No Users in the database");
		}
		return response;
	}
	
	@PutMapping(path = "/updatePassword",
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse updatePassord(int id, String password, String newPassword, String role) {
		boolean isUpdated = service1.updatePassword(id, password, newPassword, role);
		LibraryResponse response = new LibraryResponse();
		
		if(isUpdated) {
			response.setMessage("Password is updated");
		} else {
			response.setError(true);
			response.setMessage("Password is not updated");
		}
		return response;	
	}
	
	@PostMapping(path = "/requestBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse requestBook(int uId,int bId) {
		boolean isRequested = service3.request(uId, bId);

		LibraryResponse response = new LibraryResponse();
		if(isRequested) {
			response.setMessage("Request Placed");
		} else {
			response.setError(true);
			response.setMessage("Request not placed");
		}
		return response;
	}

	@PostMapping(path = "/issueBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse issueBook(int uId,int bId) {
		boolean isIssued = service2.issueBook(bId, uId);

		LibraryResponse response = new LibraryResponse();
		if(isIssued) {
			response.setMessage("Book Issued");
		} else {
			response.setError(true);
			response.setMessage("Book not Issued");
		}
		return response;
	}
	
	
	@PostMapping(path="/returnBook",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse returnBook(int uId,int bId,String status ) {
		boolean isReturned = service3.returnBook(bId, uId, status);
		LibraryResponse response = new LibraryResponse();
		if(isReturned) {
			response.setMessage("Book Returned");
		} else {
			response.setError(true);
			response.setMessage("Book not returned");
		}
		return response;		
	}
		
}
