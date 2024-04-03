
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	.nav-pills.nav-fill .nav-item {
		display: inline-block; /* 인라인 블록 요소로 변경 */
		float: none; /* 기본 플로팅 해제 */
	}

	.nav-pills.nav-fill {
		text-align: center; /* 텍스트 정렬을 이용해 내부 요소 중앙 정렬 */
	}
</style>


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
			<a class="nav-link" href="#tab4" data-toggle="tab">상품문의</a>
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
		<div class="tab-pane" id="tab4">
			<!-- 탭 #3 내용 -->
			<p id="product-inquiries">상품문의의 내용이 여기에 표시됩니다.</p>

			<!-- 문의하기 버튼 -->
			<button class="btn btn-outline-primary" data-toggle="modal" data-target="#inquiryModal">문의하기</button>

			<!-- 모달 -->
			<div class="modal fade" id="inquiryModal" tabindex="-1" role="dialog" aria-labelledby="inquiryModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="inquiryModalLabel">상품 문의</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<!-- 여기에 폼 내용을 추가 -->
							<form id="inquiryForm">
								<!-- 폼 필드 예시 -->
								<div class="form-group">
									<label for="inquiry_Content">문의내용</label>
									<textarea class="form-control" id="inquiry_Content" placeholder="문의 내용을 입력하세요"></textarea>
								</div>
								<input type="hidden" id="gCode" value="${param.gCode}">
								<!-- 추가 필드 추가 가능 -->
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="submit">확인</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 모달 -->
			<div class="modal fade" id="inquiryUpdateModal" tabindex="-1" role="dialog" aria-labelledby="inquiryModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="inquiryUpdateModalLabel">상품 문의</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<!-- 여기에 폼 내용을 추가 -->
							<form id="inquiryUpdateForm">
								<!-- 폼 필드 예시 -->
								<div class="form-group">
									<label for="inquiry_Content">문의내용</label>
									<textarea class="form-control" id="inquiry_Update_Content" placeholder="문의 내용을 입력하세요"></textarea>
								</div>
								<input type="hidden" id="inquiry_id">
								<!-- 추가 필드 추가 가능 -->
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="update-modal-btn">확인</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<script>
	$(document).ready(function () {
		var gCode = $("#gCode").val();
		inquiryLoad();

		$("#submit").on("click", function () {
			var data = {
				"code": gCode,
				"inquiry_Content": $("#inquiry_Content").val()
			};

			$.ajax({
				url: "/api/productInquiry",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				success: function (data, status, xhr) {
					alert(data.message);
					window.location.reload();
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});
		});

		function inquiryLoad() {
			$.ajax({
				url: "/api/productInquiry?gCode=" + gCode,
				type: "get",
				success: function (data) {
					displayInquiries(data);
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});
		}

		$(document).on('click', ".update-btn", function () {
			let id = $(this).data("id");
			$("#inquiry_id").val(id);

			$.ajax({
				url: "/api/productInquiry/one?inquiryId=" + id,
				type: "get",
				success: function (data) {
					$("#inquiry_Update_Content").val(data.inquiry_Content);
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});
		});

		//update-modal-btn
		$(document).on('click', "#update-modal-btn", function () {
			let id = $("#inquiry_id").val();
			let content = $("#inquiry_Update_Content").val();
			let data={
				"inquiry_Content":content,
				"inquiry_Id": id,
			}

			$.ajax({
				url: "/api/productInquiry",
				type: "patch",
				contentType: "application/json",
				data: JSON.stringify(data),
				success: function (data, status, xhr) {
					alert(data.message);
					window.location.reload();
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});
		});


		$(document).on('click', ".delete-btn", function () {
			let id = $(this).data("id");

			$.ajax({
				url: "/api/productInquiry/" + id,
				type: "delete",
				success: function (data) {
					alert(data.message);
					window.location.reload();
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});

		});

			function displayInquiries(data) {
			$('#product-inquiries').empty();
			if (data.length === 0) {
				$('#product-inquiries').append('<p>문의가 없습니다.</p>');
				return;
			}
			data.forEach(function(inquiry) {
				var inquiryHtml = '<div class="card mb-3">' +
						'<div class="card-header">' +
						'문의 날짜: ' + inquiry.inquiry_Date +
						'</div>' +
						'<div class="card-body">' +
						'<h5 class="card-title">문의 내용</h5>' +
						'<p class="card-text">' + inquiry.inquiry_Content + '</p>' +
						'<div><button class="delete-btn" data-id="' + inquiry.inquiry_Id + '">삭제</button>' +
						'<button data-toggle="modal" data-target="#inquiryUpdateModal" class="update-btn" data-id="' + inquiry.inquiry_Id + '">수정</buttonid></div>' +
						'</div>' +
						'</div>';
				$('#product-inquiries').append(inquiryHtml);
			});
		}
	});
</script>
