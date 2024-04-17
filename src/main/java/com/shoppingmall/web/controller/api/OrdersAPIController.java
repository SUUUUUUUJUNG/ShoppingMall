package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersAPIController {

    private final OrdersService ordersService;
    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody OrdersCreateRequestDTO ordersCreateRequestDTO, Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        ordersCreateRequestDTO.setMemberId(memberId);
        ordersService.create(ordersCreateRequestDTO);
        return ResponseEntity.ok(Map.of("message","상품이 추가되었습니다."));
    }
}
