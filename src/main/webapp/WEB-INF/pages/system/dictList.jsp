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
	<div id="tb_dictList" style="padding:5px;height:auto">
		<div>
			<form id="searchFrom" action="">
       	        <input type="text" name="filter_LIKES_label" class="easyui-textbox" data-options="width:150,prompt: '标签'"/>
       	        <input type="text" name="filter_LIKES_value" class="easyui-textbox" data-options="width:150,prompt: '值'"/>
       	        <input type="text" name="filter_LIKES_type" class="easyui-textbox" data-options="width:150,prompt: '类型'"/>
		        <span class="dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
			</form>
			
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add();">添加</a>
			<span class="dialog-tool-separator" style="float:none;"></span>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="del();">删除</a>
			<span class="dialog-tool-separator" style="float:none;"></span>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="edit();">修改</a>
		</div>
	</div>
	
	<table id="dg_dictList"></table>
	
	<div id="dlg_dictList"></div>
	
	<script type="text/javascript">
		var d;
		var dg;
		
		$(function(){
			dg = $("#dg_dictList").datagrid({
				method: 'get',
				url: '${ctx}/system/dict/all.do',
				fit: true,
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
				       {field:'code', title:'代码', sortable: true, width:100},
				       {field:'label', title:'标签', sortable: true, width:100},
				       {field:'value', title:'值', sortable: true, width:100},
				       {field:'type', title:'类型', sortable: true, width:100},
				       {field:'sort', title:'排序', sortable: true},
				       {field:'description', title:'描述', sortable: true, width:100},
				       
				]],
				toolbar: '#tb_dictList'
			});
		});
		
		
		function cx(){
			
		}
		
		function add(){
			d = $("#dlg_dictList").dialog({
				title: '添加',
				width: 450,
				height: 320,
				closed: false,
				cache: false,
				maximizable: true,
				resizable: true,
				modal: true,
				href: '${ctx}/system/dict/dictForm.do',
				buttons: [{
					text: '确认',
					handler: function(){
						$("#mainform_dictForm").submit();
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
			var row = $("#dg_dictList").datagrid("getSelected");
			if(row == null){
				return;
			}
			$.messager.confirm("确认","确定要删除么?",function(r){
				if(r){
					$.ajax({
						type:'post',
						url:'${ctx}/system/dict/delete/'+row.id+'.do',
						success: function(data){
							if(data == "success"){
								$("#dg").datagrid("reload");
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
			var row = $("#dg_dictList").datagrid("getSelected");
			if(row == null){
				return;
			}
			d = $("#dlg_dictList").dialog({
				title: '修改',
				width: 450,
				height: 320,
				href:'${ctx}/system/dict/update/'+row.id+'.do',
				maximizable:true,
			    modal:true,
			    buttons:[{
					text:'确认',
					handler:function(){
						$("#mainform_dictList").submit();
					}
				},{
					text:'取消',
					handler:function(){
						d.panel('close');
					}
				}]
			});
		}
	</script>
</body>
</html>