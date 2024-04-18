package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import com.shoppingmall.domain.service.CartService;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrderService;
import com.shoppingmall.domain.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderAPIController {

    private final OrderService orderService;
    private final MemberLoginService memberLoginService;
    private final PaymentsService paymentsService;
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> processOrderAndClearCart (@RequestBody OrderCreateRequestDTO requestDTO,
                                     Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        requestDTO.setMemberId(memberId);

        Long orderId = orderService.create(requestDTO);
        PaymentsCreateRequestDTO paymentsCreateRequestDTO = new PaymentsCreateRequestDTO(requestDTO, memberId, orderId);
        paymentsService.create(paymentsCreateRequestDTO);

        List<Integer> list = requestDTO.getCartIds().stream().map(cartService::delete).toList();

        return ResponseEntity.ok(Map.of("message","상품이 추가되었습니다.", "orderId", orderId));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(orderService.findAllByMemberId(memberDTO.getMemberId()));
    }
}
