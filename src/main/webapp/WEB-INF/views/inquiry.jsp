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
    <!-- 추가적인 CSS -->
    <style>
        .container {
            margin-top: 20px;
        }
        .inquiry-form {
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>

</head>
<body>
<jsp:include page="common/navbar.jsp" flush="true"/>
<h1>문의하기 화면입니다</h1><br>
<a href="/inquiry/inquiriesList">문의 내역</a>

<jsp:include page="member/inquiry.jsp" flush="true"></jsp:include><br>
</body>
</html>