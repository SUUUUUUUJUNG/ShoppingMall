package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ProductInquiryService;
import com.shoppingmall.web.service.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update/{inquiry_Id}")
    public ResponseEntity<?> updateProductInquiry(@PathVariable("inquiry_Id")Long inquiry_Id, @RequestBody ProductInquiryCreateRequestDTO requestDTO, HttpSession session){
        MemberDTO login = memberLoginService.getLogin(session);
        List<ProductInquiryDTO> inquiry = productInquiryService.updateProductInquiry(inquiry_Id);
        return ResponseEntity.ok(inquiry);
    }
}
