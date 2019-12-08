<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="rd" uri="/WEB-INF/taglib/custom.tld" %>    
<%@include file="/WEB-INF/pages/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div id="tb_userList" style="padding:5px;height:auto">
		<div>
			<form id="searchFrom" action="">
       	        <input type="text" name="filter_LIKES_loginName" class="easyui-textbox" data-options="width:150,prompt: '昵称'"/>
       	        <input type="text" name="filter_LIKES_phone" class="easyui-textbox" data-options="width:150,prompt: '电话'"/>
       	        <input type="text" name="filter_GTD_createDate" class="easyui-datebox" data-options="width:150,prompt: '开始日期'"/>
       	       - <input type="text" name="filter_LTD_createDate" class="easyui-datebox" data-options="width:150,prompt: '结束日期'"/>
		        <span class="dialog-tool-separator"></span>
		        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
			</form>
			
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add();">添加</a>
			<span class="dialog-tool-separator" style="float:none;"></span>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="del();">删除</a>
			<span class="dialog-tool-separator" style="float:none;"></span>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="edit();">修改</a>
			<span class="dialog-tool-separator" style="float:none;"></span>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-hamburg-suppliers" onclick="userRole();">用户角色</a>
		</div>
	</div>
	
	<table id="dg_userList"></table>
	
	<div id="dlg_userList"></div>
	
	<div id="dlgUserRole_userList"></div>
	
	<script type="text/javascript">
		var d;
		var dg;
		var dlgUserRole;
		
		$(function(){
			dg = $("#dg_userList").datagrid({
				method: 'get',
				url: '${ctx}/system/user/all.do',
				fit: true,
				fitColumns:true,
				border : false,
				striped:true,
				idField : 'id',
				pagination:true,
				rownumbers:true,
				pageNumber:1,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50 ],
				singleSelect:true,
				columns: [[
				       {field:'id', title:'id', hidden:true},
				       {field:'loginName', title:'账号', sortable: true, width:100},
				       {field:'name', title:'昵称', sortable: true, width:100},
				       {field:'gender', title:'性别', sortable: true, width:100,
				    	   formatter : function(value, row, index) {
				       			return value==1?'男':'女';
				        	}
				       },
				       
				       {field:'email', title:'email', sortable: true, width:100},
				       {field:'phone', title:'电话', sortable: true},
				       {field:'loginCount', title:'登录次数', sortable: true, width:100},
				       {field:'previousVisit', title:'上一次登录', sortable: true, width:100},
				       
				]],
				toolbar: '#tb_userList'
			});
		});
		
		
		function add(){
			d = $("#dlg_userList").dialog({
				title: '添加',
				width: 450,
				height: 380,
				closed: false,
				cache: false,
				maximizable: true,
				resizable: true,
				modal: true,
				href: '${ctx}/system/user/userForm.do',
				buttons: [{
					text: '确认',
					handler: function(){
						$("#mainform_userForm").submit();
					}
				},{
					text: '取消',
					handler: function(){
						d.panel("close");
					}
				}]
			});
		}
		
		function del(){
			var row = $("#dg_userList").datagrid("getSelected");
			if(row == null){
				return;
			}
			$.messager.confirm("确认","确定要删除么?",function(r){
				if(r){
					$.ajax({
						type:'post',
						url:'${ctx}/system/user/delete/'+row.id+'.do',
						success: function(data){
							if(data == "success"){
								$("#dg_userList").datagrid("reload");
								$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
							}
							else{
								$.messager.alert(data);
							}
						}
					});
				}
			});
		}
		
		function edit(){
			var row = $("#dg_userList").datagrid("getSelected");
			if(row == null){
				return;
			}
			d = $("#dlg_userList").dialog({
				title: '修改',
				width: 450,
				height: 380,
				href:'${ctx}/system/user/update/'+row.id+'.do',
				maximizable:true,
			    modal:true,
			    buttons:[{
					text:'确认',
					handler:function(){
						$("#mainform_userForm").submit();
					}
				},{
					text:'取消',
					handler:function(){
						d.panel('close');
					}
				}]
			});
		}
		
		function userRole(){
			var row = $("#dg_userList").datagrid("getSelected");
			if(row==null){
				return;
			}
			dlgUserRole = $("#dlgUserRole_userList").dialog({
				title:"用户角色",
				width:450,
				height:380,
				href:"${ctx}/system/user/"+row.id+"/userRole.do",
				maximizable:true,
				modal:true,
				buttons:[{
					text:"确认",
					handler:function(){
						saveUserRole();
						dlgUserRole.panel("close");
					}
				},{
					text:"取消",
					handler:function(){
						dlgUserRole.panel("close");
					}
				}]
				
			});
		}
		
		function cx(){
// 			var obj=$("#searchFrom").serializeObject();
// 			var obj=$("#searchFrom").serialize();
// 			var jsonStr = form2JsonString("searchFrom");
            var obj = $("#searchFrom").serializeFormToJson();
			alert(obj);
			dg.datagrid('load', obj); 
		}
	</script>
</body>
</html>