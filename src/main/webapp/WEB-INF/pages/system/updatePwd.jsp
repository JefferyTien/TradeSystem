<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="rd" uri="/WEB-INF/taglib/custom.tld" %> 
<%@include file="/WEB-INF/pages/common/common.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/lib/jquery/jquery.form.js"></script>
</head>
<body>
	<div style="padding: 5px">
	<form id="mainform"  method="post">
	<table>
		<tr>
			<td>原密码：</td>
			<td>
			<input type="hidden" name="id" value="${user.id }"/>
			<input id="oldPassword" name="oldPassword" type="password" class="easyui-validatebox" data-options="required:'true',validType:'length[6,20]'"/>
			</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input id="plainPassword" name="plainPassword" type="password" value="${user.plainPassword }" class="easyui-validatebox" data-options="required:'true',validType:'length[6,20]'" /></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-validatebox" data-options="required:'true',validType:'length[6,20]'" /></td>
		</tr>
		<tr>
			<td></td>
			<td><a id="btn" href="#" class="easyui-linkbutton">提交</a></td>
		</tr>
	</table>
	</form>
</div>
<script>
$(function(){
	$("#oldPassword").focus();
// 	$("#mainform").validate({
// 		rules: {
// 			oldPassword: {
// 				remote: "${ctx}/system/user/checkPwd.do"
// 			}
// 		},
// 		messages: {
// 			oldPassword: {
// 				remote: "原密码错误"
// 			}
// 		},
// 		submitHandler:function(form){
// 			$("#mainform").ajaxSubmit(function(data){
// 				 if(data=='success'){
// 					 parent.$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
// 						parent.d.panel('close');
// 					}
// 		   });
//         } 
// 	});
	
	$("#btn").bind("click", function(){
		var oldPassword = $("input[name='oldPassword']").val();
		$("#mainform").form("submit",{
			url:"${ctx }/system/user/updatePwd.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				
				if(isValid){
					var checkResult = false;
					// check old password
					$.ajax({
						async:false,
						type:'post',
						url:'${ctx}/system/user/checkPwd.do?'+"oldPassword="+oldPassword,
						contentType: "application/json;charset=UTF-8",
						success: function(data){
							if(data == "true"){
								checkResult = true;
							}
							else{
								$.messager.alert(data);
								checkResult = false;
							}
						}
					});
					
					if(!checkResult){
						return false;
					}
					
					var plainPassword = $("input[name='plainPassword']").val();
					var confirmPassword = $("input[name='confirmPassword']").val();
					if(plainPassword == null || confirmPassword == null){
						$.messager.alert("警告","请输入密码");
						return false;
					}
					if(confirmPassword != plainPassword){
						$.messager.alert("警告","密码不一致");
						return false;
					}
				}
				
				return isValid;
			},
			success: function(data){
				if(data == "success"){
					$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
				}
				else{
					$.messager.alert(data);
				}
			}
		});
	})
	
	
	
});
</script>
</body>
</html>