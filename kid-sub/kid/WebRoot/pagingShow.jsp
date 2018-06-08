<%@page import="com.jxdedu.entity.Kid"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>首页-</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
table.mytab {
	width: 70%;
}

form {
	margin: 0;
}
</style>
<!-- jQuery -->
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="css/bootstrap.min.js"></script>
</head>
<script type="text/javascript">

	$(function(){

		$("#txt01").keyup(function(){
			var str = this.value;
			if (str.length == 0) {
				clearHint();
			}else{
				$.ajax({// 
					type:"get" ,
					url:"GetHintServlet?q=" + str,
					success:function(data){setHint(data);},
					error:function(data){
						console.log("通信失败!");
					}
				});
			}
		});
		
	});
	
	function clearHint(){
		$("#hint").innerHTML = "";
	}
	
	function setHint(data){
		$("#hint").text(data); // $("#hint").innerHTML = data; 不管用?
	}
</script>
<body>

	<div align="center" style="margin-top:50px">
		<%@ include file="loginControl.jsp"%>
		<%@ include file="loginsuccess.jsp"%>

		<form action="FuzzySearchServlet" method="post">
			<input placeholder="请输入幼儿姓名" name="kidname" id="txt01"> <input
				type="submit" class="btn btn-info" value="查询"> <a
				class="btn btn-info" href="addKids.jsp">添加</a>&nbsp;&nbsp; <a
				class="btn btn-info" href="javascript:void(0);" onclick="delete_()">删除</a>
			<a class="btn btn-info" href="LogoutServlet" title="退出登录">注销</a>
		</form>
		<span id="hint"></span>

		<!-- 展示幼儿信息的表格 -->
		<br>
		<br>

		<table class="table table-bordered table-hover mytab">
			<tr>
				<td><input type="checkbox" id="chk_all" name="all"
					onclick="select_deselect_all(this)">全选</td>
				<td>kno</td>
				<td>姓名</td>
				<td>性别</td>
				<td>生日</td>
				<td>爱好</td>
			</tr>

			<!-- 表格数据是动态生成的，需要根据后台传来的数据而决定 -->
			<c:if test="${not empty list }">
				<c:forEach var="k" items="${list}">
					<tr>

						<td onclick="select_deselect();"><input type="checkbox"
							name="sub" value="${k.kno}">
						</td>
						<td>${k.kno}</td>
						<td><a href="ShowKidServlet?kidId=${k.kno}" title="显示详情">${k.kname}</a>
						</td>
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
		currentPage: ${currentPage}<br /> <a
			href="PagingShowKidsServlet?pageSize=${pageSize}&currentPage=1">首页&nbsp;</a>
		<a
			href="PagingShowKidsServlet?pageSize=${pageSize}&currentPage=${currentPage==1?1 :currentPage-1}">上一页&nbsp;</a>
		<a
			href="PagingShowKidsServlet?pageSize=${pageSize}&currentPage=${currentPage== pageCount ? pageCount: currentPage+1}">下一页&nbsp;</a>
		<a
			href="PagingShowKidsServlet?pageSize=${pageSize}&currentPage=${pageCount}">尾页&nbsp;</a>
		<br />
		<c:forEach var="i" begin="1" end="${pageCount}" step="1">
			<a href="PagingShowKidsServlet?pageSize=${pageSize}&currentPage=${i}">${i}&nbsp;</a>
		</c:forEach>

		<select id="choice"
			onchange="location.href='PagingShowKidsServlet?pageSize=${pageSize}&currentPage='+ $(this).val()">
			<c:forEach var="i" begin="1" end="${pageCount}" step="1">
				<c:if test="${i==currentPage}">
					<option value="${i}" selected="selected">${i}/${pageCount}</option>
				</c:if>
				<c:if test="${i!=currentPage}">
					<option value="${i}">${i}/${pageCount}</option>
				</c:if>
			</c:forEach>
		</select>

	</div>


	<!--  本页数据 -->
	<script type="text/javascript" src="js/welcome.js"></script>
	<!-- 全选效果 -->
	<script type="text/javascript" src="js/check.js"></script>
	<!-- 增删改 -->
	<script type="text/javascript" src="js/manipulate.js"></script>

</body>
</html>
