<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${! empty mesg}">
	<script>
		alert('${mesg}');
	</script>
</c:if>
<% if(session.getAttribute("mesg")!=null){
	session.removeAttribute("mesg");
} %>
<script>
	window.onload = function() {
		// 페이지 로드 시 저장된 아이디와 비밀번호를 입력 필드에 설정
		if(localStorage.getItem("userId") && localStorage.getItem("password")) {
			document.getElementById("userId").value = localStorage.getItem("userId");
			document.getElementById("password").value = localStorage.getItem("password");
		}
	}

	function saveCredentials() {
		var userId = document.getElementById("userId").value;
		var password = document.getElementById("password").value;
		localStorage.setItem("userId", userId);
		localStorage.setItem("password", password);
	}
</script>
<form action="login" method="post" onsubmit="saveCredentials()">
	아이디:<input type="text" id="userId" name="userId"><br>
	비밀번호:<input type="password" id="password" name="password"><br>
	<input type="submit" value="로그인"> <input type="reset" value="취소">
</form>
