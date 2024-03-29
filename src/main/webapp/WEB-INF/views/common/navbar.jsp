<%@ page import="com.shoppingmall.domain.dto.member.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<style>
  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
  }

  .nav-links, .user-actions {
    display: flex;
    align-items: center;
  }

  .nav-item, .nav-link {
    margin-right: 15px;
    list-style: none;
    text-decoration: none;
    color: black;
    padding: 8px;
  }

  .login-logo {
    max-width: 70px;
    max-height: 70px;
  }

  /* 추가된 스타일 */
  .user-actions {
    margin-left: auto; /* 사용자 관련 액션을 오른쪽으로 밀어냄 */
  }
</style>

<div class="navbar">
  <a href="/"><img src="/images/logo.png" class="login-logo"/></a>

  <!-- 상품 카테고리 링크 -->
  <div class="nav-links">
    <a class="nav-item nav-link" href="/?gCategory=top">TOP</a>
    <a class="nav-item nav-link" href="/?gCategory=dress">DRESS</a>
    <a class="nav-item nav-link" href="/?gCategory=outer">OUTER</a>
    <a class="nav-item nav-link" href="/?gCategory=bottom">BOTTOM</a>
  </div>

  <!-- 사용자 관련 액션 -->
  <div class="user-actions">
    <%
      MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
      if(memberDTO == null) {
    %>
    <a class="nav-item nav-link" href="/login">로그인</a>
    <a class="nav-item nav-link" href="/member/register">회원가입</a>
    <% } else { %>
    <span class="nav-item"><%=memberDTO.getUsername()%> 님</span>
    <a class="nav-item nav-link" href="/logout">로그아웃</a>
    <a class="nav-item nav-link" href="/myPage">마이페이지</a>
    <a class="nav-item nav-link" href="/cart/list">장바구니</a>
    <a class="nav-item nav-link" href="/myShopping">찜목록</a>
    <% } %>
    <a class="nav-item nav-link" href="/inquiry">고객센터</a>
  </div>
</div>
