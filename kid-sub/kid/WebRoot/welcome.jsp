<%@page import="com.jxdedu.entity.Kid"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <style type="text/css">
        table.mytab{
            width:70%;
        }
        form {
            margin:0;
        }
    </style>
    <!-- jQuery -->
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <link   rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
    <script type="text/javascript" src="css/bootstrap.min.js"></script>
  </head>

  <body>

  <div align="center" style="margin-top:50px">
        <%@ include file="loginControl.jsp" %>
        <%@ include file="loginsuccess.jsp" %>
        
        <form action="FuzzySearchServlet" method="post">
            <input placeholder="请输入幼儿姓名" name="kidname">
            <input type="submit" class="btn btn-info" value="查询">
            <a class="btn btn-info" href="addKids.jsp">添加</a>&nbsp;&nbsp;
            <a class="btn btn-info" href="javascript:void(0);"  onclick="delete_()">删除</a>
        </form>

        <!-- 展示幼儿信息的表格 -->
        <br><br>

        <table class="table table-bordered table-hover mytab">
            <tr><td><input type="checkbox" id="chk_all" name="all" onclick="select_deselect_all(this)">全选</td>
                <td>姓名</td><td>性别</td><td>生日</td><td>爱好</td></tr>
			
            <!-- 表格数据是动态生成的，需要根据后台传来的数据而决定 -->
            <c:if test="${not empty list }">
	           <c:forEach var="k" items="${list}">
	               <tr>
	                   <td onclick="select_deselect();" ><input type="checkbox" name="sub"  value="${k.kno}"></td>
	                   <td><a href="ShowKidServlet?kidId=${k.kno}" title="显示详情">${k.kname}</a></td>
	                   <td>${k.sex}</td>
	                   <td>${k.birthday}</td>
	                   <td>${k.hobby}</td>
	               </tr>
	            </c:forEach>
            </c:if>
           	<c:if test="${empty list}">
          	   <tr>
                  <td colspan="5">没有相关数据</td>
               </tr>
           	</c:if>



        </table>

    </div>


    <!--  本页数据 -->
    <script type="text/javascript" src="js/welcome.js"></script>
    <!-- 全选效果 -->
    <script type="text/javascript" src="js/check.js"></script>
    <!-- 增删改 -->
    <script type="text/javascript" src="js/manipulate.js"></script>

  </body>
</html>
