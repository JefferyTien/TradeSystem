<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div>
	<form id="mainform_dictForm" action="system/dict/${action}" method="post">
		<table class="formTable">
			<tr>
				<td>代码</td>
				<td>
					<input type="hidden" name="id" value="${dict.id}" />
					<input name="code" type="text" value="${dict.code }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>标签</td>
				<td>
					<input name="label" type="text" value="${dict.label }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>值</td>
				<td>
					<input name="value" type="text" value="${dict.value }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
					<input name="type" type="text" value="${dict.type}" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<textarea rows="2" cols="41" name="description" style="font-size: 12px;font-family: '微软雅黑'">${dict.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$("#mainform_dictForm").form({
			onSubmit: function(){
				var isValid = $(this).form('validate');
				return isValid;
			},
			success: function(data){
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