<%@ page import="com.shoppingmall.dto.WishListDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>찜목록</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        장바구니
    </button>

    <!-- Modal -->
    <form action="">
        <input type="hidden" name="gCode" value="">
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
                        <label for="size-selection">사이즈 선택</label>
                        <select class="form-control" id="size-selection">
                            <option>S</option>
                            <option>M</option>
                            <option>L</option>
                        </select>
                    </div>
                    <!-- Color Selection -->
                    <div class="form-group">
                        <label for="color-selection">색상 선택</label>
                        <select class="form-control" id="color-selection">
                            <option>navy</option>
                            <option>black</option>
                            <option>white</option>
                        </select>
                    </div>
                    <!-- Quantity Input -->
                    <div class="form-group">
                        <label for="quantity-input">주문 수량</label>
                        <input type="number" class="form-control" id="quantity-input" min="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">장바구니 담기</button>
                    <button type="button" class="btn btn-primary">결제하기</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
    </form>

    <a href="/removeFromWishList?wishListId=<%= item.getWishListId() %>" class="btn btn-danger btn-block">삭제</a>
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
</body>
</html>
