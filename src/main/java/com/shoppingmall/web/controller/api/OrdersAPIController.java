package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrdersService;
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

    @PostMapping
    public ResponseEntity<?> create (@RequestBody OrdersCreateRequestDTO ordersCreateRequestDTO, Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        ordersCreateRequestDTO.setMemberId(memberId);
        ordersService.create(ordersCreateRequestDTO);
        return ResponseEntity.ok(Map.of("message","상품이 추가되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(ordersService.findAllByMemberId(memberDTO.getMemberId()));
    }
}