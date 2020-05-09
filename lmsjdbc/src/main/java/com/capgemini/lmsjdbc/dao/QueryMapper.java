package com.capgemini.lmsjdbc.dao;

public interface QueryMapper {

	String registerQuery = "insert into users values(?,?,?,?,?,?,?)";
	
	String loginQuery = "select * from users where email=? and password=?";
	
	String addBookQuery = "insert into bookbean values(?,?,?,?,?)";
	
	String removeBookQuery = "delete from bookbean where bid=?";
	
	String updateBookQuery = "update bookbean set bookname=? where bid=?";
	
	String issueBookQuery1 = "select * from request_details where sid=? and bid=? and email=(select email from users where sid=?)";
	
	String issueBookQuery2 = "insert into book_issue_details values(?,?,?,?)";
	
	String issueBookQuery3 = "Insert into borrowed_books values(?,?,(select email from users where sid=?))";
	
	String requestQuery = "select * from bookbean where bid=?";
	
	String requestQuery1 = "select count(*) as sid from borrowed_books where sid=? and bid=? and email=(select email from users where sid=?)";
	
	String requestQuery3 = "insert into request_details values(?,(select concat(firstName,'_',lastName) from users where sid=?)"
			+ ",?,(select bookname from bookbean where bid=?),(select email from users where sid=?))";
	
	String requestQuery2 = "select count(*) as sid from book_issue_details where sid=?";
	
	String titleQuery = "select * from bookbean where bookname=?";
	
	String authorQuery = "select * from bookbean where author=?";
	
	String getAllBooksQuery = "select * from bookbean";
	
	String bookHistroyQuery = "select count(*) as sid from book_issue_details where sid=?";
	
	String borrowQuery = "select * from borrowed_books where sid=?";
	
	String searchIdQuery = "select * from bookbean where bid=?";
	
	String returnBookQuery1 = "select * from book_issue_details where bid=? and sid=?";
	
	String returnBookQuery2 = "delete from book_issue_details where bid=? and sid=?";
	
	String returnBookQuery3 = "delete from borrowed_books where bid=? and sid=?";
	
	String returnBookQuery4 = "delete from request_details where bid=? and sid=?";
	
	String showRequestsQuery = "select * from request_details";
	
	String showIssuedBooksQuery = "select * from book_issue_details";
	
	String showUsersQuery = "select * from users";
	
	String updatePasswordQuery1 = "select * from users where email=? and role=?";
	
	String updatePasswordQuery2 = "update users set password=? where email=? and password=?";
		
}

