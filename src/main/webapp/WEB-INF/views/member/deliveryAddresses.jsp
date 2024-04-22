<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        .btn-custom-primary, .btn-primary {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn-custom-primary:hover, .btn-primary:hover {
            background-color: #218838;
        }

        .btn-secondary {
            background-color: #6c757d;
        }

        .table-custom {
            border-collapse: separate;
            border-spacing: 0 15px;
            width: 100%;
        }

        .table-custom thead th {
            background-color: #e8f5e9;
            border: none;
            color: #2e7d32;
        }

        .table-custom tbody tr {
            background-color: #f0f4f7;
            box-shadow: 0 8px 16px 0 rgba(0, 128, 0, 0.2);
            border-radius: 5px;
        }

        .table-custom td, .table-custom th {
            padding: 15px;
            text-align: left;
            border: none;
        }

        .table-custom td:first-child,
        .table-custom th:first-child {
            border-top-left-radius: 10px;
            border-bottom-left-radius: 10px;
        }

        .table-custom td:last-child,
        .table-custom th:last-child {
            border-top-right-radius: 10px;
            border-bottom-right-radius: 10px;
        }

        .shadow {
            box-shadow: 0 4px 8px rgba(0, 128, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="../common/navbar.jsp" flush="true"/>
    <div style="margin-bottom: 70px;"></div>
    <jsp:include page="../common/sidebar.jsp" flush="true"/>

    <div class="container mt-4">
        <div class="row">
            <!-- 사이드바 자리-->

            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-8 offset-md-3 shadow">
                <h2 class="center-align">배송지 관리</h2>
                <div class="right-align">
                    <button id="create-shipping-address" class="btn btn-primary">새 배송지 추가</button>
                </div>
                <div class="mt-3">
                    <table class="table table-custom">
                        <thead>
                        <tr>
                            <th scope="col">선택</th>
                            <th scope="col">수령인</th>
                            <th scope="col">주소</th>
                            <th scope="col">상세 주소</th>
                            <th scope="col">우편번호</th>
                            <th scope="col">연락처</th>
                            <th scope="col">작업</th>
                        </tr>
                        </thead>
                        <tbody id="addressList">
                        <!-- 배송지 정보가 여기에 동적으로 삽입됩니다. -->
                        </tbody>

                    </table>
                    <div style="text-align: center; margin-top: 20px;">
                        <button id="applyPrimaryAddress" class="btn-custom-primary">대표 배송지 적용</button>
                    </div>
                </div>

            </div>
            <!-- 메인 컨텐츠 끝 -->
        </div>
    </div>

        <div class="modal fade" id="createAddressModal" tabindex="-1" aria-labelledby="createAddressModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="createAddressModalLabel">새 배송지 추가</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="createAddressForm">
                            <div class="form-group">
                                <label for="recipientNameCreate">수령인</label>
                                <input type="text" class="form-control" id="recipientNameCreate" required>
                            </div>
                            <button type="button" class="btn btn-outline-secondary address-btn" id="create-search">주소 검색</button>
                            <div class="form-group">
                                <label for="addressCreate">주소</label>
                                <input type="text" class="form-control" id="addressCreate" required>
                            </div>
                            <div class="form-group">
                                <label for="detailAddressCreate">상세 주소</label>
                                <input type="text" class="form-control" id="detailAddressCreate" required>
                            </div>
                            <div class="form-group">
                                <label for="zipCodeCreate">우편번호</label>
                                <input type="text" class="form-control" id="zipCodeCreate" required>
                            </div>
                            <div class="form-group">
                                <label for="phoneNumberCreate">연락처</label>
                                <input type="text" class="form-control" id="phoneNumberCreate" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="saveNewAddress">저장</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="updateAddressModal" tabindex="-1" aria-labelledby="updateAddressModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateAddressModalLabel">배송지 수정</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="updateAddressForm">
                            <input type="hidden" id="addressIdUpdate">
                            <div class="form-group">
                                <label for="recipientNameUpdate">수령인</label>
                                <input type="text" class="form-control" id="recipientNameUpdate" required>
                            </div>
                            <button type="button" class="btn btn-outline-secondary address-btn" id="update-search">주소 검색</button>
                            <div class="form-group">
                                <label for="addressUpdate">주소</label>
                                <input type="text" class="form-control" id="addressUpdate" required>
                            </div>
                            <div class="form-group">
                                <label for="detailAddressUpdate">상세 주소</label>
                                <input type="text" class="form-control" id="detailAddressUpdate" required>
                            </div>
                            <div class="form-group">
                                <label for="zipCodeUpdate">우편번호</label>
                                <input type="text" class="form-control" id="zipCodeUpdate" required>
                            </div>
                            <div class="form-group">
                                <label for="phoneNumberUpdate">연락처</label>
                                <input type="text" class="form-control" id="phoneNumberUpdate" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="updateAddress">저장</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    </div>
                </div>
            </div>
        </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    $(document).ready(function () {

        $('#create-shipping-address').click(function() {
            $('#createAddressModal').modal('show');
        });

        getAddressList();

        function getAddressList() {
            $.ajax({
                url: '/api/delivery',
                type: 'GET',
                contentType: 'application/json',
                success: function(response) {
                    var tableBody = $('#addressList');
                    tableBody.empty();

                    response.forEach(function(address, index) {
                        var row = $('<tr></tr>');
                        row.append($('<td></td>').html('<input type="radio" name="primaryAddress" value="' + address.id + '">')); // 라디오 버튼으로 수정하여 한 번에 하나만 선택 가능하게 함
                        row.append($('<td></td>').text(address.recipient_name));
                        row.append($('<td></td>').text(address.address));
                        row.append($('<td></td>').text(address.addr_Detail));
                        row.append($('<td></td>').text(address.zip_Code));
                        row.append($('<td></td>').text(address.phoneNumber));
                        row.append($('<td></td>').html('<a href="#" class="btn btn-sm btn-secondary update-btn" data-address-id="'+address.id+'">수정</a> ' +
                            '<a href="#" class="btn btn-sm btn-danger delete-btn" data-address-id="'+address.id+'">삭제</a>'));
                        $('#addressList').append(row);
                    });

                },
                error: function(error) {
                    alert('배송지 정보를 불러오는 데 실패했습니다.');
                }
            });
        }

        $('#saveNewAddress').click(function(e) {
            let recipientName = $('#recipientNameCreate').val().trim();
            let address = $('#addressCreate').val().trim();
            let detailAddress = $('#detailAddressCreate').val().trim();
            let zipCode = $('#zipCodeCreate').val().trim();
            let phoneNumber = $('#phoneNumberCreate').val().trim();

            if (!recipientName || !address || !detailAddress || !zipCode || !phoneNumber) {
                alert('모든 필드를 채워주세요.');
                return;
            }
            const addressData = {
                recipient_name: recipientName,
                address: address,
                addr_Detail: detailAddress,
                zip_Code: zipCode,
                phoneNumber: phoneNumber
            };

            $.ajax({
                url: '/api/delivery',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(addressData),
                success: function(response) {
                    $('#createAddressModal').modal('hide');
                    alert(response.message);
                    location.reload();
                },
                error: function(error) {
                    alert(error.responseJSON.message);
                }
            });
        });

        // 주소 검색 버튼 클릭 이벤트
        $('.address-btn').click(function() {
            var type = $(this).attr('id');
            searchAddress(type);
        });

        function searchAddress(type) {
            console.log(type)
            new daum.Postcode({
                oncomplete: function(data) {
                    if (type === "create-search") {
                        console.log(data.address);
                        $('#addressCreate').val(data.address);
                        $('#zipCodeCreate').val(data.zonecode);
                        $('#detailAddressCreate').focus();
                    } else if (type === "update-search") {
                        $('#addressUpdate').val(data.address);
                        $('#zipCodeUpdate').val(data.zonecode);
                        $('#detailAddressUpdate').focus();
                    }
                }
            }).open();
        }


        // 배송지 삭제 버튼 클릭 이벤트 처리
        $(document).on('click', '.delete-btn', function() {
            var addressId = $(this).data('address-id');

            if(confirm('이 배송지를 삭제하시겠습니까?')) {
                $.ajax({
                    url: '/api/delivery/'+addressId,
                    type: 'DELETE',
                    success: function(response) {
                        alert(response.message);
                        location.reload();
                    },
                    error: function(error) {
                        alert(error.responseJSON.message);
                    }
                });
            }
        });

        $('#applyPrimaryAddress').click(function() {
            var selectedAddressId = $('input[name="primaryAddress"]:checked').val();
            if (!selectedAddressId) {
                alert('대표 배송지로 설정할 주소를 선택해주세요.');
                return;
            }

            $.ajax({
                url: '/api/delivery/setPrimary/' + selectedAddressId,
                type: 'POST',
                success: function(response) {
                    alert(response.message);
                    location.reload();
                },
                error: function(error) {
                    alert(error.responseJSON.message);
                }
            });
        });

        $('#create-shipping-address').click(function() {
            $('#createAddressModal').modal('show');
        });

        // 배송지 수정 버튼 클릭 이벤트 처리
        $(document).on('click', '.update-btn', function() {
            const addressId = $(this).data("address-id");
            $.ajax({
                url: '/api/delivery/'+addressId,
                type: 'get',
                success: function(response) {
                    $('#recipientNameUpdate').val(response.recipient_name);
                    $('#addressUpdate').val(response.address);
                    $('#detailAddressUpdate').val(response.addr_Detail);
                    $('#zipCodeUpdate').val(response.zip_Code);
                    $('#phoneNumberUpdate').val(response.phoneNumber);
                    $('#addressIdUpdate').val(response.id);
                    $('#updateAddressModal').modal('show');
                },
                error: function(error) {
                    alert(error.responseJSON.message);
                }
            });

        });
        // Update address
        $('#updateAddress').click(function() {
            const addressId = $('#addressIdUpdate').val();
            const recipientName = $('#recipientNameUpdate').val().trim();
            const address = $('#addressUpdate').val().trim();
            const detailAddress = $('#detailAddressUpdate').val().trim();
            const zipCode = $('#zipCodeUpdate').val().trim();
            const phoneNumber = $('#phoneNumberUpdate').val().trim();

            if (!recipientName || !address || !detailAddress || !zipCode || !phoneNumber) {
                alert('모든 필드를 채워주세요.');
                return;
            }

            const addressData = {
                id: addressId,
                recipient_name: recipientName,
                address: address,
                addr_Detail: detailAddress,
                zip_Code: zipCode,
                phoneNumber: phoneNumber
            };

            $.ajax({
                url: '/api/delivery',
                type: 'PATCH',
                contentType: 'application/json',
                data: JSON.stringify(addressData),
                success: function(response) {
                    $('#updateAddressModal').modal('hide');
                    alert(response.message);
                    location.reload();
                },
                error: function(error) {
                    alert(error.responseJSON.message);
                }
            });
        });


    });
</script>
</html>