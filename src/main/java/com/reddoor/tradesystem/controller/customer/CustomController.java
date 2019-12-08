package com.reddoor.tradesystem.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.tradesystem.domain.Custom;
import com.reddoor.tradesystem.service.CustomService;


@Controller
@RequestMapping("/custom")
public class CustomController {
	
	@Autowired
	CustomService customService;
	
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Custom> getData() throws Exception{
		List<Custom> list = customService.find();
		return list;
	}
}
