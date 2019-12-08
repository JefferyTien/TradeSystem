package com.reddoor.tradesystem.service.order;

import java.util.List;

import com.reddoor.tradesystem.domain.COrder;
import com.reddoor.tradesystem.domain.customize.CustomResult;
import com.reddoor.tradesystem.domain.customize.DataGridResult;
import com.reddoor.tradesystem.domain.vo.COrderVO;


public interface OrderService {
	
	List<COrderVO> find() throws Exception;
	
	DataGridResult getList(int page, int rows, COrderVO cOrder) throws Exception;

	COrderVO get(String string) throws Exception;
	
	CustomResult delete(String string) throws Exception;

	CustomResult deleteBatch(String[] ids) throws Exception;

	CustomResult insert(COrder cOrder) throws Exception;

	//更新部分字段，用的是updateSelective判断非空的字段进行更新
    CustomResult update(COrder cOrder) throws Exception;
    
    //更新全部字段，不判断非空，直接进行更新
    CustomResult updateAll(COrder cOrder) throws Exception;
    
    CustomResult updateNote(COrder cOrder) throws Exception;

    CustomResult changeStatus(String[] ids) throws Exception;

    //根据订单id查找订单信息
    DataGridResult searchOrderByOrderId(int page, int rows, String orderId) throws Exception;
	
    //根据客户名称查找订单信息
	DataGridResult searchOrderByCustomName(int page, int rows,
			String customName) throws Exception;
	
	//根据产品名称查找订单信息
	DataGridResult searchOrderByProductName(int page, int rows,
			String productName) throws Exception;
	
	
	void test() throws Exception;
}
