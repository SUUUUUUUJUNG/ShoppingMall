<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        /* 드롭다운 컨테이너 스타일 */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        /* 햄버거 메뉴 스타일 */
        .dropbtn {
            background-color: #ffffff;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        /* 햄버거 메뉴 바 스타일 */
        .dropbtn .bar {
            display: block;
            width: 25px;
            height: 3px;
            margin: 5px auto;
            transition: 0.4s;
            background-color: #000000;
        }

        /* 드롭다운 내용 스타일 */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        /* 드롭다운 항목 스타일 */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* 드롭다운 항목에 마우스를 올릴 때 색 변경 */
        .dropdown-content a:hover {background-color: #f1f1f1}

        /* 드롭다운 버튼에 마우스를 올릴 때 드롭다운 내용 표시 */
        .dropdown:hover .dropdown-content {
            display: block;
        }

    </style>
</head>
<body>

<div class="dropdown">
    <button class="dropbtn">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </button>
    <div class="dropdown-content">
        <a href="/?gCategory=top">TOP</a>
        <a href="/?gCategory=dress">DRESS</a>
        <a href="/?gCategory=outer">OUTER</a>
        <a href="/?gCategory=bottom">BOTTOM</a>
    </div>
</div>

</body>
</html>




