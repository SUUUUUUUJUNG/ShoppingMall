<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Bootstrap JS 추가 -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap CSS 추가 -->

<script>
    $(document).ready(function () {
        $(".check").on("click", function () {
            var cartid = $(this).val(); // 체크박스의 value 값 (dto.cartid)을 가져옴
            var sumValue = $("#sum" + cartid).text(); // 해당 항목의 합계
            var cartAmountValue = $("#cartAmount" + cartid).val(); // 해당 항목의 수량
            // 수정된 부분: 상품 가격을 가져오는 올바른 방식
            var gPriceValue = $("#gPrice" + cartid).attr("data-price"); // 해당 항목의 가격

            console.log("sum" + cartid + ": " + sumValue);
            console.log("cartAmount" + cartid + ": " + cartAmountValue);
            console.log("gPrice" + cartid + ": " + gPriceValue); // 수정된 부분: 변수 이름 일치
        });

        $(".update-Btn").on("click", function () {
            let cartId = $(this).data("id");
			const id = '#cartAmount' + cartId;
			let gAmount = $(id).val()
            let gPrice = $(this).data("price");
            let total = gAmount * gPrice;

            $.ajax({
                url: "/cart/update",
                type: "post",
                data: {
                    'cartId': cartId,
                    'gAmount': gAmount,
                    'gPrice': gPrice,
                    'total': total
                },
                success: function (data, status, xhr) {
                    console.log(data)
                    $("#sum" + cartId).text(total);
                    totalResult();
                },
                error: function (xhr, status, error) {
                    console.log(error)
                }
            })

        })

        //삭제 버튼 이벤트 처리
        $(".delBtn").on("click", function () {
            var cartId = $(this).data("id");
            var xxx = $(this);
            $.ajax({
                url: "/cart/delete",
                type: "get",
                datatype: "text",
                data: {
                    cartId: cartId
                },
                success: function (data, status, xhr) {
                    console.log("success");
                    xxx.parents().filter("tr").remove();
                    totalResult(); //화면 로딩 완료 후
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            })

        })
        totalResult();

        function totalResult() {
            var total = 0; // 총합계를 저장할 변수
            $(".sum").each(function () { // 모든 .sum 요소에 대해 반복
                total += parseFloat($(this).text()); // 각 항목의 합계를 총합계에 추가
            });
            $("#totalReuslt").text(total); // 총합계를 #totalReuslt 요소에 설정
        }


        $("#allCheck").on("click", function () {
            // #allCheck의 체크 상태에 따라 모든 .check 체크박스의 상태를 설정
            var isChecked = $(this).is(":checked"); // #allCheck의 상태 (true or false)
            $(".check").prop("checked", isChecked); // 모든 .check 체크박스의 상태를 isChecked와 동일하게 설정
        });

        $("#delAllCart").on("click", function () {
            $("form").attr("action", "loginCheck/delAllCart");
            $("form").submit();
        });

/*
        $(".orderBtn").on("click", function () {
            var cartId = $(this).data("id");
            $.ajax({
                url:"/order/confirm",
                type:"post",
                dataType:"text",
                data:{
                    cartId:cartId
                },
                success:function (data,status,xhr){
                    console.log("success");
                },
                error:function (xhr,status,error){
                    console.log(error);
                }
            });//end ajax
        })
*/


    });


</script>

<table width="90%" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td height="30">
    </tr>

    <tr>
        <td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
                size="5"><b>- 장바구니-</b></font> &nbsp;
        </td>
    </tr>

    <tr>
        <td height="15">
    </tr>

    <tr>
        <td colspan="10">
            <hr size="1" color="CCCCCC">
        </td>
    </tr>

    <tr>
        <td height="7">
    </tr>

    <tr>
        <td class="td_default" align="center">
            <input type="checkbox" onclick="allCheck(this)"
                   name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
        <td class="td_default" align="center"><strong>주문번호</strong></td>
        <td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
        <td class="td_default" align="center"><strong>판매가</strong></td>
        <td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
        <td class="td_default" align="center"><strong>합계</strong></td>
        <td></td>
    </tr>

    <tr>
        <td height="7">
    </tr>

    <tr>
        <td colspan="10">
            <hr size="1" color="CCCCCC">
        </td>
    </tr>

    <form name="myForm">
        <c:forEach var="dto" items="${cartList}">
            <tr>
                <td class="td_default" width="80">
                    <!-- checkbox는 체크된 값만 서블릿으로 넘어간다.
                    따라서 value에 삭제할 cartid값을 설정한다. -->
                    <input type="checkbox"
                           name="check" id="check${dto.cartId}" class="check"
                           value="${dto.cartId}"></td>
                <td class="td_default" width="80">${dto.cartId}</td>
                <td class="td_default" width="80"><img src="/images/items/${dto.GImage}.gif" border="0" align="center"
                                                       width="80"/></td>
                <td class="td_default" width="300" style='padding-left: 30px'>${dto.GName}
                    <br> <font size="2" color="#665b5f">[옵션 : 사이즈(${dto.GSize}) , 색상(${dto.GColor})] </font></td>
                <td class="td_default" align="center" width="110">
                    <span id="">${dto.GPrice}</span>
                </td>
                <td class="td_default" align="center" width="90">

                    <input class="input_default" type="text" name="cartAmount"
                           id="cartAmount${dto.cartId}" style="text-align: right" maxlength="3"
                           size="2" value="${dto.GAmount }"></input></td>


                <td><input type="button" value="수정"
                           class="update-Btn btn btn-primary btn-sm"
                           data-id="${dto.cartId}" data-amount="${dto.GAmount}"
                           data-price="${dto.GPrice}" id="gPrice${dto.cartId}"/></td>


                <td class="td_default" align="center" width="80"
                    style='padding-left: 5px'><span id="sum${dto.cartId}" class="sum">
                        ${dto.GAmount*dto.GPrice}
                </span></td>
                <td>
                    <a href="/order/confirm?cartId=${dto.cartId}" type="button" class="btn btn-primary btn-sm" ${dto.cartId}>주문</a></td>
                <td class="td_default" align="center" width="30"
                    style='padding-left: 10px'>
                    <input type="button" value="삭제"
                           class="delBtn btn btn-primary btn-sm" data-id="${dto.cartId}"></td>
                <td height="10"></td>
            </tr>
            <!-- 반복끝 -->
        </c:forEach>

    </form>
    <tr>
        <td colspan="10">
            총합 :
            <div id="totalReuslt"></div>

            <hr size="1" color="CCCCCC">
        </td>
    </tr>
    <tr>
        <td height="30">
    </tr>

    <tr>
        <td align="center" colspan="5">&nbsp;&nbsp;
            <button onclick="orderAllConfirm(myForm)" class="btn btn-primary btn-sm">전체 주문하기</button>
            <button id="delAllCart" class="btn btn-primary btn-sm">전체 삭제하기</button>
            <button id="continue-shopping" class="btn btn-primary btn-sm">계속 쇼핑하기</button>
        </td>
    </tr>
    <tr>
        <td height="20">
    </tr>

</table>
