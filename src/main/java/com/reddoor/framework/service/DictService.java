package com.reddoor.framework.service;

import java.util.List;

import com.reddoor.framework.domain.Dict;

public interface DictService {
	List<Dict> findAll();
	
	public Dict get(Integer id);
	
	public void insert(Dict dict);
	
	public void update(Dict dict);
	
	public void delete(Integer id);
}
