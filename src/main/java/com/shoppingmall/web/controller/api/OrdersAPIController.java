package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrderService;
import com.shoppingmall.domain.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersAPIController {

    private final OrderService orderService;
    private final MemberLoginService memberLoginService;
    private final PaymentsService paymentsService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody OrderCreateRequestDTO orderCreateRequestDTO,
                                     Principal principal){
        System.out.println("principal = " + principal);
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        orderCreateRequestDTO.setMemberId(memberId);

        Long orderId = orderService.create(orderCreateRequestDTO);
        PaymentsCreateRequestDTO paymentsCreateRequestDTO = new PaymentsCreateRequestDTO(orderCreateRequestDTO, memberId, orderId);
        paymentsService.create(paymentsCreateRequestDTO);
        return ResponseEntity.ok(Map.of("message","상품이 추가되었습니다.", "orderId", orderId));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(orderService.findAllByMemberId(memberDTO.getMemberId()));
    }
}
