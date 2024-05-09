<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }

        .login-container {
            max-width: 400px;
            margin: 5% auto;
            padding: 2rem;
            /*background: #fff0f3; !* 컨테이너 배경색을 더 연한 파스텔 핑크로 설정 *!*/
            /*box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
        }

        .login-header {
            margin-bottom: 1.5rem;
            text-align: center;
            font-size: 1.5rem;
            color: #333;
        }

        .login-form button {
            width: 100%;
            height: 48px;
            border-radius: 0.25rem;
            background-color: #ffafc5; /* 버튼 색상을 파스텔 핑크로 설정 */
            color: white;
            font-size: 1rem;
            border: none;
            transition: background-color 0.2s;
        }

        .login-form button:hover {
            background-color: #f783ac; /* 버튼 호버 색상을 더 진한 파스텔 핑크로 설정 */
        }

        .login-form input {
            height: 48px;
            margin-bottom: 1rem;
            /*border: 1px solid #ffadc4; !* 입력 필드 테두리를 파스텔 핑크로 설정 *!*/
            border-radius: 0.25rem;
        }

        .login-form-link {
            text-align: center;
            display: block;
            margin-top: 2rem;
            color: black; /* 이전에 파스텔 핑크로 설정했으나 검은색으로 변경됨 */
            transition: color 0.2s;
            text-decoration: none; /* 링크의 밑줄을 제거 */
        }


        .login-form-link:hover {
            color: #f783ac; /* 링크 호버 색상을 더 진한 파스텔 핑크로 설정 */
        }


        .login-logo {
            width: 7%; /* 로그인 폼의 60% 크기 */
            display: block; /* 블록 레벨 요소로 만들어 중앙 정렬 가능하게 함 */
            margin: 0 auto -70px; /* 위, 양옆 자동(중앙 정렬), 아래 마진 20px */
        }

        .centered-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* 뷰포트의 전체 높이를 사용하여 중앙 정렬 */
        }

        .login-form input {
            height: 48px;
            margin-bottom: 1rem;
            border-radius: 0.25rem;
            background-color: #ffffff; /* 입력란 배경색을 흰색으로 설정 */
            border: 1px solid #ced4da; /* 입력란 테두리 색상 다시 추가 */
        }

        .error-message {
            color: red;
            font-size: 0.9rem;
            margin-bottom: 1rem;
        }


    </style>
    <script>
        window.onload = function() {
            console.log(
                '%c\n' +
                '                                                                        ,,\n' +
                '                                                                       /  ,\n' +
                '                                                                      /   /\n' +
                '                                                                     /   /\n' +
                '                                                                    /   /\n' +
                '    ___________________________                                    /   /\n' +
                '    ⎢                         ⎥                                   /   /\n' +
                '    ⎢   신수정 포트폴리오     ⎥                                  /   /\n' +
                '    ⎢____    _________________⎥                                 /   /\n' +
                '          \\/    ,      ,,                                      /   /\n' +
                '               /'+'%c@'+'%c\\____/'+'%c@'+'%c \\                                ____/   /\n' +
                '              /           \\                         _____/        /__\n' +
                '        /" \\ / •    •      \\                     __/             /  '+'%c@@'+'%c"\\\n' +
                '        \\    '+'%c@@'+'%c  ㅅ  '+'%c@@'+'%c     /___             ___/                /    _/\n' +
                '       /" \\   \\                 \\__________/                    |__/ "\\\n' +
                '       \\   \\                                                   /      /\n' +
                '        \\    \\  __                                                  _/\n' +
                '         \\                                                       __/\n' +
                '           \\_                                             ______/\n' +
                '              \\ ___                                    __/\n' +
                '                    \\__                             __/\n' +
                '                        \\_____                _____/\n' +
                '                              \\______________/\n' +
                '\n',

                'font-weight: bold;',
                'font-weight: bold; color: #ff7777',
                'font-weight: bold;',
                'font-weight: bold; color: #ff7777',
                'font-weight: bold;',
                'font-weight: bold; color: #ff7777',
                'font-weight: bold;',
                'font-weight: bold; color: #ff7777',
                'font-weight: bold;',
                'font-weight: bold; color: #ff7777',
                'font-weight: bold;'
            );
            console.log('>> %chttps://github.com/SUUUUUUUJUNG', 'font-family:monospace; font-weight: bold');
        }

    </script>
</head>
<body>
<div class="centered-container">
    <div class="container">
        <a href="/"><img src="/images/logo.png" class="login-logo"/></a>
        <div class="row justify-content-center">
            <div class="login-container">
                <c:if test="${param.error == 'error'}">
                    <p class="error-message">아이디 또는 비밀번호를 다시 확인하세요. 오늘의 쇼핑에 등록되지 않은 아이디거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.</p>
                </c:if>
                <form action="/login" method="post" class="login-form" id="loginForm">
                    <input type="text" class="form-control" id="username" name="username" placeholder="아이디" required>
                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <a href="/member/register" class="login-form-link">회원가입</a>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        // 페이지 로드 시 저장된 아이디 불러오기
        if (localStorage.getItem("savedId")) {
            $("#username").val(localStorage.getItem("savedId"));
            $("#saveId").prop("checked", true);
        }

        // 로그인 폼 제출 시 아이디 저장
        $("#loginForm").on("submit", function() {
            if ($("#saveId").is(":checked")) {
                localStorage.setItem("savedId", $("#username").val());
            } else {
                localStorage.removeItem("savedId");
            }
        });
    });
</script>
</body>
</html>
