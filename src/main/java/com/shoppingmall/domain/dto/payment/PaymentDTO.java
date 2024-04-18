package com.shoppingmall.domain.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    private Long paymentId;
    private Long memberId;
    private Long orderId;
    private Double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String paymentStatus;
}

