package com.capgemini.lmsjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.capgemini.lmsjdbc.dto.BookBorrowedInfo;
import com.capgemini.lmsjdbc.exception.LMSException;
import com.capgemini.lmsjdbc.utility.JdbcUtility;

public class StudentDAOImplementation implements StudentDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;
	
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
	public List<BookBorrowedInfo> borrowedBook(int sId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.borrowQuery);) {
			statement.setInt(1, sId);
			rs = statement.executeQuery();
			ArrayList<BookBorrowedInfo> beans = new ArrayList<BookBorrowedInfo>();
			while (rs.next()) {
				BookBorrowedInfo listOfbooksBorrowed = new BookBorrowedInfo();
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


}
