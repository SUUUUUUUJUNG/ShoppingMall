<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="container mt-5">
<jsp:include page="common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
<jsp:include page="common/sidebar.jsp" flush="true"/>
<jsp:include page="member/mypage.jsp" flush="true"/>
</div>
</body>
</html>