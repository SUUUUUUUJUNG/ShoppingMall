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

        .sidebar {
            position: absolute;
            width: 250px;
            height: 100%;
            z-index: 1;
            padding-top: 20px;
        }

        .sidebar .list-group-item {
            border: none;
            background: transparent;
            padding: 15px 20px;
            transition: background-color 0.3s, color 0.3s;
        }

        .sidebar .list-group-item:hover,
        .sidebar .list-group-item:focus {
            background-color: #FFDDEE; /* 호버시 밝은 배경색 변경 */
            color: #6d0230;
            cursor: pointer;
        }

        .sidebar .list-group-item.active {
            font-weight: bold;
            color: white;
            background-color: #D66D75; /* 활성화된 항목에 대한 배경색 */
            text-align: center;
        }

        .sidebar .list-group-item a {
            color: #6d0230;
            text-decoration: none;
        }

        .sidebar .list-group-item a:hover {
            text-decoration: underline;
        }

        .btn-custom-primary {
            background-color: #6c757d;
            border-color: #6c757d;
            color: white;
        }

        .btn-custom-primary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        .btn-custom-warning {
            background-color: #ffc107;
            border-color: #ffc107;
            color: #212529;
        }

        .btn-custom-warning:hover {
            background-color: #e0a800;
            border-color: #d39e00;
            color: #212529;
        }

        .btn-custom-danger {
            background-color: #e3342f;
            border-color: #e3342f;
            color: white;
        }

        .btn-custom-danger:hover {
            background-color: #cc1f1a;
            border-color: #b71c1c;
        }

        .table-custom thead {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .table-custom tbody tr:nth-child(even) {
            background-color: #e9ecef;
        }

        .table-custom tbody tr:nth-child(odd) {
            background-color: #f8f9fa;
        }

        .table-custom tbody tr:hover {
            background-color: #e2e6ea;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <jsp:include page="../../common/navbar.jsp" flush="true"></jsp:include>
    <h2>상품 관리</h2>
    <hr>
    <div class="container mt-4">
        <div class="row">
            <!-- 사이드바 자리-->
            <div class="sidebar">
                <div class="shadow">
                    <ul class="list-group">
                        <li class="list-group-item active" aria-current="true">관리자 페이지</li>
                        <li class="list-group-item"><a href="/admin/goods">상품 관리</a></li>
                        <li class="list-group-item"><a href="/admin/statistics">판매 통계</a></li>
                        <li class="list-group-item">할인 쿠폰(구현예정)</li>
                    </ul>
                </div>
            </div>
            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-8 offset-md-3 shadow">
                <div class="mb-3 text-end">
                    <a href="/admin/goods/add" class="btn btn-custom-primary">상품 추가</a>
                </div>

                <!-- Displaying Goods -->
                <table class="table table-hover table-custom">
                    <thead>
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
                                <a href="/admin/goods/edit/${item.gCode}" class="btn btn-custom-warning btn-sm">수정</a>
                                <button class="btn btn-custom-danger btn-sm delete-btn" data-code="${item.gCode}">삭제</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- 메인 컨텐츠 끝 -->
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $(".delete-btn").on("click", function () {
                let code = $(this).data("code");
                if (confirm("이 상품을 삭제하시겠습니까?")) {
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
                    });
                }
            });
        });
    </script>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
