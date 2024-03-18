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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<jsp:include page="common/navbar.jsp" flush="true"/>
	<hr>
	<jsp:include page="goods/goodsRetrieve.jsp"></jsp:include>
	<div style="margin-bottom: 70px;"></div> <%--상품정보 table과 tab 사이의 여백 추가--%>
	<jsp:include page="goods/product-details-tabs.jsp"></jsp:include>
</body>
</html>