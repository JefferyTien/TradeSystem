package com.reddoor.tradesystem.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.tradesystem.domain.Product;
import com.reddoor.tradesystem.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Product> getData() throws Exception{
		return productService.find();
	}
}
