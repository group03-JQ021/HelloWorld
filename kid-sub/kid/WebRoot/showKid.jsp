<%@page import="com.jxdedu.entity.Kid"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>首页</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>

  <body>
  	<div align="center" style="margin-top:50px">
        <%@ include file="loginsuccess.jsp" %>

        <% Kid k = (Kid)request.getSession().getAttribute("kid"); %>
        <div align="center">
            <h3>幼儿信息详情 || 编辑提交后可更新</h3>
            <form action="UpdateKidServlet" method="post">
                编号:
                <input type="text" value = "${kid.kno}>" name="kno"  readonly="readonly">
                <br><br>

                姓名:<input placeholder="${kid.kname}" name="kname" value="${kid.kname}">
                <br><br>
                <input type="radio" name="sex" value="男"
                    <% if("男".equals(k.getSex().trim())){ out.print("checked");} %>
                > 男
                <input type="radio" name="sex" value="女"
                    <% if("女".equals(k.getSex().trim())){ out.print("checked");} %>
                > 女
                <br><br>
                生日:<input type="date" name="birthday"  value="<%=k.getBirthday() %>">
                <br><br>
                爱好:<textarea rows="6" cols="10" name="hobby"><%=k.getHobby()%></textarea>
                <br><br>
                <input type="submit" value="提交">
                <input type="reset"  value="重置">
                <input type="button" value="返回" onclick="javascript:location.href='GetAllKidsServlet'">

            </form>

	${msg}

    </div>

  </body>
</html>
