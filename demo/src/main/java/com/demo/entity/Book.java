package com.demo.entity;

import java.io.Serializable;

public class Book implements Serializable{
	private Integer bookId;
	private String bookName;
	private String author;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String toString() {
		return "book:{ bookId="+bookId+" , bookName="+bookName+" , author="+author+"}";
	}

}
