package com.reddoor.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.Dict;
import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.mapper.DictMapper;
import com.reddoor.framework.service.DictService;

@Service("DictServiceImpl")
public class DictServiceImpl implements DictService{
	
	@Autowired
	DictMapper dictMapper;
	
	@Override
	public List<Dict> findAll() {
		return dictMapper.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public void insert(Dict dict) {
		dictMapper.insert(dict);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void update(Dict dict) {
		dictMapper.update(dict);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(Integer id) {
		dictMapper.delete(id);
	}

	@Override
	public Dict get(Integer id) {
		return dictMapper.get(id);
	}

}
