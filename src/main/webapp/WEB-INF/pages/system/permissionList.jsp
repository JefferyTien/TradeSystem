<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="rd" uri="/WEB-INF/taglib/custom.tld" %>    
<%@include file="/WEB-INF/pages/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body style="font-family: '微软雅黑'">
	<div class="easyui-layout" data-options="fit: true">
		<div data-options="region:'west',title:'菜单列表',split:true,border:false," style="width:300px;">
			<table id="menuDg"></table>
		</div>
		<div data-options="region:'center',title:'权限列表',split:true,border:false," style="padding:5px;background:#eee;">
			<div id="tg_tb" style="padding:5px;height:auto">
			    <div>
			    	<shiro:hasPermission name="sys:perm:add">
			    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add()">添加</a>
			    	<span class="toolbar-item dialog-tool-separator"></span>
			    	</shiro:hasPermission>
			        <shiro:hasPermission name="sys:perm:delete">
			        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="del()">删除</a>
			        <span class="toolbar-item dialog-tool-separator"></span>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="sys:perm:update">
			        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="upd()">修改</a>
			        <span class="toolbar-item dialog-tool-separator"></span>
			        </shiro:hasPermission>
			    </div>
			</div>
	    	<table id="dg"></table>
		</div>
		<div id="dlg"></div> 
		<div id="icon_dlg"></div>  
	</div>

	
	<script type="text/javascript">
	var dg;
	var d;
	var menuDg;
	var menuId=0;
	var parentPermId;
	$(function(){   
		menuDg=$('#menuDg').treegrid({  
		method: "get",
	    url:'${ctx}/system/permission/menu/all.do', 
		fitColumns : true,
		border : false,
		idField : 'id',
		treeField:'name',
		parentField : 'pid',
		iconCls: 'icon',
		animate:true, 
		rownumbers:true,
		singleSelect:true,
		striped:true,
	    columns:[[    
	        {field:'id',title:'id',hidden:true},    
	        {field:'name',title:'名称',width:100}
	    ]],
	    onClickRow:function(rowData){
	    	menuId=rowData.id;
	    	parentPermId=rowData.id;
	    	dg.datagrid('reload',{pid:menuId});
	    }
		});
		
		dg=$('#dg').datagrid({   
		method: "get",
		url:'${ctx}/system/permission/ope/all.do',
		fitColumns : true,
		border : false,
		idField : 'id',
		treeField:'name',
		parentField : 'pid',
		iconCls: 'icon',
		animate:true, 
		rownumbers:true,
		singleSelect:true,
		striped:true,
	    columns:[[    
	        {field:'id',title:'id',hidden:true,width:100},    
	        {field:'name',title:'名称',width:100},
	        {field:'url',title:'访问路径',width:100},
	        {field:'permCode',title:'权限编码',width:100},
	        {field:'sort',title:'排序'},
	        {field:'description',title:'描述',width:100}
	    ]],
	    toolbar:'#tg_tb',
		});
		
	});

	//弹窗增加
	function add() {
		d=$('#dlg').dialog({    
		    title: '添加权限',    
		    width: 450,    
		    height: 300,    
		    closed: false,    
		    cache: false,
		    maximizable:true,
		    resizable:true,
		    href:'${ctx}/system/permission/create.do',
		    modal: true,
		    buttons:[{
				text:'确认',
				handler:function(){
					$("#mainform").submit();
				}
			},{
				text:'取消',
				handler:function(){
						d.panel('close');
					}
			}]
		});
	}
	
	//删除
	function del(){
		var row = dg.datagrid('getSelected');
		if(row == null) return;
		parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
			if (data){
				$.ajax({
					type:'get',
					url:"${ctx}/system/permission/delete/"+row.id+".do",
					success: function(data){
						if(data == "success"){
							dg.datagrid('reload');
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

	//修改
	function upd(){
		var row = dg.datagrid('getSelected');
		if(row==null) return;
		d=$("#dlg").dialog({   
		    title: '修改权限',    
		    width: 450,    
		    height: 300,    
		    href:'${ctx}/system/permission/update/'+row.id+".do",
		    maximizable:true,
		    modal:true,
		    buttons:[{
				text:'确认',
				handler:function(){
					$("#mainform").submit();
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