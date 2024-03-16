<%@ page import="com.shoppingmall.dto.WishListDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <title>찜목록</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(function(){
            $(".cart-btn").on("click",function(){
                let gCode = $(this).data("code");
                $("#gCode").val(gCode);
                console.log("gCode",gCode);
            });//end click cart-btn

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

               var gCode = $("#gCode").val();
               var gAmount = $("#gAmount").val();

               const data = {
                   gCode:gCode,
                   gSize:gSize,
                   gColor:gColor,
                   gAmount:gAmount
               }

               console.log("data",data);

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

            });//end click cart

            $(".delete-btn").on("click",function(){
                let wishListId = $(this).data("id");
                let buttonClicked = $(this);

                console.log(wishListId);

                if(confirm("이 항목을 삭제하시겠습니까?")){
                    $.ajax({
                        url:"/api/wishList/delete",
                        type:"DELETE",
                        data:{wishListId:wishListId},
                        success:function (response){
                            alert("상품이 삭제되었습니다.");
                            buttonClicked.closest("tr").remove();
                        },
                        error:function (xhr,status,error){
                            alert("삭제 중 오류가 발생했습니다.");
                        }
                    })
                }
            });//end click


        })
    </script>
    <style>
        .item-image {
            width: 100px;
            height: auto;
        }
        .button-group a {
            margin-bottom: 4px;
            width: 100px; /* 버튼의 너비 조정 */
        }
        .button-group a:last-child {
            margin-bottom: 0;
        }
        .table-no-border td, .table-no-border th {
            border-top: none !important;
        }
        .text-details {
            vertical-align: middle;
        }

        .button-group{
            width: 75%;
            padding: 10px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>찜목록</h2>
    <%
        List<WishListDTO> wishListItems = (List<WishListDTO>)request.getAttribute("wishListItems");
        if(wishListItems != null && !wishListItems.isEmpty()) {
    %>
    <table class="table table-no-border">
        <%
            for(WishListDTO item : wishListItems) {
        %>
        <tr>
            <td class="text-details"><%= item.getGCode() %></td>
            <td>
                <a href="/goods/detail?gCode=<%= item.getGCode() %>">
                    <img src="<%= request.getContextPath() + "/images/items/" + item.getGImage() + ".gif" %>" alt="<%= item.getGName() %>" class="item-image"/>
                </a>
            </td>
            <td class="text-details">
                <strong><%= item.getGName() %></strong><br>
                <%= item.getGPrice() %>원
            </td>
            <td class="text-details">
                <div class="button-group">
<%--                    <a href="/addToCart?wishListId=<%= item.getWishListId() %>" class="btn btn-primary btn-block">장바구니</a>--%>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary cart-btn button-group" data-toggle="modal" data-target="#exampleModal" data-code="<%=item.getGCode()%>">
        장바구니
    </button>

    <!-- Modal -->
    <form action="">
        <input type="hidden" name="gCode" id="gCode" value="<%=item.getGCode()%>">
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">상품 옵션 선택</h1>
                    <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Size Selection -->
                    <div class="form-group">
                        <select class="form-control select_change" id="gSize">
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                        </select>
                    </div>
                    <!-- Color Selection -->
                    <div class="form-group">
                        <label for="gColor">색상 선택</label>
                        <select class="form-control" id="gColor">
                            <option value="navy">navy</option>
                            <option value="black">black</option>
                            <option value="white">white</option>
                        </select>
                    </div>
                    <!-- Quantity Input -->
                    <div class="form-group">
                        <label for="gAmount">주문 수량</label>
                        <input type="number" class="form-control" id="gAmount" min="1" value="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="cart">장바구니 담기</button>
                    <button type="button" class="btn btn-primary">결제하기</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
    </form>

    <button type="button" class="btn btn-danger btn-block delete-btn button-group" data-id="<%= item.getWishListId() %>">삭제</button>
<%--    <a href="/removeFromWishList?wishListId=<%= item.getWishListId() %>" class="btn btn-danger btn-block delete.btn" data-id="<%=item.getWishListId()%>">삭제</a>--%>
                </div>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
    } else {
    %>
    <p>찜 목록이 비어있습니다.</p>
    <%
        }
    %>


</div>

