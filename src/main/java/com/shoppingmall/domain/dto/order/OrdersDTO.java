package com.shoppingmall.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersDTO {

    private Long orderId;
    private Long memberId;
    private String receiverName;
    private String receiverAddress;
    private String receiverContact;
    private String deliveryNote;
    private Long paymentId;
}

