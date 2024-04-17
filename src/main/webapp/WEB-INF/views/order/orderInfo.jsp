<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 완료</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>주문 완료</h1>
    <div class="alert alert-success" role="alert">
        주문이 성공적으로 처리되었습니다.
    </div>
    <h2>주문 상세 정보</h2>
    <table class="table">
        <tbody>
        <tr>
            <th>주문번호</th>
            <td>${orderDTO.orderId}</td>
        </tr>
        <tr>
            <th>주문자</th>
            <td>${orderDTO.memberId}</td>
        </tr>

        <tr>
            <th>수령인 이름</th>
            <td>${orderDTO.receiverName}</td>
        </tr>
        <tr>
            <th>우편번호</th>
            <td>${orderDTO.receiverAddress}</td>
        </tr>

        </tbody>
    </table>
    <div class="mt-4">
        <a href="/" class="btn btn-primary">홈으로</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
