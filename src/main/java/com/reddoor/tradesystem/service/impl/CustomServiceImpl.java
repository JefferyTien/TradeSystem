package com.reddoor.tradesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.tradesystem.domain.Custom;
import com.reddoor.tradesystem.domain.CustomExample;
import com.reddoor.tradesystem.mapper.CustomMapper;
import com.reddoor.tradesystem.service.CustomService;

@Service
public class CustomServiceImpl implements CustomService {

	@Autowired
    CustomMapper customMapper;
	
	@Override
	public List<Custom> find() throws Exception {
		CustomExample example = new CustomExample();
		return customMapper.selectByExample(example);
	}

	

}
