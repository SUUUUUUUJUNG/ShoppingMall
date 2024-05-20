<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function () {

            $("#allCheck").on("click", function () {
                var isChecked = $(this).is(":checked");
                $(".check").prop("checked", isChecked);
                totalResult();
            });

            $("#process-selected-items").on("click", function () {
                let url = "/order?";
                var selectedCartIds = $(".check:checked").map(function () {
                    url += "cartId=" + $(this).val() + "&";
                    return $(this).val();
                }).get();

                if (selectedCartIds.length === 0) {
                    alert("주문할 항목을 선택해 주세요.");
                    return;
                }

                location.href = url;
            });

            $(".check").on("click", function () {
                var cartid = $(this).val();
                var sumValue = $("#sum" + cartid).text();
                var cartAmountValue = $("#cartAmount" + cartid).val();
                var gPriceValue = $("#gPrice" + cartid).data("price");

                console.log("sum" + cartid + ": " + sumValue);
                console.log("cartAmount" + cartid + ": " + cartAmountValue);
                console.log("gPrice" + cartid + ": " + gPriceValue);

                totalResult();
            });

            $(".update-Btn").on("click", function () {
                let cartId = $(this).data("id");
                const id = '#cartAmount' + cartId;
                let gAmount = $(id).val();
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
                        console.log(data);
                        $("#sum" + cartId).text(total);
                        totalResult();
                        alert("수정 되었습니다.");
                    },
                    error: function (xhr, status, error) {
                        console.log(error);
                    }
                });
            });

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
                        totalResult();
                        alert("상품이 삭제되었습니다.");
                    },
                    error: function (xhr, status, error) {
                        console.log(error);
                    }
                });
            });

            function totalResult() {
                var total = 0;
                $(".check:checked").each(function () {
                    var cartId = $(this).val();
                    total += parseFloat($("#sum" + cartId).text());
                });
                $("#totalResult").text(total);
            }

            $("#delAllCart").on("click", function () {
                $("form").attr("action", "loginCheck/delAllCart");
                $("form").submit();
            });

            $(".orderBtn").on("click", function () {
                let cartId = $(this).data("id");
                $.ajax({
                    url: "/order",
                    type: "GET",
                    data: {
                        cartId: cartId
                    },
                    success: function (data, status, xhr) {
                        console.log("success");
                    },
                    error: function (xhr, status, error) {
                        console.log(error);
                    }
                });
            });

            totalResult(); // Initial calculation
        });
    </script>
</head>
<body>
<div class="container">
    <table class="table">
        <thead class="header-row">
        <tr>
            <th scope="col"><input type="checkbox" name="allCheck" id="allCheck"> 전체선택</th>
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
                <td><img src="/images/items/${dto.GImage}.gif" border="0" align="center" width="80"/></td>
                <td>${dto.GName} <br> [옵션: 사이즈(${dto.GSize}), 색상(${dto.GColor})]</td>
                <td id="gPrice${dto.cartId}" data-price="${dto.GPrice}">${dto.GPrice}</td>
                <td><input type="text" name="cartAmount" id="cartAmount${dto.cartId}" value="${dto.GAmount}"></td>
                <td><button class="btn btn-primary btn-sm update-Btn" data-id="${dto.cartId}" data-price="${dto.GPrice}">수정</button></td>
                <td id="sum${dto.cartId}" class="sum">${dto.GAmount * dto.GPrice}</td>
                <td><a class="btn btn-primary btn-sm orderBtn" href="/order?cartId=${dto.cartId}">주문</a></td>
                <td><button class="btn btn-danger btn-sm delBtn" data-id="${dto.cartId}">삭제</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-between">
        <button id="process-selected-items" class="btn btn-primary">선택주문</button>
        <span class="font-weight-bold">총 합계: <span id="totalResult">0</span> 원</span>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
