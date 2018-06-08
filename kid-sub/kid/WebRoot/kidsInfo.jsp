<%@page import="com.jxdedu.entity.Kid"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="loginControl.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>幼儿详情页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
  <script type="text/javascript" src="css/bootstrap.min.js"></script>
  <style type="text/css">
  	.mytab{
  		width:20%;
  	}
  </style>
  </head>

  <body>
    <div align="center" style="margin-top:50px">
    <h3>幼儿详情</h3>
    <form action="UpdateKidServlet" method="post">
    <table class="table table-bordered table-hover mytab">
    	<c:if test="${not empty kid }">
	    	<input type="hidden" name="kno" value="${kid.kno}%>">
	    	<tr><td>姓名:</td><td><input name="kname" value="${kid.kname}"></td></tr>
	    	<tr><td>性别:</td>
	    		<td>
	    			<c:if test="${kid.sex=='男'}">
	    				<input name="sex" type="radio" value="男" checked> 男 &nbsp;&nbsp;
    					<input name="sex" type="radio" value="女"> 女
					</c:if>
					<c:if test="${kid.sex=='女'}">
						<input name="sex" type="radio" value="男" > 男 &nbsp;&nbsp;
    					<input name="sex" type="radio" value="女" checked> 女
					</c:if>
	    			
    			</td>
    		</tr>
	    	<tr><td>生日:</td><td><input name="birthday" type="date" value="${kid.birthday}"></td></tr>
	    	<tr><td>爱好:</td><td><input name="hobby" value="${kid.hobby}"></td></tr>
    	
    	</c:if>	
      </table>
	    <input class="btn" type="submit" value="保存更改">
    	<input class="btn" type="reset" onclick="javascript:location.href='PagingShowKidsServlet?currentPage=${currentPage}&pageSize=${pageSize}'" value="返回">
    </form>
    	${msg}
    </div>

  </body>
</html>
