<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<table id="dg_userRoleList"></table>
	<script type="text/javascript">
	var ur_dg;
	ur_dg=$("#dg_userRoleList").datagrid({
		method: 'get',
		url:'${ctx}/system/role/all.do',
		fit:true,
		fitColumns : true,
		border : false,
		idField : 'id',
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		striped:true,
		columns:[[
			{field:'ck', checkbox:true},
			{field:'id',title:'id',hidden:true,sortable:true,width:100},
			{field:'name',title:'角色名称',sortable:true,width:100},
	        {field:'roleCode',title:'角色编码',sortable:true,width:100},
	        {field:'description',title:'描述',sortable:true,width:100,tooltip: true}
		]],
		onLoadSuccess:function(){
			$.ajax({
				async:false,
				type:'get',
				url:'${ctx}/system/user/${userId}/role.do',
				success:function(data){
					if(data){
						for(var i=0,j=data.length;i<j;i++){
							ur_dg.datagrid("selectRecord", data[i]);
						}
					}
				}
			});
		}
	});
	
	function saveUserRole(){
		var newRoleList=[];
		var data=ur_dg.datagrid("getSelections");
		for(var i=0;i<data.length;i++){
			newRoleList.push(data[i].id);
		}
		$.ajax({
			async:false,
			type:'post',
			data:JSON.stringify(newRoleList),
			contentType:'application/json;charset=utf-8',
			url:'${ctx}/system/user/${userId}/updateRole.do',
			success:function(data){
				if(data=="success"){
					$.messager.show({title : "提示",msg: "操作成功！", position: "bottomRight"});
				}
				else{
					$.messager.alert(data);
				}
			}
		});
	}

	</script>
</body>
</html>