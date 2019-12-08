package com.reddoor.tradesystem.controller.order;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.tradesystem.domain.COrder;
import com.reddoor.tradesystem.domain.customize.CustomResult;
import com.reddoor.tradesystem.domain.customize.DataGridResult;
import com.reddoor.tradesystem.domain.vo.COrderVO;
import com.reddoor.tradesystem.service.order.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {

//	@Autowired
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "order/order_list";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public DataGridResult getList(Integer page, Integer rows, COrderVO cOrder) throws Exception {
		DataGridResult result = orderService.getList(page, rows, cOrder);
		return result;
		
//		orderService.test();
//		return null;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "order/order_add";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid COrder cOrder, BindingResult bindingResult) throws Exception{
		CustomResult result;
		
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(orderService.get(cOrder.getOrderId()) != null){
			result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
		}
		else{
			result = orderService.insert(cOrder);
		}
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception{
		CustomResult result = orderService.deleteBatch(ids);
		return result;
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid COrder cOrder, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return orderService.updateAll(cOrder);
	}
}
