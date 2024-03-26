<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>마이페이지 - 정보 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>

        <c:if test="${! empty mesg}">

        alert('${mesg}');

        </c:if>
        <% if(session.getAttribute("mesg")!=null){
            session.removeAttribute("mesg");
        } %>

    </script>
</head>

<div class="container mt-4">
    <div class="row">
        <!-- 사이드바 자리-->

        <!-- 메인 컨텐츠 시작 -->
        <div class="col-md-8 shadow">
            <div class="form-section">
                <h2 class="center-align">개인정보 수정</h2>
            </div>
            <form action="/myPage/update" method="post">
                <div class="form-section section-container">
                    <div class="section-title center-align" style="text-align: center">변경할 수 없는 정보</div>
                    <div class="section-content">
                        <div class="mb-3">
                            <label for="realName" class="form-label">이름</label>
                            <input type="text" class="form-control fc" id="realName" value="${login.username}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">이메일</label>
                            <input type="email" class="form-control fc" id="email" value="${login.email}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">휴대폰 번호</label>
                            <input type="text" class="form-control fc" id="phoneNumber" value="${login.phoneNumber}" disabled>
                        </div>
                    </div>
                </div>
                <div class="form-section section-container">
                    <div class="section-title center-align">비밀번호 변경</div>
                    <div class="section-content">
                        <div class="mb-3">
                            <label for="current-password" class="form-label">현재 비밀번호</label>
                            <input type="password" name="currentPassword" class="form-control fc" id="current-password" required>
                        </div>
                        <div class="center-align" style="margin-bottom: 15px; color: darkgreen;">(비밀번호는 영문/숫자/특수문자 2가지 이상 조합의 8~20자여야 합니다.)</div>
                        <div class="mb-3">
                            <label for="new-password" class="form-label">새 비밀번호</label>
                            <input type="password" class="form-control fc" id="new-password" th:onkeyup="newPasswordValidate()" required>
                        </div>
                        <div id="passwordHelpBlock" style="display: none;">
                            <p id="lengthCheck" class="text-danger">✖ 영문/숫자/특수문자 2가지 이상 조합 (8~20자)</p>
                            <p id="sequenceCheck" class="text-danger">✖ 3개 이상 연속되거나 동일한 문자/숫자 제외</p>
                            <p id="excludeUsername" class="text-danger">✖ 아이디(이메일) 제외</p>
                        </div>
                        <div class="mb-3">
                            <label for="new-password-check" class="form-label">다시 입력</label>
                            <input type="password" class="form-control fc" id="new-password-check" required>
                        </div>
                        <div class="center-align">
                            <p id="password-match-result"></p>
                        </div>
                    </div>
                </div>
                <div class="form-section section-container">
                    <div class="section-title center-align">주소 변경</div>
                    <div class="section-content">
                        <div class="mb-3">
                            <label for="address" class="form-label">주소</label>
                            <input type="text" class="form-control fc" id="address" name="address" value="${login.address}" required>
                        </div>
                        <div class="mb-3">
                            <label for="zipCode" class="form-label">우편 번호</label>
                            <input type="text" class="form-control fc" id="zipCode" value="${login.zip_Code}" name="zip_Code" readonly required>
                        </div>
                        <div class="mb-3">
                            <label for="detailAddress" class="form-label">상세 주소</label>
                            <input type="text" class="form-control fc" id="detailAddress" name="addr_Detail"
                                   value="${login.addr_Detail}" required>
                        </div>
                        <div class="mb-3 right-align">
                            <button type="button" class="btn btn-outline-secondary address-btn" onclick="searchAddress()">
                                주소 검색
                            </button>
                        </div>
                    </div>
                </div>
                <div class="center-align">
                    <button type="submit" class="btn btn-primary" onclick="return validateRegisterForm()">정보 수정</button>
                </div>
                <input type="hidden" name="userId" value="${login.userId}" id="userId">
            </form>
        </div>
        <!-- 메인 컨텐츠 끝 -->
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    $(document).ready(function() {

        $("form").submit(function(event) {
            event.preventDefault();

            updateValidation()

            var userData = {
                "userID":$("#userID").val(),
                "username": $("#username").val(),
                "password": $("#new-password").val(),
                "email": $("#email").val(),
                "realName": $("#realName").val(),
                "role": $("#role").val(),
                "userGrade": $("#userGrade").val(),
                "phoneNumber": $("#phoneNumber").val(),
                "address": $("#address").val(),
                "detailAddress": $("#detailAddress").val(),
                "zipCode": $("#zipCode").val(),
                "status": $("#status").val(),
                "currentPassword": $("#current-password").val()
            };

            $.ajax({
                type: "PATCH",
                url: "/api/users",
                contentType: "application/json",
                data: JSON.stringify(userData),
                dataType: "json",
                success: function(response) {
                    alert("사용자 정보가 업데이트되었습니다.");
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("업데이트에 실패했습니다: " + xhr.responseText);
                }
            });
        });

        $("#new-password, #new-password-check").on("input", function() {
            validatePasswordsMatch();
        });

        function updateValidation() {
            var newPassword = $("#new-password").val();
            var newPasswordCheck = $("#new-password-check").val();
            const password = $('#new-password').val();
            const address = $('#address').val();
            const detailAddress = $('#detailAddress').val();

            if (newPassword !== newPasswordCheck) {
                e.preventDefault();
                alert("새 비밀번호와 다시 입력한 비밀번호가 일치하지 않습니다.");
                $("#new-password-check").focus();
            }

            if (!validatePasswordComplexity(password)) {
                alert('비밀번호는 영문/숫자/특수문자 2가지 이상 조합의 8~20자여야 합니다.');
                return false;
            }

            if (!validateAddress(address) || !validateDetailAddress(detailAddress)) {
                alert('주소를 정확히 입력해주세요.');
                return false;
            }
        }

        // 비밀번호 일치 검사
        function validatePasswordsMatch() {
            const newPassword = $("#new-password").val();
            const newPasswordCheck = $("#new-password-check").val();
            if (newPassword === newPasswordCheck) {
                $("#password-match-result").text('✔ 비밀번호가 일치합니다.').css('color', 'green');
            } else {
                $("#password-match-result").text('✖ 비밀번호가 일치하지 않습니다.').css('color', 'red');
            }
            if (newPasswordCheck === '' && newPassword === '') {
                $("#password-match-result").text('');
            }
        }
        // 주소 검색
        function searchAddress() {
            new daum.Postcode({
                oncomplete: function(data) {
                    $('#address').val(data.address);
                    $('#zipCode').val(data.zonecode);
                    $('#detailAddress').focus();
                }
            }).open();
        }

        $(".address-btn").click(searchAddress);


        function validatePasswordComplexity(password) {
            const re = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W]).{8,20}$/;
            return re.test(password);
        }

        function validateAddress(address) {
            return address.trim().length > 0;
        }

        function validateDetailAddress(detailAddress) {
            return detailAddress.trim().length > 0;
        }
    });
</script>
</html>

