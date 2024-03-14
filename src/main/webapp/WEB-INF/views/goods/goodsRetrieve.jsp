<%@ page import="com.shoppingmall.dto.GoodsDTO" %>
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
<!--  -->
<form name="goodRetrieveForm" method="GET" action="#"><!--action을 막음 --><!-- hidden data -->
	<input type="hidden" name="gImage" id="gImage" value="${goodsDTO.GImage}">
	<input type="hidden" name="gCode" id="gCode" value="${goodsDTO.GCode}">
	<input	type="hidden" name="gName" id="gName" value="${goodsDTO.GName}">
	<input	type="hidden" name="gPrice" id="gPrice" value="${goodsDTO.GPrice}">



	<%--상품 정보 table 가운데 정렬하는 코드 추가--%>
	<table width="710" cellspacing="0" cellpadding="0" border="0" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					   border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- 상품 정보 -</b></font>
							&nbsp;</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td height="1" colspan="8" bgcolor="CECECE"></td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>

					<tr>
						<td rowspan="7"><img src="/images/items/${goodsDTO.GImage}.gif"
											 border="0" align="center" width="300" /></td>
						<td class="td_title">제품코드</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${goodsDTO.GCode}
						</td>
					</tr>
					<tr>
						<td class="td_title">상품명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${goodsDTO.GName}

						</td>
					</tr>
					<tr>
						<td class="td_title">가격</td>

						<td class="td_red" colspan="2" style='padding-left: 30px'>
							${goodsDTO.GPrice}
						</td>
					</tr>
					<tr>
						<td class="td_title">배송비</td>
						<td colspan="2"><font color="#2e56a9" size="2"
											  style='padding-left: 30px'><b> 무료배송</b> </font> <font size="2">(도서
							산간지역 별도 배송비 추가)</font></td>
					</tr>
					<tr>
						<td class="td_title" rowspan="2">상품옵션</td>
						<td colspan="2" style='padding-left: 30px'>
							<select class="select_change" size="1" name="gSize" id="gSize">
							<option selected value="사이즈선택">사이즈선택</option>
							<option value="L">L</option>
							<option value="M">M</option>
							<option value="S">S</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'><select
								class="select_change" size="1" name="gColor"
								id="gColor">
							<option selected value="색상선택">색상선택</option>
							<option value="navy">navy</option>
							<option value="black">black</option>
							<option value="ivory">ivory</option>
							<option value="white">white</option>
							<option value="gray">gray</option>
						</select></td>
					</tr>

					<tr>
						<td class="td_title">주문수량</td>
						<td style="padding-left: 30px"><input type="text"
															  name="gAmount" value="1" id="gAmount"
															  style="text-align: right; height: 18px"> <img
								src="/images/up.PNG" id="up"> <img src="/images/down.png"
																  id="down"></td>
					</tr>
				</table>


			</td>
		</tr>
	</table>
	<%
		GoodsDTO goodsDTO = (GoodsDTO)request.getAttribute("goodsDTO");
		String gCode = goodsDTO.getGCode();
		System.out.println("gCode = " + gCode);
	%>
	<div style="text-align: center;">
		<button class="btn btn-primary" id="wishlist-button" data-code="<%=gCode%>"><%
			boolean itemWishlisted = (boolean) request.getAttribute("itemWishlisted");
			if (itemWishlisted) { %>
			<i class="fas fa-heart"></i>

			<%} else {%>
			<i class="far fa-heart"></i>
			<%}%></button>
		<button class="btn btn-primary" >구매</button>
		<button class="btn btn-primary" id="cart">장바구니</button>
	</div>
</form>