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
<jsp:include page="../common/navbar.jsp" flush="true"/>
<hr>
admin.jsp
<a href="/admin">어드민 페이지</a><br>
<a href="/admin/goods">상품 관리</a><br>
<a href="/admin/statistics">판매 통계(구현예정)</a><br>
<a href="/admin/coupon">할인 쿠폰(구현예정)</a><br>
<hr>
</body>
</html>