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
<div>
	<form id="mainform" action="${ctx}/system/role/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>角色名：</td>
			<td>
			<input type="hidden" name="id" value="${id }" />
			<input id="name" name="name" type="text" value="${role.name }" class="easyui-validatebox"  data-options="width: 150,required:'required'"/>
			</td>
		</tr>
		<tr>
			<td>角色编码：</td>
			<td><input id="roleCode" name="roleCode" type="text" value="${role.roleCode }" class="easyui-validatebox"  data-options="width: 150,required:'required'" validType="length[0,30]"/></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input id="sort" name="sort" type="text" value="${role.sort}" class="easyui-numberbox" data-options="width: 150" /></td>
		</tr>
		<tr>
			<td>描述：</td>
			<td><textarea rows="3" cols="41" name="description" style="font-size: 12px;font-family: '微软雅黑'">${role.description}</textarea></td>
		</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
var action="${action}";
if(action=='update.do'){
	$('#roleCode').attr("readonly",true);
	$("#roleCode").css("background","#eee");
}

$(function(){
	$('#mainform').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){   
	    	if(data == "success"){
				if(dg != null){
					dg.datagrid("reload");
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
});

</script>
</body>
</html>