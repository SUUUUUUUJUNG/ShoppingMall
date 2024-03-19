<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container mt-5">
    <h2>문의 내역</h2>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>문의 유형</th>
            <th>문의 제목</th>
            <th>문의 내용</th>
            <th>문의 날짜</th>
            <th>상태</th>
            <th>조치</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="inquiries" items="${inquiries}">
            <tr>
                <td>${inquiries.inquiry_Type}</td>
                <td>${inquiries.inquiry_Title}</td>
                <td>${inquiries.inquiry_Content}</td>
                <td><c:out value="${inquiries.inquiry_Date}"/></td>
                <td>${inquiries.status}</td>
                <td>
                    <button class="btn btn-primary btn-sm">수정</button>
                    <button class="btn btn-danger btn-sm">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

