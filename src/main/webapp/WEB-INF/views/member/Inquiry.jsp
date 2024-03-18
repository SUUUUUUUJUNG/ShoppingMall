<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>문의하기</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- 추가적인 CSS -->
    <style>
        .container {
            margin-top: 20px;
        }
        .inquiry-form {
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="inquiry-form">
        <h2>문의하기</h2>
        <form action="SubmitInquiry" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="inquiryType">문의 유형을 선택해주세요</label>
                <select class="form-control" id="inquiryType" name="inquiryType">
                    <option>주문/배송 문의</option>
                    <option>제품 문의</option>
                    <option>환불 문의</option>
                    <option>기타 문의</option>
                </select>
            </div>
            <div class="form-group">
                <label for="inquiryContent">문의 내용을 입력해주세요</label>
                <textarea class="form-control" id="inquiryContent" name="inquiryContent" rows="5"></textarea>
            </div>
            <div class="form-group">
                <label for="imageUpload">이미지 첨부 (20MB이하의 jpg, png, bmp, gif 파일)</label>
                <input type="file" class="form-control-file" id="imageUpload" name="image" accept="image/*">
            </div>
            <button type="submit" class="btn btn-primary">문의하기</button>
        </form>
    </div>
</div>

<!-- 부트스트랩 JS, Popper.js, 그리고 jQuery 링크 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
