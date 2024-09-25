<%@ page import="com.shoppingmall.domain.dto.member.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <!-- 추가적인 CSS -->
    <script>
        $(function () {
            $("#send").on("click", function (e) {
                e.preventDefault();

                let inquiryType = $("#inquiryType").val();
                let inquiryContent = $("#inquiryContent").val();
                let image = $("#imageUpload").val();
                let inquiryTitle = $("#inquiryTitle").val();
                let memberId = $("#memberId").val();

                const data = {
                    inquiry_Type: inquiryType,
                    inquiry_Content: inquiryContent,
                    image: image,
                    inquiry_Title: inquiryTitle,
                    memberId: memberId
                }

                $.ajax({
                    url: "/inquiry/submitInquiry",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (data, status, xhr) {
                        alert(data.message);
                        window.location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.log(error)
                    },
                })//end ajax
            });//end onClick
        });//end function
    </script>
    <!-- 추가적인 CSS -->
    <style>
        /* General Styles for Form */
        h2 {
            font-size: 1.75rem;
            font-weight: bold;
        }

        .form-group label {
            font-size: 1rem;
            font-weight: normal;
        }

        .form-control {
            font-size: 1rem;
        }

        /* Style for buttons */
        #send, .btn-primary {
            background-color: #0069d9;
            border-color: #0069d9;
            color: white;
            font-size: 1rem;
            font-weight: bold;
            width: 100px;
            height: 40px;
            border-radius: 5px;
        }

        #send:hover, .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        /* Style for file input */
        .form-control-file {
            display: block;
            margin-top: 10px;
            width: 100%;
        }

        /* Adjustments for sidebar and form container */
        .inquiry-form {
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>

    <div class="col-md-8 offset-md-3 shadow">
        <div class="container">
            <div class="inquiry-form">
                <h2>문의하기</h2>
                <form action="" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="memberId" id="memberId">
                    <div class="form-group">
                        <label for="inquiryType">문의 유형을 선택해주세요</label>
                        <select class="form-control" id="inquiryType" name="inquiryType">
                            <option value="delivery">주문/배송 문의</option>
                            <option value="product">제품 문의</option>
                            <option value="refund">환불 문의</option>
                            <option value="etc">기타 문의</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inquiryTitle">문의 제목을 입력해주세요</label><br/>
                        <input type="text" id="inquiryTitle" class="form-control" name="inquiryTitle"><br/>
                        <label for="inquiryContent">문의 내용을 입력해주세요</label>
                        <textarea class="form-control" id="inquiryContent" name="inquiryContent" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="imageUpload">이미지 첨부 (20MB이하의 jpg, png, bmp, gif 파일)</label>
                        <input type="file" class="form-control-file" id="imageUpload" name="image" accept="image/*">
                    </div>
                    <button type="submit" class="btn btn-primary" id="send">보내기</button>
                    <a href="/inquiry/inquiriesList" class="btn btn-primary">문의 내역</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
