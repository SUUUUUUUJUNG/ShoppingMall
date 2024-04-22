package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.DeliveryAddressesService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryAddressesAPIController {

    private final MemberLoginService memberLoginService;
    private final DeliveryAddressesService deliveryAddressesService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeliveryAddressesCreateRequestDTO requestDTO, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        deliveryAddressesService.create(requestDTO);
        return ResponseEntity.ok(Map.of("message","주소가 추가되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal) {
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(deliveryAddressesService.findAllByMemberId(member.getMemberId()));
    }
}
