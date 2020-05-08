package com.capgemini.lmsjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.exception.LMSException;
import com.capgemini.lmsjdbc.utility.JdbcUtility;

public class LibrarianStudentDAOImplementation implements LibrarianStudentDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(UserInfo user) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.registerQuery);) {

			statement.setInt(1, user.getSId());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setLong(6, user.getMobile());
			statement.setString(7, user.getRole());
			int count = statement.executeUpdate();
			if ((user.getEmail().isEmpty()) && (count == 0)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserInfo login(String email, String password) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.loginQuery);) {

			statement.setString(1, email);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				UserInfo bean = new UserInfo();
				bean.setSId(rs.getInt("sId"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getLong("mobile"));
				bean.setRole(rs.getString("role"));
				return bean;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   
	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.titleQuery);) {

			statement.setString(1, bookName);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthor(rs.getString("author"));
				bean.setCategory(rs.getString("category"));
				bean.setPublisher(rs.getString("publisher"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.authorQuery);) {

			statement.setString(1, author);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthor(rs.getString("author"));
				bean.setCategory(rs.getString("category"));
				bean.setPublisher(rs.getString("publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();) {
			rs = stmt.executeQuery(QueryMapper.getAllBooksQuery);
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthor(rs.getString("author"));
				bean.setCategory(rs.getString("category"));
				bean.setPublisher(rs.getString("publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   
	@Override
	public ArrayList<BookInfo> searchBookById(int bId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.searchIdQuery);) {
			statement.setInt(1, bId);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthor(rs.getString("author"));
				bean.setCategory(rs.getString("category"));
				bean.setPublisher(rs.getString("publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.updatePasswordQuery1);) {
			statement.setString(1, email);
			statement.setString(2, role);
			rs = statement.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.updatePasswordQuery2);) {
					pstmt.setString(1, newPassword);
					pstmt.setString(2, email);
					pstmt.setString(3, password);
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				throw new LMSException("user doesnt exist");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}


}
