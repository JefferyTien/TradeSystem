package com.reddoor.framework.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.framework.domain.Role;
import com.reddoor.framework.domain.User;
import com.reddoor.framework.service.RolePermissionService;
import com.reddoor.framework.service.RoleService;

@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolePermissionService rolePermissionService;

	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "system/roleList";
	}
	
	@RequestMapping(value="all.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> findAll(){
		List<Role> allUser = roleService.findAll();
		return allUser;
	}
	
	/**
	 * 角色集合(JSON)
	 */
//	@RequestMapping(value="all.do",method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getData(HttpServletRequest request) {
//		Page<Role> page=getPage(request);
//		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
//		page = roleService.search(page, filters);
//		return getEasyUIData(page);
//	}
	
	/**
	 * 获取角色拥有的权限ID集合
	 * @param id
	 * @return
	 */
	@RequestMapping("{id}/all.do")
	@ResponseBody
	public List<Integer> getRolePermissions(@PathVariable("id") Integer id){
		List<Integer> permissionIdList=rolePermissionService.getPermissionIds(id);
		return permissionIdList;
	}
	
	/**
	 * 修改角色权限
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequestMapping(value = "{id}/updatePermission")
	@ResponseBody
	public String updateRolePermission(@PathVariable("id") Integer id,@RequestBody List<Integer> newPermIdList,HttpSession session){
		List<Integer> oldPermIdList=rolePermissionService.getPermissionIds(id);
		
		//获取application中的sessions
//		@SuppressWarnings("rawtypes")
//		HashSet sessions=(HashSet) session.getServletContext().getAttribute("sessions");
//		@SuppressWarnings("unchecked")
//		Iterator<Session> iterator= sessions.iterator();
//		PrincipalCollection pc=null;
//		//遍历sessions
//		while(iterator.hasNext()){
//			HttpSession s=(HttpSession) iterator.next();
//			User user=(User) s.getAttribute("user");
//			if(user.getId()==id){
//				pc= (PrincipalCollection) s.getAttribute(String.valueOf(id));
//				//清空该用户权限缓存
//				rolePermissionService.clearUserPermCache(pc);
//				s.removeAttribute(String.valueOf(id));
//				break;
//			}
//		}
		
		rolePermissionService.updateRolePermission(id,oldPermIdList ,newPermIdList);
		
		return "success";
	}
	
	/**
	 * 添加角色跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "create.do");
		return "system/roleForm";
	}

	/**
	 * 添加角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Role role,Model model) {
		roleService.add(role);
		return "success";
	}

	/**
	 * 修改角色跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("role", roleService.findById(id));
		model.addAttribute("action", "update.do");
		return "system/roleForm";
	}

	/**
	 * 修改角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("role") @RequestBody Role role,Model model) {
		roleService.updateByPrimaryKey(role);
		return "success";
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}.do")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		List<Integer> permIdList=rolePermissionService.getPermissionIds(id);
		
		roleService.deleteRole(id, permIdList);
		return "success";
	}
	
	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("role", roleService.findById(id));
		}
	}
}
