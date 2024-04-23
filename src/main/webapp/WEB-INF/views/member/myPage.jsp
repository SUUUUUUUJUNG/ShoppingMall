<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<script>

    <c:if test="${! empty mesg}">

    alert('${mesg}');

    </c:if>
    <% if(session.getAttribute("mesg")!=null){
        session.removeAttribute("mesg");
    } %>

</script>
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
                <div class="form-section">
                    <h2 class="center-align">개인정보 수정</h2>
                </div>
                <form id="update-form">
                    <div class="form-section section-container">
                        <div class="section-title center-align" style="text-align: center">변경할 수 없는 정보</div>
                        <div class="section-content">
                            <div class="mb-3">
                                <label for="realName" class="form-label">이름</label>
                                <input type="text" class="form-control fc" id="realName" value="${member.realName}" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">이메일</label>
                                <input type="email" class="form-control fc" id="email" value="${member.email}" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="phoneNumber" class="form-label">휴대폰 번호</label>
                                <input type="text" class="form-control fc" id="phoneNumber" value="${member.phoneNumber}" disabled>
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
                                <input type="password" name="newPassword" class="form-control fc" id="new-password" th:onkeyup="newPasswordValidate()" required>
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
                                <input type="text" class="form-control fc" id="address" name="address" value="${member.address}" required>
                            </div>
                            <div class="mb-3">
                                <label for="zipCode" class="form-label">우편 번호</label>
                                <input type="text" class="form-control fc" id="zipCode" value="${member.zip_Code}" name="zip_Code" readonly required>
                            </div>
                            <div class="mb-3">
                                <label for="detailAddress" class="form-label">상세 주소</label>
                                <input type="text" class="form-control fc" id="detailAddress" name="addr_Detail"
                                       value="${member.addr_Detail}" required>
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
                    <input type="hidden" name="memberId" value="${login.memberId}" id="memberId">
                </form>
            </div>
            <!-- 메인 컨텐츠 끝 -->
        </div>
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
                "memberId":$("#memberId").val(),
                "newPassword": $("#new-password").val(),
                "address": $("#address").val(),
                "addr_Detail": $("#detailAddress").val(),
                "zip_Code": $("#zipCode").val(),
                "currentPassword": $("#current-password").val()
            };
            console.log(userData);

            $.ajax({
                type: "PATCH",
                url: "/api/member",
                contentType: "application/json",
                data: JSON.stringify(userData),
                dataType: "json",
                success: function(response) {
                    alert("사용자 정보가 업데이트되었습니다.");
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    var errorMessage = JSON.parse(xhr.responseText).message;
                    alert(errorMessage);
                }
            });
        });

        $("#new-password, #new-password-check").on("input", function() {
            validatePasswordsMatch();
        });

        function updateValidation() {
            const newPassword = $("#new-password").val();
            const newPasswordCheck = $("#new-password-check").val();
            const address = $('#address').val();
            const detailAddress = $('#detailAddress').val();

            if (newPassword !== newPasswordCheck) {
                e.preventDefault();
                alert("새 비밀번호와 다시 입력한 비밀번호가 일치하지 않습니다.");
                $("#new-password-check").focus();
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

        function validateAddress(address) {
            return address.trim().length > 0;
        }

        function validateDetailAddress(detailAddress) {
            return detailAddress.trim().length > 0;
        }
    });
</script>
</html>