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
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
            background-color: #007bff;
            color: white;
            font-size: 1rem;
            border: none;
            transition: background-color 0.2s;
        }
        .login-form button:hover {
            background-color: #0056b3;
        }
        .login-form input {
            height: 48px;
            margin-bottom: 1rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }
        .login-form-link {
            text-align: center;
            display: block;
            margin-top: 2rem;
            color: #007bff;
            transition: color 0.2s;
        }
        .login-form-link:hover {
            color: #0056b3;
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
<div class="container">
    <div class="row justify-content-center">
        <div class="login-container">
            <div class="login-header">로그인</div>
            <form action="/login" method="post" class="login-form" id="loginForm">
                <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" required>
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
                <button type="submit" class="btn btn-primary">로그인</button>
                <a href="/register" class="login-form-link">회원가입</a>
            </form>
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
