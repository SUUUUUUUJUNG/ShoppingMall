package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import com.shoppingmall.domain.service.member.DeliveryAddressesService;
import com.shoppingmall.domain.service.member.MemberLoginService;
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

   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") Long Id, Principal principal) {
        validateIsDeliveryAddressesOwner(Id, principal);
        deliveryAddressesService.delete(Id);
        return ResponseEntity.ok(Map.of("message","배송지가 삭제되었습니다."));
   }

    private void validateIsDeliveryAddressesOwner(Long Id, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        DeliveryAddressesDTO deliveryAddressesDTO = deliveryAddressesService.findById(Id);

        if(deliveryAddressesDTO==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"배송지를 찾을 수 없습니다.");
        }
        if(!deliveryAddressesDTO.getMemberId().equals(memberId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
    }
}
