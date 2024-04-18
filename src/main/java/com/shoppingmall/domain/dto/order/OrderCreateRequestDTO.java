package com.shoppingmall.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreateRequestDTO {

    private Long orderId;
    private Long memberId;
    private String receiverName;
    private String receiverAddress;
    private String receiverDetailAddress;
    private String receiverZipCode;
    private String receiverContact;
    private String deliveryNote;
    private List<Long> cartIds;

    /* 결제 정보 */
    private Double amount;
    private String paymentResult;
    private String paymentMethod;
}
