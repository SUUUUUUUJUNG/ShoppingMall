<%@ page import="com.shoppingmall.domain.dto.member.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- 추가적인 CSS -->

<script>
    $(function(){
        $("#send").on("click",function(e){
            e.preventDefault();

            let inquiryType = $("#inquiryType").val();
            let inquiryContent = $("#inquiryContent").val();
            let image = $("#imageUpload").val();
            let inquiryTitle = $("#inquiryTitle").val();
            let memberId = $("#memberId").val();

            const data = {
                inquiry_Type:inquiryType,
                inquiry_Content:inquiryContent,
                image:image,
                inquiry_Title:inquiryTitle,
                memberId:memberId
            }

            $.ajax({
                url:"/inquiry/submitInquiry",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(data),
                success:function (data,status,xhr){
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
        </form>
    </div>
</div>


