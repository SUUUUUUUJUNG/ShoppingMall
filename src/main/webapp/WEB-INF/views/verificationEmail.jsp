<%--
  Created by IntelliJ IDEA.
  User: SHIN SUJUNG
  Date: 2024-04-29
  Time: 오후 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>

        h2 {
            color: #006400; /* 짙은 초록색 */
        }
        .btn, .submit-btn {
            display: inline-block;
            background-color: #4CAF50; /* 진한 민트색 */
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
            margin: 20px 20px;
            cursor: pointer;
            border: none; /* 테두리 제거 */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 버튼 그림자 추가 */
        }
        .email-form {
            margin-top: 20px;
        }
        .email-input {
            padding: 10px;
            margin: 10px 0;
            width: calc(100% - 22px);
            box-sizing: border-box;
            border-radius: 5px; /* 입력 필드 둥근 모서리 추가 */
            border: 1px solid #ced4da; /* 입력 필드 테두리 색상 조정 */
        }

        .center {
            text-align: center;
            padding-top: 20px;
        }

        .email-input {
            width: 400px;
        }

        .box {
            padding: 40px;
            margin-top: 50px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
            max-width: 600px; /* 너비 조정 */
        }

        .email-form p {
            color: #495057; /* 설명 텍스트 색상 조정 */
        }
    </style>
</head>
<body>
<h1>이메일 인증</h1>
<div class="container shadow center box">
    <h2>이메일 인증을 위한 메일입니다.</h2>
    <p>안녕하세요, <span>${user.username}사용자</span>님!</p>
    <p>회원가입을 완료하려면 아래의 링크를 클릭해 주세요.</p>
    <form action="/verify/send" method="post">
        <input type="hidden" name="email" value="${user.email}">
        <input type="hidden" name="username" value="${user.username}">
        <button class="submit-btn">이메일 인증하기</button>
    </form>
    <p>이 링크는 24시간 동안 유효합니다.</p>
    <hr>
</div>
</body>
</html>
