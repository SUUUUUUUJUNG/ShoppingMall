<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="row">
		<c:forEach var="dto" items="${goodsList}" varStatus="status">
		<div class="col-md-3 col-sm-6 goods-item">
			<div class="card">
				<a href="/goods/detail?gCode=${dto.GCode}">
					<img class="card-img-top" src="/images/items/${dto.GImage}.gif" alt="${dto.GName}">
				</a>
				<div class="card-body text-center">
					<a class="a_black" href="/goods/detail?gCode=${dto.GCode}">
						<h5 class="card-title">${dto.GName}</h5>
					</a>
					<p class="td_gray">${dto.GContent}</p>
					<p class="td_red"><strong>${dto.GPrice}</strong></p>
				</div>
			</div>
		</div>
		<c:if test="${(status.index + 1) % 4 == 0}">
	</div><div class="row"> <!-- New row for every 4 items -->
	</c:if>
	</c:forEach>
</div>
