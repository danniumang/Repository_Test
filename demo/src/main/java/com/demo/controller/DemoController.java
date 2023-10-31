package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/WebSocket")
public class DemoController {
	 @GetMapping("/toWebSocketDemo/{cid}")
	    public String toWebSocketDemo(@PathVariable String cid, Model model) {
	        model.addAttribute("cid", cid);
	        return "websocketDemo";
	    }

}
