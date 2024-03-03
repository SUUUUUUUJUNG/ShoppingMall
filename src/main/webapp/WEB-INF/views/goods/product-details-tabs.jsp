
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> <!-- Bootstrap JS 추가 -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap CSS 추가 -->

<div class="container">


	<!-- 탭 버튼들 -->
	<ul class="nav nav-pills nav-fill">
		<li class="nav-item">
			<a class="nav-link active" href="#tab1" data-toggle="tab">상품정보</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#tab2" data-toggle="tab">디테일</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#tab3" data-toggle="tab">구매후기</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#tab3" data-toggle="tab">상품문의</a>
		</li>
	</ul>
	<!-- 탭 내용 -->
	<div class="tab-content">
		<div class="tab-pane active" id="tab1">
			<!-- 탭 #1 내용 -->
			<p>상품정보의 내용이 여기에 표시됩니다.</p>
		</div>
		<div class="tab-pane" id="tab2">
			<!-- 탭 #2 내용 -->
			<p>디테일의 내용이 여기에 표시됩니다.</p>
		</div>
		<div class="tab-pane" id="tab3">
			<!-- 탭 #3 내용 -->
			<p>구매후기의 내용이 여기에 표시됩니다.</p>
		</div>
		<div class="tab-pane" id="tab3">
			<!-- 탭 #3 내용 -->
			<p>상품문의의 내용이 여기에 표시됩니다.</p>
		</div>
	</div>
</div>
