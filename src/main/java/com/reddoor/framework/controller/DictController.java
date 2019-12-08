package com.reddoor.framework.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.framework.domain.Dict;
import com.reddoor.framework.service.DictService;

@Controller
@RequestMapping("system/dict")
public class DictController extends BaseController{
	
	@Autowired
	DictService dictService;

	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "system/dictList";
	}
	
	@RequestMapping(value="all.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Dict> dictData(){
		List<Dict> allDict = dictService.findAll();
		return allDict;
	}
	
	@RequestMapping(value="dictForm.do",method = RequestMethod.GET)
	public String createForm(Model model){
		model.addAttribute("dict", new Dict());
		model.addAttribute("action", "create.do");
		return "system/dictForm";
	}
	
	@RequestMapping(value="create.do",method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Dict dict, Model model){
		dictService.insert(dict);
		return "success";
	}
	
	@RequestMapping(value = "delete/{id}.do")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		dictService.delete(id);
		return "success";
	}
	
	/**
	 * 修改字典跳转
	 */
	@RequestMapping(value = "update/{id}.do", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("dict", dictService.get(id));
		model.addAttribute("action", "update.do");
		return "system/dictForm";
	}
	
	/**
	 * 修改字典
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("dict") Dict dict,Model model) {
		dictService.update(dict);
		return "success";
	}
	
}
