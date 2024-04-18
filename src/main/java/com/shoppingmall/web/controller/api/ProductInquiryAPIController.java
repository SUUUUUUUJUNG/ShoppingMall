package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.goods.ProductInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productInquiry")
public class ProductInquiryAPIController {

    private final MemberLoginService memberLoginService;
    private final ProductInquiryService productInquiryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductInquiryCreateRequestDTO requestDTO, Principal principal){
        MemberDTO login = memberLoginService.findByPrinciple(
                principal);
        productInquiryService.create(new ProductInquiryDTO(requestDTO, login.getMemberId()));
        return ResponseEntity.ok(Map.of("message","문의가 등록되었습니다."));
    }

    @GetMapping("/member")
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO login = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(productInquiryService.findAllByMemberId(login.getMemberId()));
    }

    @GetMapping
    public ResponseEntity<?> findAllByGCode(@RequestParam("gCode") String gCode) {
        return ResponseEntity.ok(productInquiryService.findAllByGCode(gCode));
    }

    @GetMapping("/one")
    public ResponseEntity<?> findById(@RequestParam("inquiryId") Long inquiryId) {
        return ResponseEntity.ok(productInquiryService.findById(inquiryId));
    }

    @PatchMapping
    public ResponseEntity<?> updateProductInquiry(@RequestBody ProductInquiryUpdateRequestDTO requestDTO, Principal principal){
        validateIsInquiryOwner(requestDTO.getInquiry_Id(), principal);
        productInquiryService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message", "문의가 수정되었습니다.", "inquiryId", requestDTO.getInquiry_Id()));
    }



    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteProductInquiry(@PathVariable("inquiryId") Long inquiryId, Principal principal) {
        validateIsInquiryOwner(inquiryId, principal);
        productInquiryService.delete(inquiryId);
        return ResponseEntity.ok(Map.of("message", "문의가 삭제되었습니다."));
    }

    private void validateIsInquiryOwner(Long inquiryId, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        ProductInquiryDTO inquiryDTO = productInquiryService.findById(inquiryId);

        if (inquiryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "문의를 찾을 수 없습니다.");
        }
        if (!inquiryDTO.getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }
    }
}
