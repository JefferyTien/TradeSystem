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
	<div id="tb_menuList">
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add();">添加</a>
		<span class="dialog-tool-separator" style="float:none;"></span>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="del();">删除</a>
		<span class="dialog-tool-separator" style="float:none;"></span>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="edit();">修改</a>
	</div>
	<table id="tg_menuList"></table>
	
	<div id="dlg_menuList"></div>
	
	<script type="text/javascript">
		var tg;
		var d;
		var parentPermId;
	
		$(function(){
			tg=$("#tg_menuList").treegrid({
				method:'get',
				url:'${ctx}/system/permission/menu/all.do',
				fitColumns:true,
				border : false,
				idField:'id',
				treeField:'name',
				parentField : 'pid',
				iconCls: 'icon',
				animate:true,
				rownumbers:true,
				singleSelect:true,
				striped:true,
				columns:[[
					{field:'id',title:'id',hidden:true,width:100},
					{field:'name',title:'名称',iconCls:'icon',width:100},
					{field:'url',title:'资源路径',width:100},
					{field:'sort',title:'排序',width:100},
					{field:'description',title:'描述',width:100}
				]],
				toolbar:'#tb_menuList'          
			});
		});
		
		function add(){
			var row = tg.treegrid('getSelected');
			if(row){
				parentPermId=row.id;
			}
			d = $("#dlg_menuList").dialog({
					title: '添加',
					width: 450,
					height: 320,
					closed: false,
					cache: false,
					maximizable: true,
					resizable: true,
					modal: true,
					href: '${ctx}/system/permission/menu/create.do',
					buttons: [{
						text: '确认',
						handler: function(){
							$("#mainform_menuList").submit();
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
			var row = $("#tg_menuList").treegrid("getSelected");
			if(row == null){
				return;
			}
			$.messager.confirm('确认','确定要删除么?',function(r){
				if(r){
					$.ajax({
						type: 'get',
						url: '${ctx}/system/permission/delete/'+row.id+'.do',
						success: function(data){
							if(data == "success"){
								tg.treegrid('reload');
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
			var row = tg.treegrid('getSelected');
			if(row == null){
				return;
			}
			//父级权限
			parentPermId=row.pid;
			d = $("#dlg_menuList").dialog({
				title: '修改',
				width: 450,
				height: 320,
				href:'${ctx}/system/permission/menu/update/'+row.id+'.do',
				maximizable:true,
			    modal:true,
			    buttons:[{
					text:'确认',
					handler:function(){
						$("#mainform_menuList").submit();
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