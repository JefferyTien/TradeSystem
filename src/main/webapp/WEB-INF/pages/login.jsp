<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css"/>
</head>
<body>
	<div class="main">
		<div class="header hide">管理系统</div>
		<div class="content">
			<div class="title hide">管理系统登录</div>
			<form name="login" action="#" method="post">
				<fieldset>
					<!-- username -->
					<div>
						<div class="input">
							<input class="input_all name" name="name" id="username" placeholder="用户名" type="text"
								onFocus="this.className='input_all name_now';"
								onBlur="this.className='input_all name';" maxLength="24" />
						</div>
						<div id="username_span" style="display:none;padding-bottom:7px;"
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="userspan"></span>
						</div>
					</div>
					
					<!-- password -->
					<div>
						<div class="input">
							<input class="input_all password" name="password" id="password" type="password"
								   placeholder="密码" onFocus="this.className='input_all password_now';"
								   onBlur="this.className='input_all password'" maxLength="24" />
						</div>
						<div id="password_span"style="display:none;margin:0 0 0 0;padding:0 0 0 0;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="passwordspan"></span>
						</div>
					
						<div style="margin-bottom:12px">
							<div id="randiv" style="display:none;margin-left:98px;">
							
								验证码：<input id="randomcode" name="randomcode" size="8" /> <img
									id="randomcode_img" src="${ctx}/validatecode.jsp" alt=""
									width="56" height="20" align='absMiddle' /> <a
									href=javascript:randomcode_refresh()>刷新</a>
							</div>
							<div style="margin-left:98px;">
								<span id="randomcode_span"></span>
							</div>
						</div>
					</div>
					
					<!-- REMEMBERME -->					
					<div class="checkbox">
						<input type="checkbox" name="rememberme" id="rememberme" checked="checked"/>
						<label for="rememberme">
							<span>记住密码</span>
						</label>
						<span id="errorspan" style="margin-left:88px;"></span>
					</div>
					
					<div>
						<a href="#" id="login" class="button hide">登录</a>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/static/js/login/placeholder.js"></script>
	<script type="text/javascript">
		$("#login").click(
				function(){
					var uname = $("#username");
					var pwd = $("#password");
					var display = $("#randiv").css("display");
					var rcode = $("#randomcode");
					var remember = $("#rememberme");
					
					if(display == "none"){
						if($.trim(uname.val())==""){
							$("#username_span").css("display","block");
							$("#passwordspan").html("");
							$("#userspan").html("<font color='red'>用户名不能为空!</font>");
							uname.focus();
						}
						else if($.trim(pwd.val())==""){
							$('#username_span').css('display','none');
							$('#password_span').css('display','block');
							$("#userspan").html("");
							$("#passwordspan").html("<font color='red'>密码不能为空！</font>");
							pwd.focus();
						}
						else{
							$('#password_span').css('display','none');
							$("#userspan").html("");
							$("#passwordspan").html("");
							$.ajax({
								url: '${ctx}/ajaxLogin.do',
								data: {
									username: uname.val(),
									password: pwd.val(),
									rememberme: remember.val()
								},
								type: 'post',
								cache: false,
								dataType: 'json',
								success: function(data){
									if(data.msg == "account_error"){
										$("#errorspan").html("<font color='red'>用户不存在!</font>");
										rcode_flag = 1;
										$("#randiv").show();
									}
									else if(data.msg == 'password_error'){
										$("#errorspan").html("<font color='red'> 密码错误！</font>");
										rcode_flag = 1;
										$("#randiv").show();
									}
									else if(data.msg == 'authentication_error'){
										$("#errorspan").html("<font color='red'> 您没有授权！</font>");
										rcode_flag = 1;
										$("#randiv").show();
									}
									else{
										location.href = "${ctx}/home.do";
									}
									
								},
								error: function(){
									alert("登录异常!");
								}
								
							});
						}
					} 
					else {
						$("#errorspan").html("");
						if ($.trim(uname.val()) == "") {
							$("#passwordspan").html("");
							$("#userspan")
									.html(
											"<font color='red'>用户名不能为空！</font>");
							uname.focus();
						} else if ($.trim(pwd.val()) == "") {
							$("#userspan").html("");
							$("#passwordspan").html(
									"<font color='red'>密码不能为空！</font>");
							pwd.focus();
						} else if ($.trim(rcode.val()) == "") {
							$("#userspan").html("");
							$("#randomcode_span")
									.html(
											"<font color='red'>验证码不能为空！</font>");
							rcode.focus();
						}
						else{
							$("#userspan").html("");
							$("#passwordspan").html("");
							$("#randomcode_span").html("");
							$.ajax({
								url: '${ctx}/ajaxLogin.do',
								data: {
									username: name.val(),
									password: pwd.val(),
									randomcode: rcode.val() 
								},
								type: 'post',
								cache: false,
								dataType: 'json',
								success: function(data){
									if (data.msg == 'account_error') {
										console.log("account_erroe.");
										$("#errorspan")
												.html(
														"<font color='red'> 用户不存在！</font>");
										rcode_flag = 1;
										$("#randiv").show();
									} else if (data.msg == 'password_error') {
										$("#errorspan")
												.html(
														"<font color='red'> 密码错误！</font>");
										rcode_flag = 1;
										$("#randiv").show();
									} else if (data.msg == 'authentication_error') {
										$("#errorspan")
												.html(
														"<font color='red'> 您没有授权！</font>");
										rcode_flag = 1;
										$("#randiv").show();
									} else {
										location.href = "${ctx}/home.do";
									}
								},
								error: function(){
									alert("登录异常!");
								}
								
							});
						}
					}
				}
		);
		
		function randomcode_refresh(){
			$("#randomcode_img").attr("src", "${ctx}/validatecode.jsp?time"+new Date().getTime())
		}
	
	</script>
</body>
</html>