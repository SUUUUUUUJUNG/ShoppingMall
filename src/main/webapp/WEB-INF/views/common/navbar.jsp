<%@ page import="com.shoppingmall.dto.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<style>
  .nav-link.dropdown-toggle::after {
    display: none;
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

  /* 기본 리스트 스타일 제거 */
  .nav-item {
    list-style: none;
  }

</style>

<!-- 네비게이션 바 시작 -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <ul>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <div class="dropbtn"> <!-- 드롭다운 버튼으로 사용 -->
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
          </div>
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="/?gCategory=top">TOP</a></li>
          <li><a class="dropdown-item" href="/?gCategory=dress">DRESS</a></li>
          <li><a class="dropdown-item" href="/?gCategory=outer">OUTER</a></li>
          <li><a class="dropdown-item" href="/?gCategory=bottom">BOTTOM</a></li>
        </ul>
      </li>
    </ul>
    <a class="navbar-brand" href="/">오늘의 쇼핑</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <%
          MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
          if(memberDTO==null) {
        %>
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/login">로그인</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/register">회원가입</a>
        </li>
        <% } else { %>
        <li>
          <a class="nav-link active"><%=memberDTO.getUsername()%> 님</a>
        </li>
        <li>
          <a class="nav-link active btn-outline-primary" aria-current="page" href="/logout">로그아웃</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/myPage">마이페이지</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cart/list">장바구니</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/myShopping">찜목록</a>
        </li>
        <% } %>
        <li class="nav-item">
          <a class="nav-link" href="/inquiry">고객센터</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!-- 네비게이션 바 끝 -->



