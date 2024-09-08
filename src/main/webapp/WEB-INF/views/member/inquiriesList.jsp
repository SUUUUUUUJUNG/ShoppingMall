<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 내역</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <style>
        .black-link {
            color: black !important;
            text-decoration: none !important;
        }
        .black-link:hover {
            text-decoration: underline;
        }
        .table thead th {
            background-color: #f8f9fa;
        }
        /* Toned down red for the delete button */
        .btn-delete {
            background-color: #b54a4a; /* Muted red */
            color: white;
        }
        .btn-delete:hover {
            background-color: #a03e3e; /* Darker muted red */
            color: white;
        }
        .inquiry-title {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div class="mb-4"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-3 shadow p-4 rounded">
                <h2 class="mb-4">문의 내역</h2>
                <table class="table table-hover">
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
                            <td class="inquiry-title"><a href="/inquiry/detail/${inquiry.inquiry_Id}" class="black-link">${inquiry.inquiry_Title}</a></td>
                            <td><c:out value="${inquiry.inquiry_Date}"/></td>
                            <td>${inquiry.status}</td>
                            <td>
                                <form action="/inquiry/delete" method="get" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                    <input type="hidden" id="inquiry_Id" name="inquiry_Id" value="${inquiry.inquiry_Id}"/>
                                    <button class="btn btn-delete btn-sm" id="submit">삭제</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div> <!-- Closing tag for col-md-8 offset-md-3 shadow -->
        </div> <!-- Closing tag for row -->
    </div> <!-- Closing tag for container mt-5 -->
</div> <!-- Closing tag for container mt-5 -->
</body>
</html>
