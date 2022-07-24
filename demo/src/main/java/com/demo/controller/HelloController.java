package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Con")
public class HelloController {
	@GetMapping("/hello")
	public String hello() {
		System.out.println("hellow");
		return "hello";
	}

}
