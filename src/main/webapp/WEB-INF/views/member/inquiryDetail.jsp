<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

