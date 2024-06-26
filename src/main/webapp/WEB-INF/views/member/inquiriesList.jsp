<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" type="text/css" href="/css/common.css">
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-3 shadow">
                <h2>문의 내역</h2>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th>문의 유형</th>
                        <th>문의 제목</th>
                        <th>문의 날짜</th>
                        <th>상태</th>
                        <th>조치</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="inquiry" items="${inquiries}">
                        <tr>
                            <td>${inquiry.inquiry_Type}</td>
                            <td><a href="/inquiry/detail/${inquiry.inquiry_Id}">${inquiry.inquiry_Title}</a></td>
                            <td><c:out value="${inquiry.inquiry_Date}"/></td>
                            <td>${inquiry.status}</td>
                            <td>
                                <form action="/inquiry/delete" method="get">
                                    <input type="hidden" id="inquiry_Id" name="inquiry_Id"
                                           value="${inquiry.inquiry_Id}"/>
                                    <button class="btn btn-danger btn-sm" id="submit">삭제</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div> <!-- Closing tag for col-md-8 offset-md-3 shadow -->
        </div> <!-- Closing tag for row -->
    </div>