package com.capgemini.lmsjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BookRequestInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.exception.LMSException;
import com.capgemini.lmsjdbc.utility.JdbcUtility;

public class LibrarianDAOImplementation implements LibrarianDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	@Override
	public boolean addBook(BookInfo book) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.addBookQuery);) {

			statement.setInt(1, book.getbId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getAuthor());
			statement.setString(4, book.getCategory());
			statement.setString(5, book.getPublisher());
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBook(int bId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.removeBookQuery);) {

			statement.setInt(1, bId);
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBook(BookInfo book) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.updateBookQuery);) {

			statement.setString(1, book.getBookName());
			statement.setInt(2, book.getbId());
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean issueBook(int bId, int sId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.issueBookQuery1);) {

			statement.setInt(1, sId);
			statement.setInt(2, bId);
			statement.setInt(3, sId);
			try(PreparedStatement pst = connection.prepareStatement(QueryMapper.requestQuery);) {
				pst.setInt(1, bId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.issueBookQuery2);) {
					pstmt1.setInt(1, bId);
					pstmt1.setInt(2, sId);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
					int count = pstmt1.executeUpdate();
					if (count != 0) {
						try (PreparedStatement pstmt2 = connection.prepareStatement(QueryMapper.issueBookQuery3);) {
							pstmt2.setInt(1, sId);
							pstmt2.setInt(2, bId);
							pstmt2.setInt(3, sId);
							int isBorrowed = pstmt2.executeUpdate();
							if (isBorrowed != 0) {
								return true;
							} else {
								return false;
							}
						}
					} else {
						throw new LMSException("Book Not issued");
					}
				}
			} else {
				throw new LMSException("The respective user have not placed any request");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<BookIssueInfo> bookHistoryDetails(int sId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.bookHistroyQuery);) {
			statement.setInt(1, sId);
			rs = statement.executeQuery();
			ArrayList<BookIssueInfo> beans = new ArrayList<BookIssueInfo>();
			while (rs.next()) {
				BookIssueInfo issueDetails = new BookIssueInfo();
				issueDetails.setUserId(rs.getInt("sId"));
				beans.add(issueDetails);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ArrayList<BookRequestInfo> showRequests() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showRequestsQuery);) {
			ArrayList<BookRequestInfo> beans = new ArrayList<BookRequestInfo>();
			while (rs.next()) {
				BookRequestInfo bean = new BookRequestInfo();
				bean.setsId(rs.getInt("sId"));
				bean.setFullName(rs.getString("fullName"));
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<BookIssueInfo> showIssuedBooks() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showIssuedBooksQuery);) {
			ArrayList<BookIssueInfo> beans = new ArrayList<BookIssueInfo>();
			while (rs.next()) {
				BookIssueInfo bean = new BookIssueInfo();
				bean.setBookId(rs.getInt("bookId"));
				bean.setUserId(rs.getInt("userId"));
				bean.setIssueDate(rs.getDate("issueDate"));
				bean.setReturnDate(rs.getDate("returnDate"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<UserInfo> showUsers() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showUsersQuery);) {
			ArrayList<UserInfo> beans = new ArrayList<UserInfo>();
			while (rs.next()) {
				UserInfo bean = new UserInfo();
				bean.setSId(rs.getInt("sId"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getLong("mobile"));
				bean.setRole(rs.getString("role"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}



}
