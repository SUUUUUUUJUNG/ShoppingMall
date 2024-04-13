<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>상품 관리</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        .table-custom {
            margin-top: 20px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        .header-link {
            margin-bottom: 10px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="../../common/navbar.jsp" flush="true"/>
<div class="container mt-4">
    <h2>상품 관리</h2>
    <hr>

    <jsp:include page="../../common/admin-navbar.jsp" flush="true"></jsp:include>

    <!-- Add New Goods Button -->
    <div class="mb-3 text-end">
        <a href="/admin/goods/add" class="btn btn-primary">상품 추가</a>
    </div>

    <!-- Displaying Goods -->
    <table class="table table-hover table-custom">
        <thead class="table-dark">
        <tr>
            <th>코드</th>
            <th>카테고리</th>
            <th>이름</th>
            <th>가격</th>
            <th>조치</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.gCode}</td>
                <td>${item.gCategory}</td>
                <td>${item.gName}</td>
                <td>${item.gPrice}</td>
                <td>
                    <a href="/admin/goods/edit/${item.gCode}" class="btn btn-warning btn-sm">수정</a>
                    <button class="btn btn-danger btn-sm delete-btn" data-code="${item.gCode}">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>

    $(document).ready(function () {

        $(".delete-btn").on("click", function () {

            let code = $(this).data("code");

            $.ajax({
                url: "/api/goods/" + code,
                type: "DELETE",
                contentType: 'application/json',
                success: function (response) {
                    alert(response.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    alert(error);
                }
            });//end ajax
        });
    });
</script>

<!-- Bootstrap JS and Popper.js (optional) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
