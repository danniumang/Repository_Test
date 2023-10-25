package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Session_RedisController {
	//启动项目时的server.port
	//nuhup java -jar session-0.0.1SNAPSHOT.jar --server.port=8080 &
	//nuhup java -jar session-0.0.1SNAPSHOT.jar --server.port=8081 &
	@Value("${server.port}")
	String port;
	@PostMapping("/saveSession")
	public String saveName(String name,HttpSession session) {
		session.setAttribute("name", name);
		return port;
	}
	@GetMapping("/getSession")
	public String getName(HttpSession session) {
		return port+" : "+session.getAttribute("name").toString();
	}

}
