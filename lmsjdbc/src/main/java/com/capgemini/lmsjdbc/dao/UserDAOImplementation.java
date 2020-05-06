package com.capgemini.lmsjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.lmsjdbc.dto.BookInfo;
import com.capgemini.lmsjdbc.dto.BookIssueInfo;
import com.capgemini.lmsjdbc.dto.BorrowedBooksInfo;
import com.capgemini.lmsjdbc.dto.RequestsInfo;
import com.capgemini.lmsjdbc.dto.UserInfo;
import com.capgemini.lmsjdbc.exception.LMSException;
import com.capgemini.lmsjdbc.utility.JdbcUtility;

public class UserDAOImplementation implements UserDAO {

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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean request(int sId, int bId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.requestQuery1);) {
			statement.setInt(1, sId);
			statement.setInt(2, bId);
			statement.setInt(3, sId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int isBookExists = rs.getInt("sId");
				if (isBookExists == 0) {
					try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.requestQuery2);) {
						pstmt1.setInt(1, sId);
						rs = pstmt1.executeQuery();
						if (rs.next()) {
							int noOfBooksBorrowed = rs.getInt("sId");
							if (noOfBooksBorrowed < 3) {
								try (PreparedStatement pstmt2 = connection
										.prepareStatement(QueryMapper.requestQuery3);) {
									pstmt2.setInt(1, sId);
									pstmt2.setInt(2, sId);
									pstmt2.setInt(3, bId);
									pstmt2.setInt(4, bId);
									pstmt2.setInt(5, sId);
									int count = pstmt2.executeUpdate();
									if (count != 0) {
										return true;
									} else {
										return false;
									}
								}
							} else {
								throw new LMSException("no Of books limit has crossed");
							}
						} else {
							throw new LMSException("no of books limit has crossed");
						}
					}
				} else {
					throw new LMSException("You have already borrowed the requested book");
				}
			} else {
				throw new LMSException("You have already borrowed the requested book");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public List<BorrowedBooksInfo> borrowedBook(int sId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.borrowQuery);) {
			statement.setInt(1, sId);
			rs = statement.executeQuery();
			ArrayList<BorrowedBooksInfo> beans = new ArrayList<BorrowedBooksInfo>();
			while (rs.next()) {
				BorrowedBooksInfo listOfbooksBorrowed = new BorrowedBooksInfo();
				listOfbooksBorrowed.setsId(rs.getInt("sId"));
				listOfbooksBorrowed.setbId(rs.getInt("bId"));
				listOfbooksBorrowed.setEmail(rs.getString("email"));
				beans.add(listOfbooksBorrowed);
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
	public boolean returnBook(int bId, int sId, String status) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.returnBookQuery1);) {
			statement.setInt(1, bId);
			statement.setInt(2, sId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Date issueDate = rs.getDate("issueDate");
				Calendar cal = Calendar.getInstance();
				Date returnDate = cal.getTime();
				long difference = issueDate.getTime() - returnDate.getTime();
				float daysBetween = (difference / (1000 * 60 * 60 * 24));
				if (daysBetween > 7) {
					float fine = daysBetween * 5;
					System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
					if (status == "yes") {
						try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.returnBookQuery2);) {
							pstmt1.setInt(1, bId);
							pstmt1.setInt(2, sId);
							int count = pstmt1.executeUpdate();
							if (count != 0) {
								try (PreparedStatement pstmt2 = connection
										.prepareStatement(QueryMapper.returnBookQuery3);) {
									pstmt2.setInt(1, bId);
									pstmt2.setInt(2, sId);
									int isReturned = pstmt2.executeUpdate();
									if (isReturned != 0) {
										try (PreparedStatement pstmt3 = connection
												.prepareStatement(QueryMapper.returnBookQuery4);) {
											pstmt3.setInt(1, bId);
											pstmt3.setInt(2, sId);
											int isRequestDeleted = pstmt3.executeUpdate();
											if (isRequestDeleted != 0) {
												return true;
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							} else {
								return false;
							}
						}
					} else {
						throw new LMSException("The user has to pay fine for delaying book return");
					}
				} else {
					try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.returnBookQuery2);) {
						pstmt1.setInt(1, bId);
						pstmt1.setInt(2, sId);
						int count = pstmt1.executeUpdate();
						if (count != 0) {
							try (PreparedStatement pstmt2 = connection
									.prepareStatement(QueryMapper.returnBookQuery3);) {
								pstmt2.setInt(1, bId);
								pstmt2.setInt(2, sId);
								int isReturned = pstmt2.executeUpdate();
								if (isReturned != 0) {
									try (PreparedStatement pstmt3 = connection
											.prepareStatement(QueryMapper.returnBookQuery4);) {
										pstmt3.setInt(1, bId);
										pstmt3.setInt(2, sId);
										int isRequestDeleted = pstmt3.executeUpdate();
										if (isRequestDeleted != 0) {
											return true;
										} else {
											return false;
										}
									}
								} else {
									return false;
								}
							}
						} else {
							return false;
						}
					}
				}
			} else {
				throw new LMSException("This respective user hasn't borrowed any book");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public ArrayList<RequestsInfo> showRequests() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showRequestsQuery);) {
			ArrayList<RequestsInfo> beans = new ArrayList<RequestsInfo>();
			while (rs.next()) {
				RequestsInfo bean = new RequestsInfo();
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
