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
<div>
	<form id="mainform_menuList" action="${ctx}/system/permission/${action}" method="post">
		<table class="formTable">
			<tr>
				<td>菜单名称：</td>
				<td>
					<input type="hidden" name="id" value="${permission.id}" />
					<input type="hidden" name="type" value="F" />
					<input id="name" type="text" name="name" value="${permission.name}" class="easyui-validatebox" 
						data-options="width:180,required:'required',validType:'length[2,20]'"/>
				</td>
			</tr>
			<tr>
				<td>菜单路径：</td>
				<td><input id="url" type="text" name="url" value="${permission.url}" class="easyui-validatebox" data-options="width:180"/></td>
			</tr>
			<tr>
				<td>菜单图标：</td>
				<td>
					<input id="icon" type="text" name="icon" value="${permission.icon}" class="easyui-validatebox" data-options="width:180"/>
				</td>
			</tr>
			<tr>
				<td>上级菜单：</td>
				<td>
					<input id="pid" name="pid" value="${permission.pid }"/>
				</td>
			</tr>
			<tr>
				<td>排序：</td>
				<td>
					<input id="sort" type="text" name="sort" value="${permission.sort }" class="easyui-numberbox" data-options="width: 180" />
				</td>
			</tr>
			<tr>
				<td>描述：</td>
				<td>
					<textarea rows="3" cols="41" name="description">${permission.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	var action = "${action}";
	if(action == "update"){
		$("#pid").val(parentPermId);
	}
	
	$("#pid").combotree({
		width: 180,
		method: 'GET',
		url: '${ctx}/system/permission/menu/all.do',
		idField: 'id',
		textField: 'name',
		parentField: 'pid',
		iconCls: 'icon',
		animate: true
	});
	
	$("#mainform_menuList").form({
		onSubmit: function(){
			var isValid = $(this).form('validate');
			return isValid;
		},
		success: function(data){
			if(data == "success"){
				if(tg != null){
					tg.treegrid("reload");
				}
				if(d != null){
					d.panel("close");
				}
			}
			else{
				$.messager.alert(data);
			}
		}
	});
</script>

</body>
</html>