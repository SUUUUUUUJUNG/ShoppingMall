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
            <th>문의 날짜</th>
            <th>상태</th>
            <th>조치</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="inquiry" items="${inquiries}">
            <tr>
                <td>${inquiry.inquiry_Type}</td>
                <td><a href="/inquiry/detail/${inquiry.inquiry_Id}">${inquiry.inquiry_Title}</a></td>
                <td><c:out value="${inquiry.inquiry_Date}"/></td>
                <td>${inquiry.status}</td>
                <td>
                    <form action="/inquiry/delete" method="get">
                        <input type="hidden" id="inquiry_Id" name="inquiry_Id" value="${inquiry.inquiry_Id}"/>
                    <button class="btn btn-danger btn-sm" id="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

