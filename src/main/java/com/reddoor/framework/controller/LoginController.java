package com.reddoor.framework.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController extends BaseController{

//	@RequestMapping(value="logout")
//	public String logout(Model model) {
//		Subject subject = SecurityUtils.getSubject();
//		subject.logout();
//		return "system/login";
//	}
}
