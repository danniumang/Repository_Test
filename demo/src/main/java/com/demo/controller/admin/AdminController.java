package com.demo.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Auser;
import com.demo.service.admin.AdminService;
 
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	@GetMapping("/admin")
	public String tologin(String name) {
	
		return "admin/login";//jsp
		
	}
	@PostMapping("/adminlogin")
	public String login(@RequestBody Auser auser,Model model,HttpSession session) {
		return adminService.login(auser, model, session);
	}
	@PostMapping("/exit")
	public String exit(@RequestBody Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}

}
