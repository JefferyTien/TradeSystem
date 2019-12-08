package com.reddoor.framework.dao.base;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseMapper<T> {
	
    //新增一条数据
	@InsertProvider(method = "add",type=BaseSqlProvider.class)
	@Options(useGeneratedKeys=true)
	public int add(T bean);

    //根据主键删除一条数据
	@DeleteProvider(method = "delete",type=BaseSqlProvider.class)
	public int delete(T bean);

    //根据主键获取一条数据
	@SelectProvider(method = "get",type=BaseSqlProvider.class)
	public T get(T bean);

    //修改一条数据
	@UpdateProvider(method = "update",type=BaseSqlProvider.class)
	public int update(T bean);

}