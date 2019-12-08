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
	<form id="mainform_userForm" action="${ctx}/system/user/${action}" method="post">
		<table class="formTable">
			<tr>
				<td>用户名</td>
				<td>
					<input type="hidden" name="id" value="${user.id}" />
					<input name="loginName" type="text" value="${user.loginName }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<c:if test="${action == 'create.do'}">
			<tr>
				<td>密码</td>
				<td>
					<input name="plainPassword" type="password"  class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td>
					<input name="confirmPassword" type="password" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			</c:if>
			<tr>
				<td>昵称</td>
				<td>
					<input name="name" type="text" value="${user.name }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td>
					<input name="birthday" type="text" value="${user.birthday }"  class="easyui-datebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<input type="radio" id="man" name="gender" value="1"/><label for="man">男</label>
					<input type="radio" id="woman" name="gender" value="0"/><label for="woman">女</label>
				</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
					<input name="email" type="text" value="${user.email }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>电话</td>
				<td>
					<input name="phone" type="text" value="${user.phone }" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<textarea rows="2" cols="41" name="description" style="font-size: 12px;font-family: '微软雅黑'">${user.description}</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">

	var action="${action}";
	
	//用户 添加修改区分
	if(action=='create.do'){
		$("input[name='gender'][value=1]").attr("checked",true); 
		//用户名存在验证
		$('#loginName').validatebox({    
		    required: true,    
		    validType:{
		    	length:[2,20],
		    	remote:["${ctx}system/user/checkLoginName.do","loginName"]
		    }
		});  
	}else if(action=='update.do'){
		$("input[name='loginName']").attr('readonly','readonly');
		$("input[name='loginName']").css('background','#eee')
		$("input[name='gender'][value=${user.gender}]").attr("checked",true);
	}
	
	$(function(){
		$("#mainform_userForm").form({
			onSubmit: function(){
				var isValid = $(this).form('validate');
				
				if(isValid && action=='create.do'){
					var plainPassword = $("input[name='plainPassword']").val();
					var confirmPassword = $("input[name='confirmPassword']").val();
					if(plainPassword == null || confirmPassword == null){
						$.messager.alert("请输入密码");
						return false;
					}
					if(confirmPassword != plainPassword){
						$.messager.alert("密码不一致");
						return false;
					}
				}
				
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