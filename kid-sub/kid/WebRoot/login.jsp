<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.jxdedu.entity.Kid"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		console.log(": enter");
		$("#bt").click(function(){
			console.log(":");
			var username = $(":input[name='username']").val();
			var pwd = $(":input[name='pwd']").val();
			console.log(username+":"+pwd);
			$.ajax({
				type:"get",	// jQuery 的 post方式,怎样用? w3school 没有; 先去其它地方搜搜资料
				url: "TestLoginServlet?name="+username+"&password="+pwd,
				success:function(data){
					// 如果用户名和密码不是有效用户,则服务器返回字符串 false
					console.log(data+":");
					if (data == "false"){
						// 如果密码错误, 显示错误信息
						$("#msg").text("用户名或密码错误!");
						// 并拦截提交动作
						
					}else{
						$("#formId").submit();
					}
				},
				error:function(data){
					console.log("通信失败!");
					$("#formId").submit();
				}
			});
		});
	});
</script>

<body>
	<%--
		初次登陆,重定向到自动登录检测
	--%>


	<div align="center" sytle="margin-top:100px">
		<h3>登录页面</h3>
		<p>用户名 abc,密码 123</p>
		<form action="LoginServlet" method="post" id="formId">
			<input placeholder="请输入用户名" name="username"> <br>
			<br> <input placeholder="请输入密码" name="pwd"> <br>
			
			<span id="msg"></span> <br>
			<input id="bt" type="button" value="登录">
		</form>

		${loginMsg}
	</div>
</body>
</html>
