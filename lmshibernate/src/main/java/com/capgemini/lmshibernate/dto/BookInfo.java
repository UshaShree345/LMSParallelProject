package com.capgemini.lmshibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "bookbean")
@SequenceGenerator(name = "seq3", initialValue = 101, allocationSize = 100)
public class BookInfo {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String bookAuthor;
	@Column
	private String bookCategory;
	@Column
	private String bookPublisher;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	/*
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BookIssueDetails> issueDetails;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<RequestDetails> requests;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BorrowedBooks> borrowed;
	 * 
	 */
}
