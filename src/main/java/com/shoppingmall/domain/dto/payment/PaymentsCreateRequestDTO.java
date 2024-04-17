package com.shoppingmall.domain.dto.payment;

import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
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

    public PaymentsCreateRequestDTO(OrdersCreateRequestDTO ordersCreateRequestDTO) {
        this.amount = ordersCreateRequestDTO.getAmount();
        this.paymentMethod = ordersCreateRequestDTO.getPaymentMethod();
        this.paymentResult = ordersCreateRequestDTO.getPaymentResult();
    }
}
