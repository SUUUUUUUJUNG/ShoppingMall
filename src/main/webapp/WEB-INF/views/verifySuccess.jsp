<%--
  Created by IntelliJ IDEA.
  User: SHIN SUJUNG
  Date: 2024-04-30
  Time: 오후 7:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>이메일 인증 성공</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 30px;
            color: #666666;
        }
        a {
            display: inline-block;
            background-color: #4CAF50;
            color: #ffffff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>이메일 인증 성공!</h1>
    <p>축하합니다! 이메일 인증이 성공적으로 완료되었습니다.</p>
    <div>
        <a href="/login">로그인 페이지로 이동</a>
    </div>
</div>
</body>
</html>