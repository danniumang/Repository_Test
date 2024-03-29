package com.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.demo.entity.Message;

@Controller
public class GreetingController {
	@MessageMapping("/helloWebSocket")
	@SendTo("/topic/greetings")
	public Message  greeting(Message  message) {
		return message;
	}

}
