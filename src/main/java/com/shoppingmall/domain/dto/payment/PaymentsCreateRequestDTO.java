package com.shoppingmall.domain.dto.payment;

import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PaymentsCreateRequestDTO {

    private Long memberId;
    private Long orderId;

    /* 결제 정보 */
    private Double amount;
    private String paymentResult;
    private LocalDateTime PaymentDate = LocalDateTime.now();
    private String paymentMethod;

    public PaymentsCreateRequestDTO(OrderCreateRequestDTO orderCreateRequestDTO, Long memberId, Long orderId) {
        this.memberId = memberId;
        this.orderId = orderId;
        this.amount = orderCreateRequestDTO.getAmount();
        this.paymentMethod = orderCreateRequestDTO.getPaymentMethod();
        this.paymentResult = orderCreateRequestDTO.getPaymentResult();
    }
}
