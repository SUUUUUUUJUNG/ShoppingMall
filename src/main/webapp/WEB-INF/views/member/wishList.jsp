<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.shoppingmall.domain.dto.WishListDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>찜목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script>
        $(function () {
            $(".cart-btn").on("click", function () {
                let gCode = $(this).data("code");
                $("#gCode").val(gCode);
                console.log("gCode", gCode);
            });

            $("#cart").on("click", function (e) {
                e.preventDefault();
                var gSize = $("#gSize").val();
                var gColor = $("#gColor").val();
                if (gSize === "사이즈선택" || gColor === "색상선택") {
                    alert("상품의 사이즈와 색상 옵션을 선택해주세요.");
                    return;
                }

                var gCode = $("#gCode").val();
                var gAmount = $("#gAmount").val();

                const data = {
                    gCode: gCode,
                    gSize: gSize,
                    gColor: gColor,
                    gAmount: gAmount
                }

                console.log("data", data);

                $.ajax({
                    url: "/cart/add",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (data, status, xhr) {
                        alert(data.message);
                        $("#cartModal").modal('hide');
                    },
                    error: function (xhr, status, error) {
                        console.log(error)
                    },
                })

            });

            $(".delete-btn").on("click", function () {
                let wishListId = $(this).data("id");
                let buttonClicked = $(this);

                console.log(wishListId);

                if (confirm("이 항목을 삭제하시겠습니까?")) {
                    $.ajax({
                        url: "/api/wishList/delete",
                        type: "DELETE",
                        data: {wishListId: wishListId},
                        success: function (response) {
                            alert("상품이 삭제되었습니다.");
                            buttonClicked.closest("tr").remove();
                        },
                        error: function (xhr, status, error) {
                            alert("삭제 중 오류가 발생했습니다.");
                        }
                    })
                }
            });

        })
    </script>
    <style>
        .item-image {
            width: 70px;
            height: auto;
        }

        .button-group {
            display: flex;
            gap: 10px;
        }

        .button-group .btn {
            width: 90px;
            text-align: center;
        }

        .text-details {
            vertical-align: middle;
        }

        .modal-footer .btn-primary {
            width: 120px;
        }

        /* 버튼 색상 변경 */
        .button-group .btn-primary {
            background-color: #007bff; /* 톤다운된 회색-파란색 */
            border-color: #007bff; /* 동일한 색상으로 경계 색상 */
        }

        .button-group .btn-primary:hover {
            background-color: #4e555b; /* 호버 시 좀 더 어두운 색상 */
            border-color: #4e555b;
        }

        .button-group .btn-danger {
            background-color: #e74c3c; /* 톤다운된 빨간색 */
            border-color: #e74c3c; /* 약간 더 어두운 경계 색상 */
        }

        .button-group .btn-danger:hover {
            background-color: #b52b27; /* 호버 시 좀 더 어두운 색상 */
            border-color: #a12722;
        }

    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-3 shadow">
                <h2>찜목록</h2>
                <%
                    List<WishListDTO> wishListItems = (List<WishListDTO>) request.getAttribute("wishListItems");
                    if (wishListItems != null && !wishListItems.isEmpty()) {
                %>
                <table class="table table-no-border">
                    <%
                        for (WishListDTO item : wishListItems) {
                    %>
                    <tr>
                        <td class="text-details"><%= item.getGCode() %></td>
                        <td>
                            <a href="/goods/detail?gCode=<%= item.getGCode() %>">
                                <img src="<%= request.getContextPath() + "/images/items/" + item.getGImage() + ".gif" %>"
                                     alt="<%= item.getGName() %>" class="item-image"/>
                            </a>
                        </td>
                        <td class="text-details">
                            <strong><%= item.getGName() %></strong><br>
                            <%= item.getGPrice() %>원
                        </td>
                        <td class="text-details">
                            <div class="button-group">
                                <button type="button" class="btn btn-primary cart-btn"
                                        data-code="<%= item.getGCode() %>" data-bs-toggle="modal"
                                        data-bs-target="#cartModal">
                                    장바구니
                                </button>
                                <button type="button" class="btn btn-danger delete-btn"
                                        data-id="<%= item.getWishListId() %>">삭제
                                </button>
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
        </div>
    </div>
</div>

<!-- Modal -->
<form action="">
    <input type="hidden" name="gCode" id="gCode">
    <div class="modal fade" id="cartModal" tabindex="-1"
         aria-labelledby="cartModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="cartModalLabel">상품 옵션 선택</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-group mb-3">
                        <label for="gSize">사이즈 선택</label>
                        <select class="form-control" id="gSize">
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="gColor">색상 선택</label>
                        <select class="form-control" id="gColor">
                            <option value="navy">네이비</option>
                            <option value="black">블랙</option>
                            <option value="ivory">아이보리</option>
                            <option value="white">화이트</option>
                            <option value="gray">그레이</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="gAmount">주문 수량</label>
                        <input type="number" class="form-control" id="gAmount" min="1" value="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="cart">장바구니 담기</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
