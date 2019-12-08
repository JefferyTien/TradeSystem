package com.reddoor.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("system/config")
public class ConfigController extends BaseController{

	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "system/configList";
	}
}
