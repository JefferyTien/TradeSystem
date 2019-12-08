package com.reddoor.framework.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.framework.dao.Page;
import com.reddoor.framework.dao.PropertyFilter;
import com.reddoor.framework.domain.User;
import com.reddoor.framework.service.UserRoleService;
import com.reddoor.framework.service.UserService;

@Controller
@RequestMapping("system/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value="checkLoginName.do",method = RequestMethod.POST)
	@ResponseBody
	public String checkLoginName(String loginName){
		if(userService.getUser(loginName) == null){
			return "true";
		}
		else{
			return "false";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "system/userList";
	}
	
	@RequestMapping(value="all.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findAll(HttpServletRequest request){
		Page<User> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		
		page = userService.search(page, filters);
		return getEasyUIData(page);
	}
	
	/**
	 * 新增用户页面跳转
	 */
	@RequestMapping(value="userForm.do",method = RequestMethod.GET)
	public String createForm(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("action", "create.do");
		return "system/userForm";
	}
	
	/**
	 * 新增用户
	 */
	@RequestMapping(value="create.do",method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid User user, Model model){
		userService.create(user);
		return "success";
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "delete/{id}.do")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		userService.deleteById(id);
		return "success";
	}
	
	/**
	 * 修改用户跳转
	 */
	@RequestMapping(value = "update/{id}.do", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("action", "update.do");
		return "system/userForm";
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("user") @RequestBody User user,Model model) {
		userService.updateUser(user);
		return "success";
	}
	
	/**
	 * 用户角色页面跳转
	 */
	@RequestMapping(value = "{userId}/userRole.do", method = RequestMethod.GET)
	public String userRole(@PathVariable("userId") Integer id, Model model){
		model.addAttribute("userId", id);
		return "system/userRoleList";
	}
	
	/**
	 * 获取用户的角色
	 */
	@RequestMapping(value = "{id}/role.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> getRolesById(@PathVariable("id") Integer id, Model model){
		return userRoleService.getRolesById(id);
	}
	
	/**
	 * 修改用户拥有的角色
	 */
	@RequestMapping(value = "{id}/updateRole.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserRole(@PathVariable("id") Integer id, @RequestBody List<Integer> newRoleList){
		userRoleService.updateUserRole(id,userRoleService.getRolesById(id), newRoleList);
		return "success";
	}
	
	
	/**
	 * 修改密码跳转
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwdForm(Model model, HttpSession session) {
		model.addAttribute("user", (User) session.getAttribute("user"));
		return "system/updatePwd";
	}
	
	/**
	 * ajax请求校验原密码是否正确
	 * 
	 * @param oldPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkPwd", method = RequestMethod.POST)
	@ResponseBody
	public String checkPwd(String oldPassword, HttpSession session) {
		if (userService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPassword,@Valid @ModelAttribute @RequestBody User user, HttpSession session) {
		if (userService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			userService.updatePwd(user);
			session.setAttribute("user", user);
			return "success";
		} else {
			return "fail";
		}
	}
	
}
