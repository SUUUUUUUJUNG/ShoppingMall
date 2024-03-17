<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<c:if test="${!empty success}">
	<script type="text/javascript">
		alert('${success}')
	</script>
</c:if>

<%
	if(session.getAttribute("success") != null)
	session.removeAttribute("success");
%>

</head>
<body>

<jsp:include page="common/navbar.jsp" flush="true"/>

	<h1><a href="/">메인 화면</a></h1>

	<!-- top / menu 헤더로 include처리 -->
	<jsp:include page="common/menu.jsp" flush="true" /><br>
	<jsp:include page="common/top.jsp" flush="true" />
	
	<hr>
	
	<jsp:include page="goods/goodsList.jsp" flush="true"></jsp:include>
	<%-- <jsp:include page="goods/goodsList.jsp" flush="true" /> --%>

</body>
</html>