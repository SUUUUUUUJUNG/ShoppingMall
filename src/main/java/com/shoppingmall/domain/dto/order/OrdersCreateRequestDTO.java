package com.shoppingmall.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersCreateRequestDTO {

    private Long orderId;
    private Long memberId;
    private String receiverName;
    private String receiverAddress;
    private String receiverDetailAddress;
    private String receiverZipCode;
    private String receiverContact;
    private String deliveryNote;

    /* 결제 정보 */
    private Double amount;
    private String paymentResult;
    private String paymentMethod;
}
