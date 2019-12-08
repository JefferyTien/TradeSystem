<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="rd" uri="/WEB-INF/taglib/custom.tld" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="padding-top:10px;padding-left:10px;">
		我日
		<rd:select defaultValue="true" name="orderStatus" id="orderStatus" dictName="order_status" />
	</div>
</body>
</html>