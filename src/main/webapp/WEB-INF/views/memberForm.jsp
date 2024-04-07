<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            // 비밀번호 입력창 포커스 시 메시지 표시
            $("#password").on("focus", function() {
                $("#pwCheck").show().text("8자 이상의 비밀번호를 입력해주세요.");
            });

            // 비밀번호 입력 시 검증
            $("#password").on("keyup", function() {
                var password = $(this).val();
                // 비밀번호가 8자 이상일 경우 메시지 숨김
                if (password.length >= 8) {
                    $("#pwCheck").hide();
                } else {
                    // 8자 미만일 경우 메시지 다시 표시
                    $("#pwCheck").show().text("영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.");
                }
            });


            // 비밀번호 일치 여부 검사
            let idValid = false;

            $("#password, #password2").on("keyup", function () {
                var password = $("#password").val();
                var confirmPassword = $("#password2").val();

                // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
                if (password !== confirmPassword) {
                    $("#passwordMismatch").show().text("비밀번호가 일치하지 않습니다.");
                } else {
                    // 일치하는 경우
                    $("#passwordMismatch").hide();
                }
            });

            $("#username").on("keyup", function () {
                var userId = $(this).val();
                $.ajax({
                    url: "/idDuplicateCheck",
                    type: "post",
                    datatype: "json",
                    data: {userId: userId},
                    success: function (data) {
                        idValid = data.valid;
                        if (!idValid) {
                            $("#username").css("border-color", "red");
                            $("#idCheck").show().text("이미 사용중인 아이디입니다.");
                        } else {
                            $("#username").css("border-color", ""); // 테두리 색상 초기화
                            $("#idCheck").hide(); // 메시지 숨김
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error(error);
                    }
                });
            });

            // 폼 제출 이벤트
            $("form").on("submit", function (event) {
                var password = $("#password").val();
                var confirmPassword = $("#password2").val();


                if (password !== confirmPassword) {
                    alert("비밀번호가 일치하지 않습니다.");
                    event.preventDefault();
                    return;
                }

                if (!idValid) {
                    alert("아이디를 다시 확인해주세요");
                    event.preventDefault();
                    return;
                }
                alert("회원가입이 완료되었습니다.");
            });
        });
    </script>
    <style>
        body {
            background-color: #f5f5f5;
        }
        .form-signin {
            width: 100%;
            max-width: 430px;
            padding: 15px;
            margin: auto;
        }
        .form-signin .form-floating:focus-within {
            z-index: 2;
        }
        .form-signin input[type="text"],
        .form-signin input[type="email"],
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .form-signin button {
            margin-top: 10px;
        }
        /* 주소 검색 버튼 스타일 조정 */
        .address-btn {
            height: 58PX; /* form-control과 동일한 높이 설정 */
            border: 1px solid #ced4da; /* Bootstrap의 form-control 테두리 스타일과 일치시킴 */
            border-radius: 0 0.25rem 0.25rem 0; /* 오른쪽 끝에만 border-radius 적용 */
            margin-left: -1px; /* 버튼과 입력 필드 사이의 테두리 중복을 제거 */
            transform: translateY(-10px); /* 버튼을 위로 10px 이동 */
        }

        .input-group-text {
            padding: 0.375rem 0.75rem; /* 부트스트랩 기본 패딩과 일치시킵니다 */
            display: flex;
            align-items: center; /* 아이콘을 수직으로 가운데 정렬합니다 */
            justify-content: center; /* 아이콘을 수평으로 가운데 정렬합니다 */
            width: 60px;
            height: 58px;
        }

        .input-group .fas {
            font-size: 1.5rem; /* FontAwesome 아이콘의 기본 크기를 지정합니다 */
        }

        /* 인풋 필드의 높이와 아이콘 컨테이너의 높이를 일치시킵니다 */
        .form-control {
            height: calc(1.5em + 0.75rem + 2px);
        }

        /* 라벨이 인풋 필드 위로 올라가도록 조정합니다 */
        .form-floating > .form-control,
        .form-floating > label {
        }


        /* 입력 필드 초기 상태에서 라벨의 위치를 설정 */
        .form-floating > label {
            position: absolute;
            left: 0;
            top: 0;
            padding-left: 70px; /* 초기 상태에서 라벨을 오른쪽으로 이동 */
            padding-top: 1rem;
            pointer-events: none; /* 라벨에 마우스 이벤트가 발생하지 않도록 설정 */
            transition: all 0.2s ease; /* 부드러운 이동을 위한 트랜지션 */
            background: transparent;
        }

        .input-group-text {
            margin-right: -1px; /* 아이콘과 입력 필드 사이의 경계를 정리합니다 */
            border-right: 0; /* 오른쪽 테두리를 제거하여 입력 필드와 시각적으로 통합합니다 */
        }

        /* 아이콘의 크기나 위치가 여전히 문제가 있다면, 아이콘 컨테이너에 대한 추가 스타일 조정이 필요할 수 있습니다 */
        .input-group-text {
            display: flex;
            align-items: center;
            justify-content: center;
            /* 필요에 따라 padding, margin 조정 */
        }

    </style>
</head>
<body>
<div th:replace="~{common/navBar :: content}"></div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3></div>
                <div class="card-body">
                    <form method="post" class="form-signin needs-validation" >
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                            <input type="text" class="form-control" id="username" name="username" placeholder="아이디" required >
                            <label for="username">아이디</label>
                        </div>
                        <p id="idCheck" style="display: none; color: red">이미 사용중인 아이디입니다.</p>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon2"><i class="fas fa-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password"  placeholder=" " aria-label="비밀번호" aria-describedby="basic-addon1" required>
                            <label for="password">비밀번호</label>
                        </div>
                        <p id="pwCheck" style="display: none; color: red"></p>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon2_1"><i class="fas fa-lock"></i></span>
                            <input type="password2" class="form-control" id="password2" placeholder=" " aria-label="비밀번호 확인" aria-describedby="basic-addon1" required>
                            <label for="password2">비밀번호 확인</label>
                        </div>
                        <p id="passwordMismatch" style="display: none; color: red;"></p>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon"><i class="fas fa-id-card"></i></span>
                            <input type="text" class="form-control" id="realName" name="realName" placeholder="이름" required>
                            <label for="realName">이름</label>
                        </div>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon3"><i class="fas fa-envelope"></i></span>
                            <input type="email" class="form-control" id="email" name="email"  placeholder="이메일" required>
                            <label for="email">이메일</label>
                        </div>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon4"><i class="fas fa-phone"></i></span>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"  placeholder="휴대폰 번호" required>
                            <label for="phoneNumber">휴대폰 번호</label>
                        </div>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon5"><i class="fas fa-home"></i></span>
                            <input type="text" class="form-control" id="address" name="address"  placeholder="주소" required readonly>
                            <label for="address">주소</label>
                            <button type="button" class="btn btn-outline-secondary address-btn" onclick="searchAddress()">주소 검색</button>
                        </div>
                        <div class="input-group mb-3 form-floating">
                            <span class="input-group-text" id="basic-addon6"><i class="fas fa-building"></i></span>
                            <input type="text" class="form-control" id="addr_Detail" name="addr_Detail" placeholder="상세 주소">
                            <label for="addr_Detail">상세 주소</label>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon7"><i class="fas fa-users"></i></span>
                            <select class="form-select" id="role" name="role" >
                                <option value="ROLE_자영업자">test</option>
                                <option value="ROLE_판매자">test</option>
                            </select>
                        </div>
                        <input type="hidden" id="zip_Code" name="zip_Code">
                        <button type="submit" class="btn btn-primary btn-block">가입하기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    $(document).ready(function() {

        // 주소 검색
        $('.address-btn').click(function() {
            new daum.Postcode({
                oncomplete: function(data) {
                    console.log(data.zonecode);
                    $('#address').val(data.address);
                    $('#zip_Code').val(data.zonecode);
                    $('#detailAddress').focus();
                }
            }).open();
        });
    });

</script>
</body>
</html>
