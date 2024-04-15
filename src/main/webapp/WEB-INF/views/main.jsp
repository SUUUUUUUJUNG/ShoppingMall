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
	<style>

		.goods-item {
			transition: transform .2s, box-shadow .2s; /* 애니메이션 효과 추가 */
		}
		.goods-item:hover {
			transform: scale(1.03); /* 마우스 오버시 조금 커지는 효과 */
			box-shadow: 0 0 10px #FF69B4; /* 분홍색 그림자 */
		}
		.a_black {
			color: black;
			text-decoration: none; /* 링크 밑줄 제거 */
		}
		.a_black:hover {
			color: darkgray;
			text-decoration: none; /* 호버 시 텍스트 색 변경 */
		}
		.td_default {
			color: #444;
		}
		.td_red strong {
			color: red;
		}
		.goods-item {
			margin-bottom: 20px; /* Adds space below each item */
		}
		.container .row:not(:last-child) {
			margin-bottom: 20px; /* Adds space between rows */
		}
	</style>
	<link rel="stylesheet" href="/css/common.css">

</head>
<body>
<jsp:include page="common/navbar.jsp" flush="true"/>
	<hr>
<jsp:include page="goods/goodsList.jsp" flush="true"></jsp:include>
	<%-- <jsp:include page="goods/goodsList.jsp" flush="true" /> --%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>