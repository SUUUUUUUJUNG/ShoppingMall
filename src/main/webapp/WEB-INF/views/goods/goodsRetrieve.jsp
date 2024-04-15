<%@ page import="com.shoppingmall.domain.dto.goods.GoodsDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
	$(function() {
		// 장바구니 버튼 클릭 이벤트
		$("#cart").on("click",function(e){
			e.preventDefault();
			// 사이즈와 색상이 선택되었는지 확인
			var gSize = $("#gSize").val();
			var gColor = $("#gColor").val();
			// 사이즈나 색상이 선택되지 않았으면 경고창 표시
			if(gSize === "사이즈선택" || gColor === "색상선택") {
				alert("상품의 사이즈와 색상 옵션을 선택해주세요.");
				return; // 폼 제출 방지
			}

			var gCode=$("#gCode").val();
			var gAmount=$("#gAmount").val();

			const data = {
				gCode:gCode,
				gSize:gSize,
				gColor:gColor,
				gAmount:gAmount,
			}

			$.ajax({
				url:"/cart/add",
				type: "post",
				contentType: "application/json",
				data:JSON.stringify(data),
				success:function (data,status,xhr){
					alert(data.message);
				},
				error: function (xhr, status, error) {
					console.log(error)
				},
			})

		});

		$("#wishlist-button").on("click",function(e){
			event.preventDefault(e);
			var code = $("#wishlist-button").data("code");
			console.log(code);
			$.ajax({
				url:"/api/wishList",
				type:"post",
				data:{
					gCode:code
				},
				success:function(data,status,xhr){
					let mesg = data.message;
					alert(mesg);
					if(mesg.includes("추가")) {
						$("#wishlist-button").html('<i class="fas fa-heart"></i>');
					}
					if(mesg.includes("삭제")) {
						$("#wishlist-button").html('<i class="far fa-heart"></i>');
					}
				},
				error:function(xhr,status,error){
					console.log("찜하기 실패");
					console.log(error);
				}
			})
		})


	});
</script>
<form name="goodRetrieveForm" method="GET" action="#" class="container mt-3">
	<input type="hidden" name="gImage" id="gImage" value="${goodsDTO.GImage}">
	<input type="hidden" name="gCode" id="gCode" value="${goodsDTO.GCode}">
	<input type="hidden" name="gName" id="gName" value="${goodsDTO.GName}">
	<input type="hidden" name="gPrice" id="gPrice" value="${goodsDTO.GPrice}">

	<div class="card card-custom bg-light mb-3">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="/images/items/${goodsDTO.GImage}.gif" class="img-fluid rounded-image" alt="${goodsDTO.GName}">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">${goodsDTO.GName}</h5>
					<p class="card-text"><strong>제품 코드:</strong> ${goodsDTO.GCode}</p>
					<p class="card-text"><strong>가격:</strong> ${goodsDTO.GPrice}</p>
					<p class="card-text"><small class="text-muted">특정 지역 무료 배송, 도서산간 추가 배송비 발생.</small></p>
					<div class="mb-2">
						<label>사이즈:</label>
						<select class="form-select form-select-sm" name="gSize" id="gSize">
							<option selected value="사이즈선택">사이즈 선택</option>
							<option value="L">L</option>
							<option value="M">M</option>
							<option value="S">S</option>
						</select>
					</div>
					<div class="mb-3">
						<label>색상:</label>
						<select class="form-select form-select-sm" name="gColor" id="gColor">
							<option selected value="색상선택">색상 선택</option>
							<option value="navy">네이비</option>
							<option value="black">블랙</option>
							<option value="ivory">아이보리</option>
							<option value="white">화이트</option>
							<option value="gray">그레이</option>
						</select>
					</div>
					<button class="btn btn-pink" id="wishlist-button" data-code="${goodsDTO.GCode}">
						<% if ((boolean) request.getAttribute("itemWishlisted")) { %>
						<i class="fas fa-heart"></i> 찜한 상품
						<% } else { %>
						<i class="far fa-heart"></i> 찜하기
						<% } %>
					</button>
					<button class="btn btn-pink">구매하기</button>
					<button class="btn btn-pink" id="cart">장바구니 추가</button>
				</div>
			</div>
		</div>
	</div>
</form>