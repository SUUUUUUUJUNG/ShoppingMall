<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- 사이드바 스타일 정의 -->
    <style>
        .sidebar {
            position: fixed;
            width: 200px;
            height: 100%;
            background: #f8f9fa;
            z-index: 1; /* 낮은 z-index 설정 */
        }

        /* 메인 컨텐츠 스타일 정의 */
        .content {
            margin-left: 230px; /* 사이드바 너비보다 약간 더 큰 마진을 설정합니다. */
            position: relative; /* 필요한 경우 z-index 적용을 위해 position 속성을 설정합니다. */
            z-index: 2; /* 더 높은 z-index 설정 */
        }

        /* 사이드바 내 링크에 대한 스타일 */
        .sidebar .list-group-item a {
            color: black; /* 링크의 글자색을 검정색으로 설정 */
            text-decoration: none; /* 링크의 밑줄을 제거 */
        }

        .sidebar .list-group-item a:hover,
        .sidebar .list-group-item a:focus {
            text-decoration: none; /* 호버하거나 포커스될 때 밑줄이 나타나지 않도록 설정 */
            font-weight: bold; /* 호버하거나 포커스될 때 글씨를 굵게 설정 */
        }

        .sidebar .list-group-item.active a {
            font-weight: bold; /* 활성화된 항목의 글씨를 굵게 설정 */
        }

    </style>

</head>
<body>

<!-- 사이드바 추가 -->
<div class="sidebar">
    <ul class="list-group">
        <li class="list-group-item active" aria-current="true">MY 쇼핑</li>
        <li class="list-group-item">주문목록/배송조회</li>
        <li class="list-group-item">취소/반품/교환/환불 내역</li>
        <li class="list-group-item">할인쿠폰</li>
        <li class="list-group-item">포인트</li>
    </ul>
    <ul class="list-group">
        <li class="list-group-item active" aria-current="true">MY 활동</li>
        <li class="list-group-item"><a href="/inquiry">문의하기</a></li>
        <li class="list-group-item"><a href="/inquiry/inquiriesList">문의내역 확인</a></li>
        <li class="list-group-item">리뷰관리</li>
        <li class="list-group-item"><a href="/myShopping">찜 리스트</a></li>
    </ul>
    <ul class="list-group">
        <li class="list-group-item active" aria-current="true">MY 정보</li>
        <li class="list-group-item"><a href="/myPage">개인정보확인/수정</a></li>
        <li class="list-group-item">결제수단 관리</li>
        <li class="list-group-item"><a href="/myPage/deliveryAddresses">배송지 관리</a></li>
    </ul>

</div>

</body>
</html>