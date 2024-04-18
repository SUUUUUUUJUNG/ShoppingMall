<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주문 상세 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
        .header {
            background-color: #0056b3;
            color: white;
            padding: 10px 15px;
            margin-bottom: 30px;
            border-radius: 5px;
        }
        .value {
            font-weight: bold;
        }
        .btn-home {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="../common/navbar.jsp" flush="true"/>
<div class="container">
    <div class="header">
        <h1>주문이 성공적으로 처리되었습니다</h1>
    </div>
    <div class="alert alert-success" role="alert">
        주문 처리가 성공적으로 완료되었습니다.
    </div>
    <h2>주문 상세 정보</h2>
    <table class="table table-hover">
        <tbody>
        <tr>
            <th>주문 번호:</th>
            <td class="value">${orderDTO.orderId}</td>
        </tr>
        <tr>
            <th>고객 ID:</th>
            <td class="value">${orderDTO.memberId}</td>
        </tr>
        <tr>
            <th>수령인 이름:</th>
            <td class="value">${orderDTO.receiverName}</td>
        </tr>
        <tr>
            <th>우편 번호:</th>
            <td class="value">${orderDTO.receiverAddress}</td>
        </tr>
        <tr>
            <th>연락처:</th>
            <td class="value">${orderDTO.receiverContact}</td>
        </tr>
        <tr>
            <th>배송 메모:</th>
            <td class="value">${orderDTO.deliveryNote}</td>
        </tr>
        </tbody>
    </table>
    <h3>주문 상품 목록</h3>
    <table class="table">
        <thead>
        <tr>
            <th>상품 코드</th>
            <th>색상 옵션</th>
            <th>사이즈 옵션</th>
            <th>수량</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderItems}" var="item">
            <tr>
                <td>${item.GCode}</td>
                <td>${item.colorOption}</td>
                <td>${item.sizeOption}</td>
                <td>${item.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-center btn-home">
        <a href="/" class="btn btn-primary">홈으로 돌아가기</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
