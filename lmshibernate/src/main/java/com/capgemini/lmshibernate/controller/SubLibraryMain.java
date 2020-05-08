package com.capgemini.lmshibernate.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.lmshibernate.dto.BookBorrowedInfo;
import com.capgemini.lmshibernate.dto.BookInfo;
import com.capgemini.lmshibernate.dto.BookIssueInfo;
import com.capgemini.lmshibernate.dto.BookRequestInfo;
import com.capgemini.lmshibernate.dto.UserInfo;
import com.capgemini.lmshibernate.exception.LMSException;
import com.capgemini.lmshibernate.factory.Factory;
import com.capgemini.lmshibernate.service.LibrarianService;
import com.capgemini.lmshibernate.service.LibrarianStudentService;
import com.capgemini.lmshibernate.service.StudentService;
import com.capgemini.lmshibernate.validation.Validation;

public class SubLibraryMain {

	public static void LibraryOperations() {

		boolean flag = false;
	//	int checkId = 0;
		String checkFirstName = null;
		String checkLastName = null;
		long checkMobile = 0;
		String checkEmail = null;
		String checkPassword = null;
		String checkRole = null;
		boolean checkStatus = true;

		Validation validation = new Validation();

		LibrarianStudentService service1 = Factory.getLibrarianStudentService();
		LibrarianService service2 = Factory.getLibrarianService();
		StudentService service3 = Factory.getStudentService();

		do {
			try (Scanner scanner = new Scanner(System.in);) {
				System.out.println("<--------LIBRARY MANAGEMENT SYSTEM--------->");
				System.out.println("[1]  REGISTER");
				System.out.println("[2]  LOGIN");
				System.out.println("[3]  EXIT");
				do {
					try {

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							/*
							 * do { try { System.out.println("Enter ID :"); regId = scanner.nextInt();
							 * validation.validateId(regId); flag = true; } catch (InputMismatchException e)
							 * { flag = false; System.err.println("Id should contains only digits"); } catch
							 * (LMSException e) { flag = false; System.err.println(e.getMessage()); } }
							 * while (!flag);
							 */

							do {
								try {
									System.out.println("Enter First Name :");
									checkFirstName = scanner.next();
									validation.validatedName(checkFirstName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Name should contains only Alphabates");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);
							do {
								try {
									System.out.println("Enter Last Name :");
									checkLastName = scanner.next();
									validation.validatedName(checkLastName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Name should contains only Alphabates");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter Email :");
									checkEmail = scanner.next();
									validation.validatedEmail(checkEmail);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Email should be proper ");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter Password :");
									checkPassword = scanner.next();
									validation.validatedPassword(checkPassword);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Enter correct Password ");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter Mobile :");
									checkMobile = scanner.nextLong();
									validation.validatedMobile(checkMobile);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Mobile Number  should contains only numbers");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter Role :");
									checkRole = scanner.next();
									validation.validatedRole(checkRole);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Role should either Admin or Student");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							UserInfo ai = new UserInfo();
							// ai.setId(regId);
							ai.setFirstName(checkFirstName);
							ai.setLastName(checkLastName);
							ai.setEmail(checkEmail);
							ai.setPassword(checkPassword);
							ai.setMobile(checkMobile);
							ai.setRole(checkRole);
							try {
								boolean check = service1.register(ai);
								if (check) {
									System.out.println("Registered");
								} else {
									System.out.println("Already UserInfo is registered");
								}
							} catch (LMSException e) {
								System.err.println(e.getMessage());
							}
							break;
						case 2:
							System.out.println("enter email");
							String email = scanner.next();
							System.out.println("enter password");
							String password = scanner.next();
							try {
								UserInfo loginInfo = service1.login(email, password);
								if (loginInfo.getEmail().equals(email) && loginInfo.getPassword().equals(password)) {
									System.out.println("Logged In Successfully");
								}
								if (loginInfo.getRole().equals("admin")) {
									do {
										try {
											System.out.println("-----------------------------------------------");
											System.out.println("[1]  ADD BOOK");
											System.out.println("[2]  REMOVE BOOK");
											System.out.println("[3]  ISSUE BOOK");
											System.out.println("[4]  SEARCH BOOK BY AUTHOR NAME");
											System.out.println("[5]  SEARCH BOOK BY BOOK TITLE");
											System.out.println("[6]  VIEW ALL BOOKS");
											System.out.println("[7]  SEARCH BOOK BY BOOK ID");
											System.out.println("[8]  UPDATE BOOK");
											System.out.println("[9]  CHECK STUDENT BOOK HISTROY");
											System.out.println("[10] VIEW ALL REQUESTS");
											System.out.println("[11] VIEW ISSUED BOOKS");
											System.out.println("[12] VIEW ALL USERS");
											System.out.println("[13] UPDATE PASSWORD");
											System.out.println("[14] LOGOUT");

											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:
												System.out.println("enter id");
												int addId = scanner.nextInt();
												System.out.println("enter bookname");
												String addName = scanner.next();
												System.out.println("enter authorname");
												String addAuth = scanner.next();
												System.out.println("enter category");
												String addCategory = scanner.next();
												System.out.println("enter publisher");
												String addPublisher = scanner.next();
												/*
												 * System.out.println("enter no of copies"); int addCopies =
												 * scanner.nextInt();
												 */
												BookInfo bi = new BookInfo();
												bi.setBookId(addId);
												bi.setBookName(addName);
												bi.setBookAuthor(addAuth);
												bi.setBookCategory(addCategory);
												bi.setBookPublisher(addPublisher);
												// bi.setCopies(addCopies);
												try {
													boolean check2 = service2.addBook(bi);
													if (check2) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Added Book");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book not added");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}

												break;
											case 2:
												System.out.println("enter id");
												int removeId = scanner.nextInt();
												try {
													boolean check3 = service2.removeBook(removeId);
													if (check3) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Removed Book");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book not removed");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 3:
												System.out.println("enter Book Id");
												int issueId = scanner.nextInt();
												System.out.println("Enter UserInfo Id");
												int UserInfoId = scanner.nextInt();
												try {
													boolean check4 = service2.issueBook(issueId, UserInfoId);
													if (check4) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book Issued");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book not issued");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 4:
												System.out.println("Search the book by the Author Name:");
												String author = scanner.next();
												try {
													List<BookInfo> bookauthor = service1.searchBookByAuthor(author);
													if (!bookauthor.isEmpty() && bookauthor != null) {
														System.out.println(
																"<--------------------------------------------------------------------->");
														System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
																"BookId", "BookName", "BookAuthor", "BookCategory",
																"BookPublisherName"));
														for (BookInfo bookBean : bookauthor) {

															if (bookBean != null) {
																System.out.println(bookBean.toString());
															}
														}
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println(
																"No books are available written by this author");
													}

												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 5:
												System.out.println("  Search the book by the Book_Title :");
												String btitle = scanner.next();
												try {
													List<BookInfo> booktitle = service1.searchBookByTitle(btitle);
													if (!booktitle.isEmpty() && booktitle != null) {
														System.out.println(
																"<--------------------------------------------------------------------->");
														System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
																"BookId", "BookName", "BookAuthor", "BookCategory",
																"BookPublisherName"));
														for (BookInfo bookBean : booktitle) {
															if (bookBean != null) {
																System.out.println(bookBean.toString());
															}
														}
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("No books are available with this title.");
													}

												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 6:
												try {
													List<BookInfo> info = service1.getBooksInfo();
													if (!info.isEmpty() && info != null) {
														System.out.println(
																"<--------------------------------------------------------------------->");
														System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
																"BookId", "BookName", "BookAuthor", "BookCategory",
																"BookPublisherName"));
														for (BookInfo bookBean : info) {

															if (bookBean != null) {

																System.out.println(bookBean.toString());
															}
														}
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Books info is not present");
													}

												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 7:
												System.out.println("  Search the book by the Book_ID :");
												int book_Id = scanner.nextInt();
												try {
													List<BookInfo> bId = service1.searchBookById(book_Id);
													if (!bId.isEmpty() && bId != null) {
														System.out.println(String.format("%-10s %-15s %-15s %-10s %s",
																"BookId", "BookName", "AuthorName", "BookCategory",
																"PublisherName"));
														for (BookInfo bookBean : bId) {
															if (bookBean != null) {
																System.out.println(String.format(
																		"%-10s %-15s %-15s %-10s %s",
																		bookBean.getBookId(), bookBean.getBookName(),
																		bookBean.getBookAuthor(),
																		bookBean.getBookCategory(),
																		bookBean.getBookPublisher()));

															}
														}
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.err.println("No books are available with this ID.");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 8:
												System.out.println("Enter the updated id :");
												int bid = scanner.nextInt();
												System.out.println("Enter bookName to be updtaed");
												String updatedBookName = scanner.next();
												BookInfo bean2 = new BookInfo();
												bean2.setBookId(bid);
												bean2.setBookName(updatedBookName);
												try {
													boolean updated = service2.updateBook(bean2);
													if (updated) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book is updated");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Book is not updated");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 9:
												System.out.println("Enter the UserInfo Id");
												int UserInfo_Id = scanner.nextInt();
												try {
													List<Integer> uid = service2.bookHistoryDetails(UserInfo_Id);
													for (Integer issueDetails : uid) {
														if (issueDetails != null) {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("No of books Borrowed :" + issueDetails);
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println(
																	"Respective UserInfo hasn't borrowed any books");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 10:
												System.out.println(" Requests received are:");
												try {
													List<BookRequestInfo> requests = service2.showRequests();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %s",
															"UserInfoId", "UserInfoName", "BookId", "BookName"));

													for (BookRequestInfo requestBean : requests) {
														if (requestBean != null) {
															System.out.println(requestBean.toString());
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("No Requests are received");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 11:
												System.out.println("Issued Books are:");
												try {
													List<BookIssueInfo> issuedBooks = service2.showIssuedBooks();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %s", "BookId",
															"UserInfoId", "IssueDate", "ReturnDate"));
													for (BookIssueInfo issueBean : issuedBooks) {
														if (issueBean != null) {

															System.out.println(issueBean.toString());
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("No book has been issued");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 12:
												System.out.println("UserInfos are:");
												try {
													List<UserInfo> UserInfos = service2.showUsers();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(
															String.format("%-10s %-10s %-10s %-15s %-10s %-13s %s",
																	"UserInfoId", "FirstName", "LastName", "Email",
																	"Password", "MobileNumber", "Role"));
													for (UserInfo bean : UserInfos) {
														if (bean != null) {
															System.out.println(bean.toString());
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("No UserInfos are present");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 13:
												System.out.println("Enter the Id :");
												int id = scanner.nextInt();
												System.out.println("Enter the Old password");
												String old_Password = scanner.next();
												System.out.println("Enter the new password");
												String new_Password = scanner.next();
												String UserInfo_Role = loginInfo.getRole();
												try {
													boolean updated = service1.updatePassword(id, old_Password,
															new_Password, UserInfo_Role);
													if (updated) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Password is updated");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Password is not updated");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 14:
												LibraryOperations();

											default:
												System.out.println("-----------------------------------------------");
												System.out.println("Invalid Choice");
												break;
											}
										} catch (InputMismatchException ex) {
											System.out
													.println("Incorrect entry. Please input only a positive integer.");
											scanner.nextLine();
										}
									} while (true);
								} else if (loginInfo.getRole().equals("student")) {
									do {
										try {
											System.out.println("-----------------------------------------------");
											System.out.println("[1]  REQUEST BOOK");
											System.out.println("[2]  VIEW BORROWED BOOKS");
											System.out.println("[3]  SEARCH BOOK BY AUTHOR NAME");
											System.out.println("[4]  SEARCH BOOK BY BOOK TITLE");
											System.out.println("[5]  SEARCH BOOK BY BOOK ID");
											System.out.println("[6]  VIEW ALL BOOKS");
											System.out.println("[7]  RETURN BOOK");
											System.out.println("[8]  UPDATE PASSWORD");
											System.out.println("[9]  LOGOUT");

											int choice2 = scanner.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Enter the Book Id:");
												int reqBookId = scanner.nextInt();
												System.out.println("Enter the UserInfo Id:");
												int reqUserInfoId = scanner.nextInt();
												try {
													if (loginInfo.getUserId() == reqUserInfoId) {
														boolean requested = service3.request(reqUserInfoId, reqBookId);
														if (requested != false) {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Book is Requested");
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Book is not Requested");
														}
													} else {
														System.out.println("Enter the correct UserInfoId");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 2:
												System.out.println("Enter the UserInfo Id");
												int UserInfo_Id = scanner.nextInt();
												try {
													if (loginInfo.getUserId() == UserInfo_Id) {
														List<BookBorrowedInfo> borrowedBookList = service3
																.borrowedBook(UserInfo_Id);
														System.out.println(
																"<--------------------------------------------------------------------->");
														System.out.println(String.format("%-10s %-10s %s", "UserInfoId",
																"BookId", "EmailId"));
														for (BookBorrowedInfo bookBean : borrowedBookList) {

															if (bookBean != null) {
																System.out.println(bookBean.toString());

															} else {
																System.out.println(
																		"-----------------------------------------------");
																System.out.println(
																		"No books are borrowed by the UserInfo");
															}
														}
													} else {
														System.out.println("Incorrect UserInfo_Id");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 3:
												System.out.println("Search the book by the Author Name :");
												String author = scanner.next();
												try {
													List<BookInfo> bookauthor = service1.searchBookByAuthor(author);
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
															"BookId", "BookName", "BookAuthor", "BookCategory",
															"BookPublisherName"));
													for (BookInfo bookBean : bookauthor) {

														if (bookBean != null) {

															System.out.println(bookBean.toString());
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println(
																	"No books are available written by this author");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 4:
												System.out.println("Search the book by the Book Title :");
												String btitle = scanner.next();

												try {
													List<BookInfo> booktitle = service1.searchBookByTitle(btitle);
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
															"BookId", "BookName", "BookAuthor", "BookCategory",
															"BookPublisherName"));
													for (BookInfo bookBean : booktitle) {
														if (bookBean != null) {
															System.out.println(bookBean.toString());

														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out
																	.println("No books are available with this title.");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 5:
												System.out.println("  Search the book by the Book category :");
												String bcategory = scanner.next();

												try {
													BookInfo bean5 = new BookInfo();
													bean5.setBookName(bcategory);
													List<BookInfo> bookCategory1 = service1
															.searchBookByTitle(bcategory);
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
															"BookId", "BookName", "BookAuthor", "BookCategory",
															"BookPublisherName"));
													for (BookInfo bookBean : bookCategory1) {
														if (bookBean != null) {
															System.out.println(bookBean.toString());
														} else {
															System.out
																	.println("No books are available with this title.");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 6:
												try {
													List<BookInfo> info = service1.getBooksInfo();
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s",
															"BookId", "BookName", "BookAuthor", "BookCategory",
															"BookPublisherName"));
													for (BookInfo bookBean : info) {

														if (bookBean != null) {

															System.out.println(bookBean.toString());
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Books info is not presernt");
														}
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 7:
												System.out.println("Enter the Book id to return :");
												int returnId = scanner.nextInt();
												System.out.println("Enter UserInfoId");
												int UserInfoId = scanner.nextInt();
												System.out.println("Enter the status of the book");
												String status = scanner.next();
												try {
													if (loginInfo.getUserId() == UserInfoId) {
														boolean returned = service3.returnBook(returnId, UserInfoId,
																status);
														if (returned != false) {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Book is Returned");
														} else {
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Book is not Returned");
														}
													} else {
														System.out.println("Invalid UserInfoId");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 8:
												System.out.println("Enter the Id :");
												int id = scanner.nextInt();
												System.out.println("Enter the Old password");
												String old_Password = scanner.next();
												System.out.println("Enter the new password");
												String new_Password = scanner.next();
												String UserInfo_Role = loginInfo.getRole();
												try {
													boolean updated = service1.updatePassword(id, old_Password,
															new_Password, UserInfo_Role);
													if (updated) {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Password is updated");
													} else {
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Password is not updated");
													}
												} catch (LMSException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 9:
												LibraryOperations();
											default:
												break;
											}
										} catch (InputMismatchException ex) {
											System.out
													.println("Incorrect entry. Please input only a positive integer.");
											scanner.nextLine();
										}
									} while (true);
								}
							} catch (Exception e) {
								System.out.println("Invalid Credentials");
								System.out.println("Try logging in again,Press 2 to login");
							}
							break;
						/*
						 * case 3: System.out.println("EXIT"); //loginStatus = false; break;
						 */
						default:
							break;
						}
					} catch (InputMismatchException ex) {
						System.out.println("Incorrect entry. Please input only a positive integer.");
						scanner.nextLine();
					}
				} while (true);
			}
		} while (checkStatus);
	}

}
