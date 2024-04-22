package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.DeliveryAddressesService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryAddressesAPIController {

    private final MemberLoginService memberLoginService;
    private final DeliveryAddressesService deliveryAddressesService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeliveryAddressesCreateRequestDTO requestDTO, Principal principal) {
        requestDTO.setMemberId(memberLoginService.findByPrinciple(principal).getMemberId());
        deliveryAddressesService.create(requestDTO);
        return ResponseEntity.ok(Map.of("message", "주소가 추가되었습니다."));
    }

    @PostMapping("/setPrimary/{id}")
    public ResponseEntity<?> setPrimary(@PathVariable("id") Long id, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        DeliveryAddressesDTO deliveryAddressesDTO = deliveryAddressesService.findById(id);
        memberService.setPrimaryAddress(deliveryAddressesDTO);
        return ResponseEntity.ok(Map.of("message", "대표배송지로 설정되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberId(Principal principal) {
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(deliveryAddressesService.findAllByMemberId(member.getMemberId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id, Principal principal) {
        validateIsDeliveryAddressesOwner(id, principal);
        return ResponseEntity.ok(deliveryAddressesService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long Id, Principal principal) {
        validateIsDeliveryAddressesOwner(Id, principal);
        deliveryAddressesService.delete(Id);
        return ResponseEntity.ok(Map.of("message", "배송지가 삭제되었습니다."));
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody DeliveryAddressesUpdateRequestDTO requestDTO, Principal principal) {
        validateIsDeliveryAddressesOwner(requestDTO.getId(), principal);
        requestDTO.setMemberId(memberLoginService.findByPrinciple(principal).getMemberId());
        deliveryAddressesService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message", "배송지가 수정되었습니다."));
    }

    private void validateIsDeliveryAddressesOwner(Long Id, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        DeliveryAddressesDTO deliveryAddressesDTO = deliveryAddressesService.findById(Id);

        if (deliveryAddressesDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "배송지를 찾을 수 없습니다.");
        }
        if (!deliveryAddressesDTO.getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }
    }
}
