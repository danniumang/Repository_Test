package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.Auser;

@RestController
public class HelloWordController {
	@GetMapping("/admin/hello")
	public String admin() {
		return "hello admin";
	}
	@GetMapping("/user/hello")
	public String user() {
		return "hello user";
	}
	@GetMapping("/db/hello")
	public String dba() {
		return "hello dba";
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello ";
	}
	
	@GetMapping("/admin/userhtml")
	public ModelAndView userhtml() {
		List<Auser>ausers=new ArrayList<>();
		Auser a1=new Auser();
		a1.setAname("a1");
		a1.setApwd("123");
		Auser a2=new Auser();
		a2.setAname("a2");
		a2.setApwd("123");
		ausers.add(a2);
		ausers.add(a1);
		ModelAndView mv=new ModelAndView();
		mv.addObject("ausers",ausers);
		mv.setViewName("user");
		return mv;
	}

}
