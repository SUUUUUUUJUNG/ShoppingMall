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
            <td><img src="<%= request.getContextPath() + "/images/items/" + item.getGImage() + ".gif" %>" alt="<%= item.getGName() %>" class="item-image"/></td>
            <td class="text-details">
                <strong><%= item.getGName() %></strong><br>
                <%= item.getGPrice() %>원
            </td>
            <td class="text-details">
                <div class="button-group">
                    <a href="/addToCart?wishListId=<%= item.getWishListId() %>" class="btn btn-primary btn-block">장바구니</a>
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
