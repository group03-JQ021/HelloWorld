<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.jxdedu.entity.Kid" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
    
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
  	<%--
		初次登陆,重定向到自动登录检测
	--%>

		<%-- 调试用输出 --%>
		<%--<%
				System.out.println("begin login");
		%>--%>
	<c:if test="${ empty auto_login_check}">
		<%-- 调试用输出 --%>
		<%
			System.out.println("go auto check");
		%>
		<jsp:forward page="/AutoLoginCheckServlet"></jsp:forward>
	</c:if>

    <div align="center" sytle="margin-top:100px">
    	<h3>登录页面</h3>
    	<p>用户名 abc,密码 123</p>
    	<form action="LoginServlet2" method="post">
    		<input placeholder="请输入用户名" name="username">
    		<br><br>
			<input placeholder="请输入密码" name="pwd">
			<br><br>
			<input type="checkbox" name="remeber" title="30秒内自动登录"/>30秒内自动登录
			<br><br><input type="submit" value="登录">
    	</form>
    	
    	${loginMsg}
    </div>
  </body>
</html>
