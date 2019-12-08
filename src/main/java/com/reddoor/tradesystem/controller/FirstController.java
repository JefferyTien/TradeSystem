package com.reddoor.tradesystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {
	
	@RequestMapping(value={"/","/first.do","/login.do"})
	public String first(Model model){
		return "login";
	}
	
	@RequestMapping("/home.do")
	public String home(HttpSession session, Model model) {
		return "home";
	}
}
