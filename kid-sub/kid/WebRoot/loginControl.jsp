<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty loginFlag}">
	<c:redirect  url="login.jsp"></c:redirect>
</c:if>