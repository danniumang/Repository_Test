package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.UserServiceus;

@RestController
public class UserController {
	@Autowired
	UserServiceus userService;
	@GetMapping("/getUserById")
	public String getUserById(Integer id) {
		return userService.getUserById(id);
	}
	@GetMapping("/deleteUserById")
	public void deleteUserById(Integer id) {
		userService.deleteUserById(id);
	}

}
