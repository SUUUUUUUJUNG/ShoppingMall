package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrdersService;
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

    private final OrdersService ordersService;
    private final MemberLoginService memberLoginService;
    private final PaymentsService paymentsService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody OrdersCreateRequestDTO ordersCreateRequestDTO,
                                     Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        ordersCreateRequestDTO.setMemberId(memberId);
        Long orderId = ordersService.create(ordersCreateRequestDTO);
        paymentsService.create(new PaymentsCreateRequestDTO(ordersCreateRequestDTO, orderId, memberId));
        return ResponseEntity.ok(Map.of("message","상품이 추가되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(ordersService.findAllByMemberId(memberDTO.getMemberId()));
    }
}
