<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${! empty mesg}">
	<script>
alert('${mesg}');
</script>
</c:if>
<% if(session.getAttribute("mesg")!=null){
	session.removeAttribute("mesg");
} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="common/top.jsp" flush="true" /><br>
	<jsp:include page="common/menu.jsp" flush="true" />

	<hr>

	<jsp:include page="goods/goodsRetrieve.jsp"></jsp:include>
</body>
</html>