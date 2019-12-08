package com.reddoor.framework.mapper;

import java.util.List;

import com.reddoor.framework.domain.Dict;

public interface DictMapper {
	List<Dict> findAll();
	
	Dict get(Integer id);
	
	void insert(Dict dict);
	
	void update(Dict dict);
	
	void delete(Integer id);
}
