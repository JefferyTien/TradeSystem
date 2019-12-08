package com.reddoor.tradesystem.service.impl;

import java.util.List;

import com.reddoor.tradesystem.domain.COrder;
import com.reddoor.tradesystem.domain.COrderExample;
import com.reddoor.tradesystem.domain.customize.CustomResult;
import com.reddoor.tradesystem.domain.vo.COrderVO;
import com.reddoor.tradesystem.service.order.OrderService;
import com.reddoor.tradesystem.mapper.COrderMapper;
import com.reddoor.tradesystem.domain.customize.DataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    COrderMapper cOrderMapper;
	
	@Override
	public List<COrderVO> find() throws Exception{
		COrderExample example = new COrderExample();
		return cOrderMapper.selectByExample(example);
	}
	
	@Override
	public DataGridResult getList(int page, int rows, COrderVO cOrder) throws Exception{
		
		//分页处理
		PageHelper.startPage(page, rows);
		List<COrderVO> list = cOrderMapper.find(cOrder);
		//创建一个返回值对象
		DataGridResult result = new DataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<COrderVO> pageInfo = new PageInfo<COrderVO>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public COrderVO get(String id) throws Exception{
		return cOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public CustomResult delete(String id) throws Exception{
		int i = cOrderMapper.deleteByPrimaryKey(id);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult deleteBatch(String[] ids) throws Exception{
		int i = cOrderMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult insert(COrder cOrder) throws Exception{
		int i = cOrderMapper.insert(cOrder);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增订单失败");
		}
	}

	@Override
	public CustomResult update(COrder cOrder) throws Exception{
		int i = cOrderMapper.updateByPrimaryKeySelective(cOrder);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}

	@Override
	public CustomResult updateAll(COrder cOrder) throws Exception{
		int i = cOrderMapper.updateByPrimaryKey(cOrder);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单失败");
		}
	}

	@Override
	public CustomResult updateNote(COrder cOrder) throws Exception{
		int i = cOrderMapper.updateNote(cOrder);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改订单要求失败");
		}
	}
	
	@Override
	public CustomResult changeStatus(String[] ids) throws Exception{
		int i = cOrderMapper.changeStatus(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}
	
	@Override
	public DataGridResult searchOrderByOrderId(int page, int rows, String orderId) throws Exception{
		//分页处理
		PageHelper.startPage(page, rows);
		List<COrderVO> list = cOrderMapper.searchOrderByOrderId(orderId);
		//创建一个返回值对象
		DataGridResult result = new DataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public DataGridResult searchOrderByCustomName(int page, int rows, String custonName)
			throws Exception{
		//分页处理
		PageHelper.startPage(page, rows);
		List<COrderVO> list = cOrderMapper.searchOrderByCustomName(custonName);
		//创建一个返回值对象
		DataGridResult result = new DataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public DataGridResult searchOrderByProductName(int page, int rows, String productName)
			throws Exception{
		//分页处理
		PageHelper.startPage(page, rows);
		List<COrderVO> list = cOrderMapper.searchOrderByProductName(productName);
		//创建一个返回值对象
		DataGridResult result = new DataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<COrderVO> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	
	public void test(){
		System.out.println("fuck");
	}
}
