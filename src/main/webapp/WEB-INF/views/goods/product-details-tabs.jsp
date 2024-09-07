<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9;
            font-family: 'Helvetica Neue', sans-serif;
        }

        .nav-pills .nav-link {
            border-radius: 0.5rem;
            background-color: #fff;
            color: #343a40;
            margin: 0 10px;
            transition: background-color 0.3s ease;
        }

        .nav-pills .nav-link.active {
            background-color: #007bff;
            color: white;
        }

        .card-custom {
            background-color: #ffffff;
            border: none;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            margin-bottom: 20px;
        }

        .card-custom:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.15);
        }

        .card-header-custom {
            background-color: #007bff;
            color: white;
            padding: 20px;
            border-bottom: none;
            font-size: 18px;
            border-top-left-radius: 12px;
            border-top-right-radius: 12px;
        }

        .card-body-custom {
            padding: 20px;
            line-height: 1.6;
            color: #333;
        }

        .card-title-custom {
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .card-text-custom {
            font-size: 16px;
        }

        .btn-custom {
            padding: 10px 20px;
            font-size: 14px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn-primary-custom {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
        }

        .btn-primary-custom:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .btn-danger-custom {
            background-color: #dc3545;
            border-color: #dc3545;
            color: white;
        }

        .btn-danger-custom:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .modal-content {
            border-radius: 0.5rem;
        }

        .form-control, .form-select {
            border-radius: 0.5rem;
        }

        .form-label {
            font-weight: 600;
        }

        .shadow {
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .tab-content p {
            font-size: 18px;
            color: #333;
        }

        /* Add smooth animation */
        .modal.fade .modal-dialog {
            transition: transform 0.3s ease-out;
            transform: translateY(-10px);
        }

        .modal.fade.show .modal-dialog {
            transform: translateY(0);
        }
    </style>
</head>
<body>

<div class="container my-5">
    <ul class="nav nav-pills nav-fill mb-4">
        <li class="nav-item">
            <a class="nav-link active" href="#tab1" data-bs-toggle="tab">상품정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#tab3" data-bs-toggle="tab">구매후기</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#tab4" data-bs-toggle="tab">상품문의</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab1">
            <p class="col-md-8 offset-md-2 shadow p-3">${goodsDTO.GContent}</p>
        </div>
        <div class="tab-pane" id="tab3">
            <p id="product-reviews">구매후기의 내용이 여기에 표시됩니다.</p>
            <form id="reviewForm">
                <div class="mb-3">
                    <textarea class="form-control" id="review_Text" placeholder="리뷰 내용을 입력하세요" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <input type="number" min="1" max="5" id="rating" class="form-control" placeholder="평점">
                </div>
                <button type="submit" class="btn btn-primary-custom btn-custom">전송하기</button>
            </form>
        </div>
        <div class="tab-pane" id="tab4">
            <p id="product-inquiries">상품문의의 내용이 여기에 표시됩니다.</p>
            <button class="btn btn-primary-custom btn-custom" data-bs-toggle="modal" data-bs-target="#inquiryModal">문의하기</button>
        </div>
    </div>
</div>

<!-- 문의 모달 -->
<div class="modal fade" id="inquiryModal" tabindex="-1" aria-labelledby="inquiryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inquiryModalLabel">상품 문의</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="inquiryForm">
                    <div class="mb-3">
                        <label for="inquiry_Content" class="form-label">문의내용</label>
                        <textarea class="form-control" id="inquiry_Content" placeholder="문의 내용을 입력하세요" rows="3"></textarea>
                    </div>
                    <input type="hidden" id="gCode" value="${param.gCode}">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary-custom btn-custom" id="submit">확인</button>
                <button type="button" class="btn btn-secondary btn-custom" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- 리뷰 수정 모달 -->
<div class="modal fade" id="reviewUpdateModal" tabindex="-1" aria-labelledby="reviewUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reviewUpdateModalLabel">리뷰 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="reviewUpdateForm">
                    <div class="mb-3">
                        <label for="review_Update_Content" class="form-label">리뷰 내용</label>
                        <textarea class="form-control" id="review_Update_Content" placeholder="리뷰 내용을 입력하세요" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <input type="number" min="1" max="5" id="update-rating" class="form-control" placeholder="평점">
                    </div>
                    <input type="hidden" id="reviewId">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary-custom btn-custom" id="review-update-modal-btn">확인</button>
                <button type="button" class="btn btn-secondary btn-custom" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- Inquiry Update Modal -->
<div class="modal fade" id="inquiryUpdateModal" tabindex="-1" aria-labelledby="inquiryUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inquiryUpdateModalLabel">상품 문의 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="inquiryUpdateForm">
                    <div class="mb-3">
                        <label for="inquiry_Update_Content" class="form-label">문의 내용</label>
                        <textarea class="form-control" id="inquiry_Update_Content" placeholder="문의 내용을 입력하세요" rows="3"></textarea>
                    </div>
                    <input type="hidden" id="inquiry_id">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary-custom btn-custom" id="update-modal-btn">확인</button>
                <button type="button" class="btn btn-secondary btn-custom" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        var gCode = $("#gCode").val();
        inquiryLoad();

        // Submit Inquiry
        $("#submit").on("click", function () {
            var data = {
                "code": gCode,
                "inquiry_Content": $("#inquiry_Content").val()
            };

            $.ajax({
                url: "/api/productInquiry",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        // Submit Review
        $("#reviewForm").on("submit", function (e) {
            e.preventDefault();
            var data = {
                "code": gCode,
                "review_Text": $("#review_Text").val(),
                "rating": $("#rating").val()
            };

            $.ajax({
                url: "/api/review",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        // Load Inquiries and Reviews
        function inquiryLoad() {
            $.ajax({
                url: "/api/productInquiry?gCode=" + gCode,
                type: "get",
                success: function (data) {
                    displayInquiries(data);
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });

            $.ajax({
                url: "/api/review?gCode=" + gCode,
                type: "get",
                success: function (data) {
                    displayReviews(data);
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        }

        // Display Inquiries
        function displayInquiries(data) {
            $('#product-inquiries').empty();
            if (data.length === 0) {
                $('#product-inquiries').append('<p>문의가 없습니다.</p>');
                return;
            }
            data.forEach(function (inquiry) {
                var inquiryHtml = '<div class="card card-custom">' +
                    '<div class="card-header card-header-custom">' +
                    '문의 날짜: ' + inquiry.inquiry_Date +
                    '</div>' +
                    '<div class="card-body card-body-custom">' +
                    '<p class="card-text card-text-custom">' + inquiry.inquiry_Content + '</p>' +
                    '<button class="btn btn-danger-custom btn-custom delete-btn" data-id="' + inquiry.inquiry_Id + '">삭제</button>' +
                    '<button class="btn btn-primary-custom btn-custom update-btn" data-bs-toggle="modal" data-bs-target="#inquiryUpdateModal" data-id="' + inquiry.inquiry_Id + '">수정</button>' +
                    '</div>' +
                    '</div>';
                $('#product-inquiries').append(inquiryHtml);
            });
        }

        // Display Reviews
        function displayReviews(data) {
            $('#product-reviews').empty();
            if (data.length === 0) {
                $('#product-reviews').append('<p>리뷰가 없습니다.</p>');
                return;
            }
            data.forEach(function (review) {
                var reviewHtml = '<div class="card card-custom">' +
                    '<div class="card-header card-header-custom">' +
                    '리뷰 날짜: ' + review.created_At +
                    '</div>' +
                    '<div class="card-body card-body-custom">' +
                    '<p class="card-text card-text-custom">' + review.review_Text + '</p>' +
                    '<p class="card-text card-text-custom">평점 : ' + review.rating + '</p>' +
                    '<button class="btn btn-danger-custom btn-custom review-delete-btn" data-id="' + review.review_Id + '">삭제</button>' +
                    '<button class="btn btn-primary-custom btn-custom review-update-btn" data-bs-toggle="modal" data-bs-target="#reviewUpdateModal" data-id="' + review.review_Id + '">수정</button>' +
                    '</div>' +
                    '</div>';
                $('#product-reviews').append(reviewHtml);
            });
        }

        // Update Inquiry
        $(document).on('click', "#update-modal-btn", function () {
            let id = $("#inquiry_id").val();
            let content = $("#inquiry_Update_Content").val();
            let data = {
                "inquiry_Content": content,
                "inquiry_Id": id,
            }

            $.ajax({
                url: "/api/productInquiry",
                type: "patch",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        // Update Review
        $(document).on('click', "#review-update-modal-btn", function () {
            let id = $("#reviewId").val();
            let rating = $("#update-rating").val();
            let content = $("#review_Update_Content").val();
            let data = {
                "review_Text": content,
                "review_Id": id,
                "rating": rating
            }

            $.ajax({
                url: "/api/review",
                type: "patch",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        // Delete Inquiry
        $(document).on('click', ".delete-btn", function () {
            let id = $(this).data("id");

            $.ajax({
                url: "/api/productInquiry/" + id,
                type: "delete",
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });

        });

        // Delete Review
        $(document).on('click', ".review-delete-btn", function () {
            let id = $(this).data("id");

            $.ajax({
                url: "/api/review/" + id,
                type: "delete",
                success: function (data) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });

        });
    });
</script>
</body>
</html>
