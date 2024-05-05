<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
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

</style>
<body>

<div class="container mt-5" style="width: 90%">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
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
                <!-- 메인 컨텐츠 끝 -->
            </div>
        </div>
    </div>
</body>
</html>