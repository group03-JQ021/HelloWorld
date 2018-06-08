<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jxdedu.entity.Kid" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>@taglib 指令的使用</title>
    
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
    <%
    	Kid k = new Kid();
    	k.setKno(1);
    	k.setKname("test el");
    	k.setHobby("many hobbies");
    	pagecontext.setAttribute("k",k);
     %>
     ${k.kname}
  </body>
</html>
