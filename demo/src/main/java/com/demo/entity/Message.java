package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	private String name;
	private String content;
	private Date date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    public String toString() {
    	return "Message{ name:"+name+" ,content: "+content+" , date: "+date+"}";
    }
}
