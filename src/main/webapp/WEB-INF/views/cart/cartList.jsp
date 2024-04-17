<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    body {
        background-color: #f4f4f4;
        font-family: 'Noto Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
    }

    .container {
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    .header-row {
        background: #007bff;
        color: white;
        border-radius: 6px;
        margin-bottom: 10px;
    }

    .btn-primary {
        background-color: #0069d9;
        border-color: #0062cc;
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }

    .btn-sm {
        padding: .25rem .5rem;
        font-size: .875rem;
        line-height: 1.5;
        border-radius: .2rem;
    }

    input[type='checkbox'] {
        cursor: pointer;
    }

    input[type="text"] {
        border: 1px solid #ddd;
        margin-right: 5px;
        width: 50px;
        text-align: center;
    }

    .table {
        width: 100%;
        margin-bottom: 1rem;
        color: #212529;
    }

    th {
        padding: .75rem;
        vertical-align: top;
        border-top: 1px solid #dee2e6;
    }

    td {
        padding: .75rem;
        vertical-align: top;
        border-top: 1px solid #dee2e6;
    }

    .td_default {
        background-color: #e9ecef;
        border: 1px solid #ced4da;
    }

    hr {
        margin-top: 1rem;
        margin-bottom: 1rem;
        border: 0;
        border-top: 1px solid rgba(0, 0, 0, 0.1);
    }
</style>

<script>
    $(document).ready(function () {

        $("#allCheck").on("click", function () {
            // #allCheck의 체크 상태에 따라 모든 .check 체크박스의 상태를 설정
            var isChecked = $(this).is(":checked"); // #allCheck의 상태 (true or false)
            $(".check").prop("checked", isChecked); // 모든 .check 체크박스의 상태를 isChecked와 동일하게 설정
        });

        $("#process-selected-items").on("click", function() {
            let url = "/order?"
            var selectedCartIds = $(".check:checked").map(function() {
                url += "cartId=" + $(this).val() + "&"
                return $(this).val();
            }).get();

            if (selectedCartIds.length === 0) {
                alert("주문할 항목을 선택해 주세요.");
                return;
            }

            location.href= url;

        });

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

        //수정 버튼 처리
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
                    alert("수정 되었습니다.")
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
                    alert("상품이 삭제되었습니다.")
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

        $("#delAllCart").on("click", function () {
            $("form").attr("action", "loginCheck/delAllCart");
            $("form").submit();
        });


        $(".orderBtn").on("click", function () {
            let cartId = $(this).data("id");
            $.ajax({
                url:"/order",
                type:"GET",
                data:{
                    cartId: cartId
                },
                success:function (data,status,xhr){
                    console.log("success");
                },
                error:function (xhr,status,error){
                    console.log(error);
                }
            });//end ajax
        })
    });
</script>
<div class="container">
    <table class="table">
        <thead class="header-row">
        <tr>
            <th scope="col"><input type="checkbox" name="allCheck" id="allCheck"> 전체선택</th>
            <th scope="col">주문번호</th>
            <th scope="col" colspan="2">상품정보</th>
            <th scope="col">판매가</th>
            <th scope="col" colspan="2">수량</th>
            <th scope="col">합계</th>
            <th scope="col">주문</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dto" items="${cartList}">
            <tr>
                <td><input type="checkbox" class="check" value="${dto.cartId}"></td>
                <td>${dto.cartId}</td>
                <td><img src="/images/items/${dto.GImage}.gif" border="0" align="center" width="80"/></td>
                <td>${dto.GName} <br> [옵션: 사이즈(${dto.GSize}), 색상(${dto.GColor})]</td>
                <td>${dto.GPrice}</td>
                <td><input type="text" name="cartAmount" id="cartAmount${dto.cartId}" value="${dto.GAmount}"></td>
                <td><button class="btn btn-primary btn-sm update-Btn" data-id="${dto.cartId}" data-price="${dto.GPrice}">수정</button></td>
                <td id="sum${dto.cartId}">${dto.GAmount * dto.GPrice}</td>
                <td><a class="btn btn-primary btn-sm" href="/order?cartId=${dto.cartId}">주문</a></td>
                <td><button class="btn btn-danger btn-sm delBtn" data-id="${dto.cartId}">삭제</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button id="process-selected-items">선택주문</button>
</div>

