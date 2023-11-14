package com.demo.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * user 使用validation 进行校验
 */
public class User {
	
	private int id;
	@Size(min=5,max=10,message="{user.username.size}")
	private String username;
	private String address;
	private String gender;
	private String phone;
     @Email(message="{user.mail.pattern}")
     @NotNull(message="user.mail.notnull")//分组校验
	 private String mail; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	  public String getMail() { return mail; } public void setMail(String mail) {
	  this.mail = mail; }
	 

}
