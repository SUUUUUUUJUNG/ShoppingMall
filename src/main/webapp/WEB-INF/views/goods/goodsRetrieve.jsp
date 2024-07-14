<%@ page import="com.shoppingmall.domain.dto.goods.GoodsDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.css"/>
    <link rel="stylesheet" href="/css/common.css">
    <style>
        .pink-theme {
            background-color: #f8d7da;
            color: #721c24;
        }

        .table-pink th {
            background-color: #f8d7da;
            color: #721c24;
        }

        .btn-pink {
            background-color: #ff85b2;
            border-color: #ff85b2;
        }

        .btn-pink:hover {
            background-color: #ff65a3;
            border-color: #ff65a3;
        }

        .card-custom {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.1);
            transition: 0.3s;
            border-radius: 5px;
        }

        .card-custom:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .rounded-image {
            border-radius: 10px;
        }

        .icon-button {
            background: none;
            border: none;
        }

        .modal-body button {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>

    <script>
        $(function () {
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
                    gAmount: gAmount,
                }

                $.ajax({
                    url: "/cart/add",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (data, status, xhr) {
                        alert(data.message);
                    },
                    error: function (xhr, status, error) {
                        console.log(error)
                    },
                })
            });

            $("#wishlist-button").on("click", function (e) {
                e.preventDefault();
                var code = $("#wishlist-button").data("code");
                $.ajax({
                    url: "/api/wishList",
                    type: "post",
                    data: {
                        gCode: code
                    },
                    success: function (data, status, xhr) {
                        let mesg = data.message;
                        alert(mesg);
                        if (mesg.includes("추가")) {
                            $("#wishlist-button").html('<i class="fas fa-heart"></i>');
                        }
                        if (mesg.includes("삭제")) {
                            $("#wishlist-button").html('<i class="far fa-heart"></i>');
                        }
                    },
                    error: function (xhr, status, error) {
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
                        <button type="button" class="icon-button float-end" title="공유하기" data-bs-toggle="modal" data-bs-target="#shareModal">
                            <i class="far fa-share-square"></i>
                        </button>
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
                        <div class="mb-3">
                            <label for="gAmount">수량:</label>
                            <input type="number" id="gAmount" name="gAmount" value="1" min="1" class="form-control form-control-sm">
                        </div>
                        <button class="btn btn-pink" id="wishlist-button" data-code="${goodsDTO.GCode}">
                            <% if ((boolean) request.getAttribute("itemWishlisted")) { %>
                            <i class="fas fa-heart"></i>
                            <% } else { %>
                            <i class="far fa-heart"></i>
                            <% } %>
                        </button>
                        <button class="btn btn-pink">구매하기</button>
                        <button class="btn btn-pink" id="cart">장바구니 추가</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <jsp:include page="../goods/product-details-tabs.jsp"></jsp:include>
</div>

<!-- 공유하기 모달 -->
<div class="modal fade" id="shareModal" tabindex="-1" aria-labelledby="shareModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="shareModalLabel">공유하기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <button id="copyUrlBtn" class="btn btn-outline-primary w-100">URL 복사하기</button>
                <button id="shareViaEmail" class="btn btn-outline-secondary w-100">메일로 공유하기</button>
                <button id="shareOnTwitter" class="btn btn-outline-info w-100">트위터로 공유하기</button>
                <button id="shareOnKakao" class="btn btn-outline-warning w-100">카카오톡으로 공유하기</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        Kakao.init('f49cdc6ab94e18091b69652084f67569');

        $("#shareOnKakao").on("click", function () {
            if (Kakao && Kakao.Link) {
                Kakao.Link.sendDefault({
                    objectType: 'feed',
                    content: {
                        title: $("#gName").val(),
                        description: '페이지 설명',
                        imageUrl: 'http://localhost:8092/images/logo.png',
                        link: {
                            mobileWebUrl: window.location.href,
                            webUrl: window.location.href
                        }
                    }
                });
            } else {
                console.error("Kakao SDK not loaded");
            }
        });

        $("#copyUrlBtn").on("click", function () {
            var url = window.location.href;
            navigator.clipboard.writeText(url).then(() => {
                alert("URL이 클립보드에 복사되었습니다.");
            });
        });

        $("#shareViaEmail").on("click", function () {
            var subject = encodeURIComponent("공유하고 싶은 페이지 제목");
            var body = encodeURIComponent(window.location.href + "\n\n이 페이지를 확인해보세요!");
            window.location.href = "mailto:?subject=" + subject + "&body=" + body;
        });

        $("#shareOnTwitter").on("click", function () {
            var tweetText = encodeURIComponent("이 페이지를 확인해보세요! " + window.location.href);
            var twitterUrl = "https://twitter.com/intent/tweet?text=" + tweetText;
            window.open(twitterUrl, '_blank');
        });
    });
</script>
</body>
</html>
