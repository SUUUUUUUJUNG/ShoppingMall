<%@page import="com.shoppingmall.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- top부분 -->
<!-- EL&JSTL태그 -->
<c:if test = "${empty login}">			<!-- 로그인 안 된 경우 -->
	<a href="${pageContext.request.contextPath}/login">로그인</a>
	<a href="${pageContext.request.contextPath}/memberForm">회원가입</a>
</c:if>
<c:if test = "${!empty login}">			<!-- 로그인 된 경우 -->
	${login.getUsername()}님 환영합니다.
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/cart/list">장바구니</a>
	<a href="${pageContext.request.contextPath}/myPage">myPage</a>
</c:if>

