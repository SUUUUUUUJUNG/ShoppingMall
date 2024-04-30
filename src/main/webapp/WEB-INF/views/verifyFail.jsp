<%--
  Created by IntelliJ IDEA.
  User: SHIN SUJUNG
  Date: 2024-04-30
  Time: 오후 7:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>이메일 인증 실패</title>
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
        h1 {
            color: #f44336; /* Red color to indicate failure */
        }
        p {
            margin: 20px auto;
            color: #333;
        }
        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #2196F3; /* Blue color for neutral action */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #1e88e5;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>이메일 인증 실패</h1>
    <p>유효하지 않거나 만료된 인증 링크입니다.</p>
    <div>
        <a href="/" >홈페이지로 돌아가기</a>
    </div>
</div>
</body>
</html>

