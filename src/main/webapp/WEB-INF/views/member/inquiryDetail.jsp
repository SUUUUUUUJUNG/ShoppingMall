<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<jsp:include page="../common/navbar.jsp" flush="true"/>
<div class="container mt-5">
    <h2>문의 상세 정보</h2>
<c:forEach var="inquiry" items="${inquiries}">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title"><b>문의 유형</b></h3>
            <p class="card-text">${inquiry.inquiry_Type}</p>
        </div>
    </div>
    <div class="card mt-3">
        <div class="card-body">
            <h3 class="card-title"><b>문의 내용</b></h3>
            <p class="card-text">${inquiry.inquiry_Content}</p>
        </div>
    </div>
    <div class="card mt-3">
        <div class="card-body">
            <h3 class="card-title"><b>답변</b></h3>
            <!-- 여기서 c:if는 답변이 있을 경우에만 답변 섹션을 표시하기 위함입니다. -->
            <c:if test="${not empty inquiry.response}">
                <p class="card-text">${inquiry.response}</p>
                <p class="text-muted"><small>${inquiry.response_Date}</small></p>
            </c:if>
        </div>
    </div>
</div>
</c:forEach>

