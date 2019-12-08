package com.reddoor.framework.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.reddoor.framework.dao.PropertyFilter.MatchType;

@Service
public class BaseDao<T> {
	
	public Example buildExampleByPropertyFilter(final List<PropertyFilter> filters, Class<T> cls) {
		Example example = new Example(cls);
		boolean firstOne = true;
		for (PropertyFilter filter : filters) {
			Criteria criteria = example.createCriteria();
			if (!filter.hasMultiProperties()) { //只有一个属性需要比较的情况.
				criteria = buildCriteria(criteria, filter.getPropertyName(), filter.getMatchValue(), filter
						.getMatchType());
			} else {//包含多个属性需要比较的情况,进行or处理.
				for (String param : filter.getPropertyNames()) {
					criteria = buildCriteria(criteria, param, filter.getMatchValue(), filter.getMatchType());
				}
			}
			if(!firstOne){
				example.and(criteria);
			}
			else{
				firstOne = false;
			}
			
		}
		return example;
	}
	
	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	public Criteria buildCriteria(Criteria criteria, final String propertyName, final Object propertyValue, final MatchType matchType) {
		//根据MatchType构造criteria
		switch (matchType) {
			case EQ:
				criteria.orEqualTo(propertyName, propertyValue);
				break;
			case LIKE:
				criteria.orLike(propertyName, "%"+(String) propertyValue+"%");
				break;
			case LE:
				criteria.orLessThanOrEqualTo(propertyName, propertyValue);
				break;
			case LT:
				criteria.orLessThan(propertyName, propertyValue);
				break;
			case GE:
				criteria.orGreaterThanOrEqualTo(propertyName, propertyValue);
				break;
			case GT:
				criteria.orGreaterThan(propertyName, propertyValue);
		}
		return criteria;
	}
}
