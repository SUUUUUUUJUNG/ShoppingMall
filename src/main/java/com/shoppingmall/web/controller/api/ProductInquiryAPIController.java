package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ProductInquiryService;
import com.shoppingmall.web.service.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productInquiry")
public class ProductInquiryAPIController {

    private final MemberLoginService memberLoginService;
    private final ProductInquiryService productInquiryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductInquiryCreateRequestDTO requestDTO, HttpSession session){
        MemberDTO login = memberLoginService.getLogin(session);
        productInquiryService.create(new ProductInquiryDTO(requestDTO, login.getMemberId()));
        return ResponseEntity.ok(Map.of("message","문의가 등록되었습니다."));
    }

    @GetMapping("/member")
    public ResponseEntity<?> findAllByMemberId(HttpSession session){
        MemberDTO login = memberLoginService.getLogin(session);
        List<ProductInquiryDTO> inquiries = productInquiryService.findAllByMemberId(login.getMemberId());
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping
    public ResponseEntity<?> findAllByGCode(@RequestParam("gCode") String gCode) {
        List<ProductInquiryDTO> inquiries = productInquiryService.findAllByGCode(gCode);
        return ResponseEntity.ok(inquiries);
    }

    @PatchMapping
    public ResponseEntity<?> updateProductInquiry(@RequestBody ProductInquiryUpdateRequestDTO requestDTO, HttpSession session){
        validateIsInquiryOwner(requestDTO.getInquiry_Id(), session);
        productInquiryService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message", "문의가 수정되었습니다.", "inquiryId", requestDTO.getInquiry_Id()));
    }



    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteProductInquiry(@PathVariable("inquiryId") Long inquiryId, HttpSession session) {
        validateIsInquiryOwner(inquiryId, session);

        boolean isDeleted = productInquiryService.deleteProductInquiry(inquiryId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "문의를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(Map.of("message", "문의가 삭제되었습니다."));
    }

    private void validateIsInquiryOwner(Long inquiryId, HttpSession session) {
        Long memberId = memberLoginService.getLogin(session).getMemberId();
        ProductInquiryDTO inquiryDTO = productInquiryService.findById(inquiryId);
        if (!inquiryDTO.getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "mesg");
        }
    }
}
