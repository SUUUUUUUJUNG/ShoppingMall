<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>

    .nav-pills .nav-link {
        border-radius: 0.25rem;
        background-color: #f8f9fa;
        color: #495057;
    }

    .nav-pills .nav-link.active {
        background-color: #0062cc;
        color: white;
    }

    /* 카드 스타일 */
    .card-custom {
        background: #ffffff;
        border: none;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
        transition: transform 0.3s ease-in-out;
    }

    .card-custom:hover {
        transform: translateY(-5px);
    }

    .card-header-custom {
        background: #f8f9fa;
        color: #333;
        padding: 10px 20px;
        border-bottom: 1px solid #e0e0e0;
        font-size: 16px;
    }

    .card-body-custom {
        padding: 20px;
        line-height: 1.5;
        color: #666;
    }

    .card-title-custom {
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .card-text-custom {
        font-size: 14px;
    }

    .btn-custom {
        padding: 5px 10px;
        font-size: 12px;
        border-radius: 5px;
    }

    .btn-primary-custom {
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-primary-custom:hover {
        background-color: #0056b3;
        border-color: #004085;
    }

    .btn-danger-custom {
        background-color: #dc3545;
        border-color: #dc3545;
    }

    .btn-danger-custom:hover {
        background-color: #c82333;
        border-color: #bd2130;
    }


    .modal-content {
        border-radius: 0.3rem;
    }

    .btn-custom {
        background-color: #0062cc;
        color: white;
    }

    .btn-custom:hover {
        background-color: #004885;
    }

    .btn-outline-custom {
        border-color: #0062cc;
        color: #0062cc;
    }

    .btn-outline-custom:hover {
        background-color: #0062cc;
        color: white;
    }

    .form-control, .form-select {
        border-radius: 0.25rem;
    }

    .form-label {
        font-weight: 500;
    }
    .shadow {
        box-shadow: 0 4px 8px rgba(0, 128, 0, 0.1);
    }
</style>
<div class="container">
    <!-- 탭 버튼들 -->
    <ul class="nav nav-pills nav-fill">
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
    <!-- 탭 내용 -->
    <div class="tab-content">
        <div class="tab-pane active" id="tab1">
            <!-- 탭 #1 내용 -->
            <p class="col-md-8 offset-md-3 shadow">${goodsDTO.GContent}</p>
        </div>
        <div class="tab-pane" id="tab3">
            <!-- 탭 #3 내용 -->
            <p id="product-reviews">구매후기의 내용이 여기에 표시됩니다.</p>
            <form id="reviewForm">
                <textarea class="form-control" id="review_Text" placeholder="리뷰내용을 입력하세요"></textarea>
                <input type="number" min="1" max="5" id="rating">
                <button>전송하기</button>
            </form>
        </div>
        <div class="tab-pane" id="tab4">
            <!-- 탭 #3 내용 -->
            <p id="product-inquiries">상품문의의 내용이 여기에 표시됩니다.</p>

            <!-- 문의하기 버튼 -->
            <button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#inquiryModal">문의하기</button>
        </div>
    </div>
</div>

<!-- 문의하기 모달 -->
<div class="modal fade" id="inquiryModal" tabindex="-1" role="dialog" aria-labelledby="inquiryModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inquiryModalLabel">상품 문의</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 여기에 폼 내용을 추가 -->
                <form id="inquiryForm">
                    <!-- 폼 필드 예시 -->
                    <div class="form-group">
                        <label for="inquiry_Content">문의내용</label>
                        <textarea class="form-control" id="inquiry_Content" placeholder="문의 내용을 입력하세요"></textarea>
                    </div>
                    <input type="hidden" id="gCode" value="${param.gCode}">
                    <!-- 추가 필드 추가 가능 -->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="submit">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- 문의 수정 모달 -->
<div class="modal fade" id="inquiryUpdateModal" tabindex="-1" role="dialog"
     aria-labelledby="inquiryModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inquiryUpdateModalLabel">상품 문의</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 여기에 폼 내용을 추가 -->
                <form id="inquiryUpdateForm">
                    <!-- 폼 필드 예시 -->
                    <div class="form-group">
                        <label for="inquiry_Content">문의내용</label>
                        <textarea class="form-control" id="inquiry_Update_Content"
                                  placeholder="문의 내용을 입력하세요"></textarea>
                    </div>
                    <input type="hidden" id="inquiry_id">
                    <!-- 추가 필드 추가 가능 -->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update-modal-btn">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- 리뷰 수정 모달 -->
<div class="modal fade" id="reviewUpdateModal" tabindex="-1" role="dialog"
     aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reviewUpdateModalLabel">리뷰 수정</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <!-- 여기에 폼 내용을 추가 -->
                <form id="reviewUpdateForm">
                    <!-- 폼 필드 예시 -->
                    <div class="form-group">
                        <label for="review_Update_Content">리뷰 내용</label>
                        <textarea class="form-control" id="review_Update_Content" placeholder="리뷰 내용을 입력하세요"></textarea>
                    </div>
                    <input type="number" min="1" max="5" id="update-rating">
                    <input type="hidden" id="reviewId">
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="review-update-modal-btn">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var gCode = $("#gCode").val();
        inquiryLoad();

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
                success: function (data, status, xhr) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

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
                success: function (data, status, xhr) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

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

        $(document).on('click', ".update-btn", function () {
            let id = $(this).data("id");
            $("#inquiry_id").val(id);

            $.ajax({
                url: "/api/productInquiry/one?inquiryId=" + id,
                type: "get",
                success: function (data) {
                    $("#inquiry_Update_Content").val(data.inquiry_Content);
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        $(document).on('click', ".review-update-btn", function () {
            let id = $(this).data("id");
            $("#reviewId").val(id);

            $.ajax({
                url: "/api/review/one?reviewId=" + id,
                type: "get",
                success: function (data) {
                    $("#review_Update_Content").val(data.review_Text);
                    $("#update-rating").val(data.rating);
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        $(document).on('click', "#review-update-modal-btn", function () {  //리뷰 수정
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
                success: function (data, status, xhr) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });

        //update-modal-btn
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
                success: function (data, status, xhr) {
                    alert(data.message);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });
        });


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
						'<h5 class="card-title card-title-custom">문의 내용</h5>' +
						'<p class="card-text card-text-custom">' + inquiry.inquiry_Content + '</p>' +
						'<div>' +
						'<button class="btn btn-danger-custom btn-custom review-delete-btn" data-id="' + inquiry.inquiry_Id + '">삭제</button> ' +
						'<button class="btn btn-primary-custom btn-custom update-btn" data-bs-toggle="modal" data-bs-target="#inquiryUpdateModal" data-id="' + inquiry.inquiry_Id + '">수정</button>' +
						'</div>' +
						'</div>' +
						'</div>';
				$('#product-inquiries').append(inquiryHtml);
			});
        }

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
						'<h5 class="card-title card-title-custom">리뷰 내용</h5>' +
						'<p class="card-text card-text-custom">' + review.review_Text + '</p>' +
						'<p class="card-text card-text-custom">평점 : ' + review.rating + '</p>' +
						'<div>' +
						'<button class="btn btn-danger-custom btn-custom review-delete-btn" data-id="' + review.review_Id + '">삭제</button> ' +
						'<button class="btn btn-primary-custom btn-custom review-update-btn" data-bs-toggle="modal" data-bs-target="#reviewUpdateModal" data-id="' + review.review_Id + '">수정</button>' +
						'</div>' +
						'</div>' +
						'</div>';
				$('#product-reviews').append(reviewHtml);
			});
		}
    });
</script>
