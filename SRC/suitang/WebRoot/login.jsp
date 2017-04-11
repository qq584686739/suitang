<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   	<form action="http://139.129.11.18:8090/suitang/userLogin_login.action" method="post"> 
   <!-- 	<form action="http://localhost:8080/suitang/userLogin_login.action" method="post"> -->
   	
		认证类型：<input type="text" name="identity_type" /> <br/>
		认证id：<input type="text" name="identifier" /> <br/>
		昵称：<input type="text" name="nickname" /> <br/>
		性别：<input type="text" name="sex" /> <br/>
		头像	：<input type="text" name="avatar" /> <br/>
		本次登录设备：<input type="text" name="device_name" /> <br/>
		本次登录设备id：<input type="text" name="device_id" /> <br/>
		<input type="submit" value="登录"/>
   	</form>
  </body>
</html>
