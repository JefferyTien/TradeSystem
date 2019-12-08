<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="rd" uri="/WEB-INF/taglib/custom.tld"%>
<%@include file="/WEB-INF/pages/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

</head>
<body>
	<table class="easyui-datagrid" id="orderList" title="订单列表"
			data-options="fit:true,border:false,singleSelect:false,collapsible:true,pagination:true,rownumbers:true,url:'${ctx}/order/list.do',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_order">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'orderId',align:'center',width:100">订单编号</th>
				<th data-options="field:'custom',align:'center',width:100,formatter:formatCustom">订购客户</th>
				<th data-options="field:'product',align:'center',width:100,formatter:formatProduct">订购产品</th>
				<th data-options="field:'quantity',align:'center',width:100">订购数量</th>
				<th data-options="field:'unitPrice',width:70,align:'center'">税前单价</th>
				<th data-options="field:'unit',width:70,align:'center'">单位</th>
				<th data-options="field:'status',width:60,align:'center',formatter:TOOL.formatOrderStatus">状态</th>
				<th data-options="field:'orderDate',width:130,align:'center',formatter:TOOL.formatDateTime">订购日期</th>
				<th data-options="field:'requestDate',width:130,align:'center',formatter:TOOL.formatDateTime">要求日期</th>
				<th data-options="field:'note',width:100,align:'center', formatter:formatOrderNote">订单要求</th>
				<th data-options="field:'image',width:100,align:'center', formatter:formatImg">相关图片</th>
				<th data-options="field:'file',width:180,align:'center', formatter:formatFile">订单附件</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar_order" style="height: 22px; padding: 3px 11px; background: #fafafa;">
		<rd:shiroHasPerm permission="order:add">
			<div style="float:left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="order_add()" >新增</a>
			</div>
		</rd:shiroHasPerm>
		<rd:shiroHasPerm permission="order:edit">
			<div style="float:left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="order_edit()" >编辑</a>
			</div>
		</rd:shiroHasPerm>
		<rd:shiroHasPerm permission="order:delete">
			<div style="float:left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="order_delete()" >删除</a>
			</div>
		</rd:shiroHasPerm>
		
		<div class="datagrid-btn-separator"></div>
		
		<div style="float:left;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="order_reload()" >刷新</a>
		</div>
		
		<div id="search_order" style="float:right;">
			<input id="search_text_order" class="easyui-searchbox" 
				data-options="searcher:doSearch_order,prompt:'请输入...',menu:'#menu_order'"
				style="width:250px;vertical-align: middle;" />
			<div id="menu_order" style="width:120px;">
				<div data-options="name:'orderId'">订单编号</div> 
				<div data-options="name:'orderCustom'">客户名称</div>
				<div data-options="name:'orderProduct'">产品名称</div> 
			</div>
		</div>
	</div>
	
	<div id="orderAddWindow" class="easyui-window" title="添加订单" 
		data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'${ctx}/order/add.do'" style="width:65%;height:80%;padding:10px;">
	</div>
	
	<script type="text/javascript">
	function doSearch_order(value,name){
		
	}
	//格式化客户信息
	function formatCustom(value, row, index){ 
		if(value !=null && value != ''){
			var row = onOrderClickRow(index); 
			return "<a href=javascript:openOrderCustom("+index+")>"+value.customName+"</a>";
		}else{
			return "无";
		}
	};  
	
	//根据index拿到该行值
	function onOrderClickRow(index) {
		var rows = $('#orderList').datagrid('getRows');
		return rows[index];
	}
	
	//格式化产品信息
	function  formatProduct(value, row, index){ 
		if(value !=null && value != ''){
			return "<a href=javascript:openOrderProduct("+index+")>"+value.productName+"</a>";
		}else{
			return "无";
		}
	};
	
	//格式化订单要求
	function formatOrderNote(value, row, index){ 
		if(value !=null && value != ''){
			return "<a href=javascript:openOrderNote("+index+")>"+"订单要求"+"</a>";
		}else{
			return "无";
		}
	}
	
	
	function order_add(){
		$.get("order/add_judge.do", '', function(data){
			if(data.msg != null){
				$.messager.alert('提示', data.msg);
			}
			else{
				$("#orderAddWindow").window("open");
			}
		});
	}
	
	function order_edit(){
		
	}
	
	function getOrderSelectionsIds(){
		var orderList = $("#orderList");
		var sels = orderList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].orderId);
		}
		ids = ids.join(",");
		
		return ids;
	}
	
	
	function order_delete(){
		$.get("order/delete_judge.do", '', function(data){
			if(data.msg != null){
				$.messager.alert('提示', data.msg);
			}
			else{
				var ids = getOrderSelectionsIds();
				if(ids.length == 0){
					$.messager.alert('提示','未选中订单!');
					return;
				}
				$.messager.confirm('确认', '确定删除ID为' + ids +' 的订单么?',function(r){
					if(r){
						var params = {"ids":ids};
						$.post("order/delete_batch.do",params,function(data){
							if(data.status == 200){
								$.messager.alert('提示','订单删除成功',undefined,function(){
									$("#orderList").datagrid("reload");
								});
							}
						});
					}
				})
				
			}
		});
	}
	
    function order_reload(){
    	$("#orderList").datagrid("reload");
    }
	</script>
</body>

</html>