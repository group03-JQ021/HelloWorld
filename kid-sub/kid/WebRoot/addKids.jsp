<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="loginControl.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- jQuery -->
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="css/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {

		$("#txt01").keyup(function() {
			var str = this.value;
			if (str.length == 0) {
				clearHint();
			} else {
				$.ajax({
					type : "get",
					url : "TestValidateServlet?name=" + str,
					success : function(data) {
						$("#msg").text(data);
					},
					error : function(data) {
						console.log("通信失败!");
					}
				});
			}
		});

	});

	function clearHint() {
		$("#msg").text("");
	}
</script>
<style type="text/css">
	#msg{
		margin-left:2em;
		position:absolute;
		font-style:italic;
		color:red;
	}
</style>

</head>

<body>

	<div align="center">
		<h3>幼儿添加</h3>
		<form action="AddKidServlet" method="post">
			<input placeholder="请输入幼儿姓名" name="kname" id="txt01"><span id="msg"></span>
			<br>
			<br> <input type="radio" name="sex" value="男"> 男 <input
				type="radio" name="sex" value="女"> 女 <br>
			<br> <input type="date" name="birthday"> <br>
			<br>
			<textarea rows="6" cols="10" name="hobby"></textarea>
			<br>
			<br> <input type="submit" value="提交"> <input
				type="reset" value="重置"> <input type="button" value="返回"
				onclick="javascript:location.href='GetAllKidsServlet'">

		</form>
		<%
			//获取在后台保存的添加提示信息，因为在后台放到请求中的数据是Object类型
			String addMsg = (String) request.getAttribute("addMsg");
			if (addMsg != null) {
				out.print(addMsg);//在页面显示提示信息
			}
		%>

	</div>
</body>
</html>
