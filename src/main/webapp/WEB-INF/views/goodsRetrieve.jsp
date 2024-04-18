<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/common.css">
    <style>
        .pink-theme {
            background-color: #f8d7da;
            color: #721c24;
        }


        .table-pink th {
            background-color: #f8d7da;
            color: #721c24;
        }

        .btn-pink {
            background-color: #ff85b2;
            border-color: #ff85b2;
        }

        .btn-pink:hover {
            background-color: #ff65a3;
            border-color: #ff65a3;
        }

        .card-custom {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.1);
            transition: 0.3s;
            border-radius: 5px;
        }

        .card-custom:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .rounded-image {
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="common/navbar.jsp" flush="true"/>
	<div style="margin-bottom: 70px;"></div>
    <jsp:include page="goods/goodsRetrieve.jsp"></jsp:include>
    <div style="margin-bottom: 70px;"></div>
    <%--상품정보 table과 tab 사이의 여백 추가--%>
    <jsp:include page="goods/product-details-tabs.jsp"></jsp:include>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>