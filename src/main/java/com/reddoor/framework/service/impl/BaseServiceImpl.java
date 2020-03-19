package com.reddoor.framework.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reddoor.framework.dao.BaseDao;
import com.reddoor.framework.dao.Page;
import com.reddoor.framework.dao.PropertyFilter;
import com.reddoor.framework.domain.QueryExample;
import com.reddoor.framework.service.BaseService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {
 
//    protected final Logger logger = LoggerFactory.getLogger(getClass());
 
    public abstract Mapper<T> getMapper();
    
    @Autowired
    public BaseDao<T> baseDao;
 
    @Override
    @Transactional(rollbackFor = Exception.class) //事务回滚
    public Integer add(T t) {
        return getMapper().insertSelective(t); //封装单表操作方法
    }
 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(ID id) {
        return getMapper().deleteByPrimaryKey(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(T t){
    	 return getMapper().delete(t);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateByPrimaryKey(T t){
    	return getMapper().updateByPrimaryKey(t);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(){
    	return getMapper().selectAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public T findOne(T t){
    	return getMapper().selectOne(t);
    }
    
    @Override
    @Transactional(readOnly = true)
    public T findById(ID id){
    	return getMapper().selectByPrimaryKey(id);
    }
    
    @Transactional(readOnly = true)
	public Page<T> search(final Page<T> page, final List<PropertyFilter> filters) {
    	Example example = baseDao.buildExampleByPropertyFilter(filters, this.getTClass());
    	if(page.isOrderBySetted()){
    		if("asc".equals(page.getOrder())){
    			example.orderBy(page.getOrderBy()).asc();
    		}
    		else{
    			example.orderBy(page.getOrderBy()).desc();
    		}
    	}
    	
    	//分页处理
    	PageHelper.startPage(page.getPageNo(), page.getPageSize(), true);
    	List<T> resultList =  getMapper().selectByExample(example);
    	PageInfo<T> pageInfo = new PageInfo<T>(resultList);
    	
    	page.setResult(resultList);
    	page.setTotalCount(pageInfo.getTotal());
    	return page;
	}
    
    /**
     * 保存一个list实体，null的属性不会保存，会使用数据库默认值
     *
     * @param list
     * @return
     */
    public Integer batchAdd(List<T> list){
    	return null;
    }
 
    /**
     * 根据主键更新属性不为null的值
     *
     * @param list
     * @return
     */
    public Integer batchUpdateByPrimaryKey(List<T> list){
    	return null;
    }
 
    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param t
     * @return
     */
    public List<T> find(T t){
    	return null;
    }
 
    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @return
     */
    public Integer updateByExampleSelective(QueryExample<T> queryExample){
    	return null;
    }
 
    /**
     * 根据实体中的属性值进行分页查询，查询条件使用等号
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<T> findPage(T t, Integer pageNum, Integer pageSize){
    	return null;
    }
 
    public List<T> findByExample(Example example){
    	return null;
    }
 
    /**
     * 根据query条件更新record数据
     *
     * @param record 要更新的数据
     * @param query  查询条件
     * @return
     */
    public Integer updateByExampleSelective(T record, Example query){
    	return null;
    }
 
    /**
     * 根据query条件更新record数据
     *
     * @param record 要更新的数据
     * @param query  查询条件
     * @return
     */
    public Integer updateByExampleSelective(T record, T query){
    	return null;
    }
 
    /**
     * 查询数量
     *
     * @param record
     * @return
     */
    public Integer findCount(T record){
    	return null;
    }
 
    /**
     * 查询数量
     *
     * @param query
     * @return
     */
    public Integer findCountByExample(Example query){
    	return null;
    }
    
    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
}