package com.reddoor.tradesystem.zoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lab")
public class LabController {
	@RequestMapping("test.do")
	public String test() {
		return "lab/test";
	}
	
	@RequestMapping("lottery.do")
	public String lottery() {
		return "lab/lottery";
	}
	
	@RequestMapping("dashboard.do")
	public String dashboard() {
		return "lab/dashboard";
	}
}
