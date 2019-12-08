package com.reddoor.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.domain.PermissionWrapper4TreeGrid;
import com.reddoor.framework.service.PermissionService;

@Controller
@RequestMapping("system/permission")
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "system/permissionList";
	}
	
	/**
	 * 菜单页面
	 */
	@RequestMapping(value="menu.do",method = RequestMethod.GET)
	public String menuList(){
		return "system/menuList";
	}
	
//	@RequestMapping(value="menu/all.do", method = RequestMethod.GET)
//	@ResponseBody
//	public PermissionWrapper4TreeGrid menuData(){
//		List<Permission> allMenus = permissionService.getAllMenus();
//		return new PermissionWrapper4TreeGrid(allMenus);
//	}
	
	@RequestMapping(value="menu/all.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> menuData(){
		List<Permission> allMenus = permissionService.getAllMenus();
		return allMenus;
	}
	
	/**
	 * 权限集合(JSON)
	 */
	@RequestMapping(value="all.do",method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> getData() {
		List<Permission> permissionList=permissionService.getAll();
		return permissionList;
	}
	
	@RequestMapping(value="menu/create.do",method = RequestMethod.GET)
	public String menuCreateForm(Model model){
		model.addAttribute("permission", new Permission());
		model.addAttribute("action", "create.do");
		return "system/menuForm";
	}
	
	/**
	 * 添加权限跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("permission", new Permission());
		model.addAttribute("action", "create.do");
		return "system/permissionForm";
	}
	
	@RequestMapping(value="create.do",method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Permission permission, Model model){
		permissionService.insert(permission);
		return "success";
	}
	
	@RequestMapping(value = "delete/{id}.do")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		permissionService.delete(id);
		return "success";
	}
	
	/**
	 * 修改菜单跳转
	 */
	@RequestMapping(value = "menu/update/{id}.do", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("permission", permissionService.get(id));
		model.addAttribute("action", "update.do");
		return "system/menuForm";
	}
	
	/**
	 * 修改权限跳转
	 */
	@RequestMapping(value = "update/{id}.do", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("permission", permissionService.get(id));
		model.addAttribute("action", "update.do");
		return "system/permissionForm";
	}
	
	/**
	 * 修改权限/菜单
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("permission") Permission permission,Model model) {
		permissionService.update(permission);
		return "success";
	}
	
	/**
	 * 获取菜单下的操作
	 */
	@RequestMapping("ope/all.do")
	@ResponseBody
	public Map<String, Object> menuOperationData(Integer pid){
		List<Permission> menuOperList=permissionService.getMenuOperation(pid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", menuOperList);
		map.put("total",menuOperList.size());
		return map;
	}
}
