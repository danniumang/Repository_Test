package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceus {
	public String getUserById(Integer id) {
		System.out.println("get "+id);
		return "user";
	}
	public void deleteUserById(Integer id) {
		System.out.println("delete "+id);
	}

}
