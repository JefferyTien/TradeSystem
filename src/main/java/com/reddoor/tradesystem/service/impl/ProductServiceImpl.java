package com.reddoor.tradesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.tradesystem.domain.Product;
import com.reddoor.tradesystem.domain.ProductExample;
import com.reddoor.tradesystem.mapper.ProductMapper;
import com.reddoor.tradesystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    ProductMapper productMapper;
	
	@Override
	public List<Product> find() throws Exception {
		ProductExample example = new ProductExample();
		return productMapper.selectByExample(example);
	}

}
