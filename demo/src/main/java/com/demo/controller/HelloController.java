package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Con")
public class HelloController {
	@GetMapping("/hello")
	public String hello(HttpSession session) {
		session.setAttribute("hellow", "this is session test");
		System.out.println("hellow");
		return "hello";
	}

}
