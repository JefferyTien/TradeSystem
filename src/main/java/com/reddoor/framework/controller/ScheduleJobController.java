package com.reddoor.framework.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("system/scheduleJob")
public class ScheduleJobController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/scheduleJobList";
	}
	
}
