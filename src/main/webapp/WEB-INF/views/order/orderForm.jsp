<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 폼</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://www.paypal.com/sdk/js?client-id=AWBPCVEiX4w3yudS0xAVkag-1LRWSxlVX1lWiWtsPdt3lHsWu5DI7VCcB07d9OguyDPmL7--_R1m3zok"></script>
    <script>
        $(document).ready(function() {
            $('#sameAsOrderer').change(function() {
                if (this.checked) {
                    $('#receiverName').val($('#ordererName').val());
                    $('#receiverAddress').val($('#ordererAddress').val());
                    $('#receiverDetailAddress').val($('#ordererDetailAddress').val());
                    $('#receiverZipCode').val($('#ordererZipCode').val());
                    $('#receiverContact').val($('#ordererContact').val());
                } else {
                    $('#receiverName').val('');
                    $('#receiverAddress').val('');
                    $('#receiverDetailAddress').val('');
                    $('#receiverZipCode').val('');
                    $('#receiverContact').val('');
                }
            });
            paypal.Buttons({
                style: {
                    layout: 'vertical', // or 'horizontal'
                    color: 'gold', // or 'gold', 'blue', 'silver', 'white', 'black'
                    shape: 'rect', // or 'pill'
                    label: 'paypal', // or 'checkout', 'buynow', 'pay'
                    size: 'responsive', // or 'small', 'medium', 'large', 'responsive' for automatic resizing
                },
                createOrder: function (data, actions) {
                    // 결제 생성
                    return actions.order.create({
                        purchase_units: [{
                            amount: {
                                value: '10.00' // 테스트 결제 금액
                            }
                        }]
                    });
                },
                onApprove: function (data, actions) {
                    // 결제 승인
                    return actions.order.capture().then(function (details) {
                        alert('결제 완료: ' + details.payer.name.given_name);
                        $("#paymentResult").val("success");
                        orderProcessing();
                    });
                }
            }).render('#paypal-button-container');

        });

        function orderProcessing() {
            let receiverName = $('#receiverName').val();
            let receiverAddress = $('#receiverAddress').val();
            let receiverDetailAddress = $('#receiverDetailAddress').val();
            let receiverZipCode = $('#receiverZipCode').val();
            let receiverContact = $('#receiverContact').val();
            let deliveryNote = $('#deliveryNote').val();
            let paymentResult = $('#paymentResult').val();
            let paymentMethod = $('#paymentMethod').val();
            let totalPrice = $('#totalPrice').val();

            let data = {
                receiverName: receiverName,
                receiverAddress: receiverAddress,
                receiverDetailAddress:receiverDetailAddress,
                receiverZipCode:receiverZipCode,
                receiverContact:receiverContact,
                deliveryNote:deliveryNote,
                paymentResult:paymentResult,
                paymentMethod:paymentMethod,
                amount:totalPrice,
            };

            $.ajax({
                url:"/api/orders",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(data),
                success:function (data,status,xhr){
                    alert(data.message);
                    location.href = "/order/" + response.orderId;
                },
                error: function (xhr, status, error) {
                    console.log(error)
                },
            })
        }

    </script>
</head>
<body>
<jsp:include page="../common/navbar.jsp" flush="true"/>
<div class="container mt-4">
    <h2>주문 상세 내역</h2>
    <form>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>제품 이미지</th>
                    <th>제품 이름</th>
                    <th>가격</th>
                    <th>색상</th>
                    <th>사이즈</th>
                    <th>수량</th>
                    <th>총 가격</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartList}">
                    <tr>
                        <td><img src="/images/items/${item.GImage}.gif" alt="제품 이미지" style="width: 100px; height: auto;"></td>
                        <td>${item.GName}</td>
                        <td>${item.GPrice}원</td>
                        <td>${item.GColor}</td>
                        <td>${item.GSize}</td>
                        <td><input type="number" class="form-control" value="${item.GAmount}"></td>
                        <td>${item.GPrice * item.GAmount}원</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- 주문자 정보 -->
        <fieldset class="border p-4 mt-4">
            <legend>주문자 정보</legend>
            <div class="mb-3">
                <label for="ordererName" class="form-label">주문자 이름:</label>
                <input type="text" class="form-control" id="ordererName" name="ordererName" value="${member.realName}" required>
            </div>
            <div class="mb-3">
                <label for="ordererAddress" class="form-label">주문자 주소:</label>
                <input type="text" class="form-control" id="ordererAddress" name="ordererAddress" value="${member.address}" required>
                <label for="ordererDetailAddress" class="form-label">상세 주소:</label>
                <input type="text" class="form-control" id="ordererDetailAddress" name="ordererDetailAddress" value="${member.addr_Detail}" required>
                <label for="ordererZipCode" class="form-label">우편번호:</label>
                <input type="text" class="form-control" id="ordererZipCode" name="ordererZipCode" value="${member.zip_Code}" required>
            </div>
            <div class="mb-3">
                <label for="ordererContact" class="form-label">주문자 연락처:</label>
                <input type of="text" class="form-control" id="ordererContact" name="ordererContact" value="${member.phoneNumber}" required>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" class="form-check-input" id="sameAsOrderer">
                <label class="form-check-label" for="sameAsOrderer">주문자와 수령인 정보 동일</label>
            </div>
        </fieldset>

        <!-- 배송 정보 -->
        <fieldset class="border p-4 mt-4">
            <legend>배송 정보</legend>
            <div class="mb-3">
                <label for="receiverName" class="form-label">수령인 이름:</label>
                <input type="text" class="form-control" id="receiverName" name="receiverName" required>
            </div>
            <div class="mb-3">
                <label for="receiverAddress" class="form-label">수령인 주소:</label>
                <input type="text" class="form-control" id="receiverAddress" name="receiverAddress" required>
                <label for="receiverDetailAddress" class="form-label">상세 주소:</label>
                <input type="text" class="form-control" id="receiverDetailAddress" name="receiverDetailAddress" required>
                <label for="receiverZipCode" class="form-label">우편번호:</label>
                <input type="text" class="form-control" id="receiverZipCode" name="receiverZipCode" required>
            </div>
            <div class="mb-3">
                <label for="receiverContact" class="form-label">수령인 연락처:</label>
                <input type="text" class="form-control" id="receiverContact" name="receiverContact" required>
            </div>
            <div class="mb-3">
                <label for="deliveryNote" class="form-label">배송 메모:</label>
                <textarea class="form-control" id="deliveryNote" name="deliveryNote"></textarea>
            </div>
        </fieldset>
        <!-- 결제 방식 선택 -->
        <input type="number" id="totalPrice" name="totalPrice" value="10000">
        <div class="mb-3">
            <label for="paymentMethod" class="form-label">결제 방식</label>
            <select class="form-select" id="paymentMethod" name="paymentMethod">
                <option value="Credit Card">신용카드</option>
                <option value="Kakao Pay">카카오페이</option>
                <option value="Bank Transfer">무통장입금</option>
            </select>
            <input type="hidden" name="paymentResult">
        </div>
        <div class="payment-section">
            <div id="paypal-button-container"></div>
            <input type="hidden" name="paymentResult" id="paymentResult" value="">
        </div>
        <button type="submit" class="btn btn-primary" onclick="orderProcessing()">주문 확인</button>
    </form>
</div>
</body
<script>

</script>
</html>
