package com.reddoor.tradesystem.service;

import java.util.List;

import com.reddoor.tradesystem.domain.Custom;

public interface CustomService {
	
	List<Custom> find() throws Exception;
}
