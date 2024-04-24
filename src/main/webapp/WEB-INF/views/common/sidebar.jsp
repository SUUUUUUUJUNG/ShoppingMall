<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- 사이드바 스타일 정의 -->
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

<!-- 사이드바 추가 -->
<div class="sidebar">
    <div class="shadow">
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
            <li class="list-group-item"><a href="/myPage/review">리뷰관리</a></li>
            <li class="list-group-item"><a href="/myShopping">찜 리스트</a></li>
        </ul>
        <ul class="list-group">
            <li class="list-group-item active" aria-current="true">MY 정보</li>
            <li class="list-group-item"><a href="/myPage">개인정보확인/수정</a></li>
            <li class="list-group-item">결제수단 관리</li>
            <li class="list-group-item"><a href="/myPage/deliveryAddresses">배송지 관리</a></li>
        </ul>
    </div>
</div>
