package com.reddoor.tradesystem.service;

import java.util.List;

import com.reddoor.tradesystem.domain.Product;

public interface ProductService {
	List<Product> find() throws Exception;
}
