<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 관리</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>

    <div class="container mt-4">
        <div class="row">
            <!-- 사이드바 자리-->

            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-8 offset-md-3 shadow">
                <h2>내 리뷰 관리</h2>
                <table id="reviewsTable" class="table">
                    <thead>
                    <tr>
                        <th>리뷰 ID</th>
                        <th>상품 코드</th>
                        <th>리뷰 내용</th>
                        <th>평점</th>
                        <th>작성 시간</th>
                        <th>작업</th> <!-- 작업 헤더 추가 -->
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 리뷰 데이터가 여기에 동적으로 삽입됩니다 -->
                    </tbody>
                </table>
            </div>
            <!-- 메인 컨텐츠 끝 -->
        </div>
    </div>
</div>
<!-- 리뷰 수정 모달 -->
<div class="modal fade" id="editReviewModal" tabindex="-1" aria-labelledby="editReviewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editReviewModalLabel">리뷰 수정</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editReviewForm">
                    <div class="form-group">
                        <label for="reviewText">리뷰 내용</label>
                        <textarea class="form-control" id="reviewText" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="reviewRating">평점</label>
                        <select class="form-control" id="reviewRating">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>
                </form>
            </div>
            <input type="hidden" id="reviewId">
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="saveChanges">저장</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        function editReview(reviewId) {

            $.ajax({
                type: 'get', // 또는 'PUT'
                url: '/api/review/one?reviewId=' + reviewId,
                success: function(response) {
                    $('#reviewId').val(response.review_Id)
                    $('#reviewText').val(response.review_Text);
                    $('#reviewRating').val(response.rating);
                },
                error: function(xhr, status, error) {
                    alert("리뷰 수정에 실패했습니다.");
                }
            });
        }
        $('#saveChanges').off('click').on('click', function() {
            var reviewId = $('#reviewId').val();
            $.ajax({
                type: 'PATCH', // 또는 'PUT'
                url: '/api/review',
                contentType: "application/json",
                data:JSON.stringify({
                    review_Id: reviewId,
                    review_Text: $('#reviewText').val(),
                    rating: $('#reviewRating').val()
                }),
                success: function(response) {
                    alert("리뷰가 성공적으로 수정되었습니다.");
                    $('#editReviewModal').modal('hide'); // 모달창 숨기기
                    window.location.reload();
                    // 여기에 페이지를 새로고침하거나, 테이블의 데이터를 업데이트하는 로직을 추가할 수 있습니다.
                },
                error: function(xhr, status, error) {
                    alert("리뷰 수정에 실패했습니다.");
                }
            });
        });
        function deleteReview(reviewId, rowElement) {
            console.log("리뷰 삭제:", reviewId);
            // AJAX 요청을 통해 리뷰를 삭제
            $.ajax({
                type: 'DELETE',
                url: '/api/review/' + reviewId, // 리뷰 ID를 이용한 삭제 요청 경로
                success: function (response) {
                    console.log(response.message);
                    alert("리뷰가 성공적으로 삭제되었습니다.");
                    // 삭제 성공 시 테이블에서 해당 행을 제거
                    rowElement.remove();
                },
                error: function (xhr, status, error) {
                    console.error("삭제 실패: ", error);
                    alert("리뷰 삭제에 실패하였습니다.");
                }
            });
        }

        $.ajax({
            type: 'GET',
            url: '/api/review/member', // 요청을 보낼 서버의 URL 주소
            dataType: 'json', // 서버에서 보내줄 데이터의 타입
            success: function (reviews) {
                // 서버로부터 응답을 성공적으로 받았을 때 실행할 코드
                var tableBody = $('#reviewsTable tbody');
                reviews.forEach(function (review) {
                    var row = $('<tr></tr>');
                    row.append($('<td></td>').text(review.review_Id));
                    var link = $('<a></a>').attr('href', '/goods/detail?gCode=' + review.gcode).text(review.gcode);
                    var td = $('<td></td>').append(link);
                    row.append(td);
                    row.append($('<td></td>').text(review.review_Text));
                    row.append($('<td></td>').text(review.rating));
                    row.append($('<td></td>').text(review.created_At));

                    // 수정 및 삭제 버튼 - Bootstrap 스타일 적용
                    var editButton = $('<button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editReviewModal"></button>').text('수정').click(function () {
                        editReview(review.review_Id); // 수정 버튼 클릭 이벤트
                    });
                    var deleteButton = $('<button class="btn btn-danger btn-sm"></button>').text('삭제').click(function() {
                        deleteReview(review.review_Id, row); // 삭제 버튼 클릭 이벤트
                    });
                    var actionTd = $('<td></td>').append(editButton, ' ', deleteButton); // 공간 추가
                    row.append(actionTd);

                    tableBody.append(row);
                });
            },
            error: function (xhr, status, error) {
                // 요청 실패시 실행할 코드
                console.error("Error: " + error);
                alert("리뷰 데이터를 불러오는 데 실패했습니다.");
            }
        });
    });
</script>
</body>
</html>
