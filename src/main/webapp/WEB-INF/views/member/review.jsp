<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

<script>
    $(document).ready(function() {
        $.ajax({
            type: 'GET',
            url: '/api/review/member', // 요청을 보낼 서버의 URL 주소
            dataType: 'json', // 서버에서 보내줄 데이터의 타입
            success: function(reviews) {
                // 서버로부터 응답을 성공적으로 받았을 때 실행할 코드
                var tableBody = $('#reviewsTable tbody');
                reviews.forEach(function(review) {
                    var row = $('<tr></tr>');
                    row.append($('<td></td>').text(review.review_Id));
                    var link = $('<a></a>').attr('href', '/goods/detail?gCode=' + review.gcode).text(review.gcode);
                    var td = $('<td></td>').append(link);
                    row.append(td);
                    row.append($('<td></td>').text(review.review_Text));
                    row.append($('<td></td>').text(review.rating));
                    row.append($('<td></td>').text(review.createdAt));
                    tableBody.append(row);
                });
            },
            error: function(xhr, status, error) {
                // 요청 실패시 실행할 코드
                console.error("Error: " + error);
                alert("리뷰 데이터를 불러오는 데 실패했습니다.");
            }
        });
    });
</script>
</body>
</html>
