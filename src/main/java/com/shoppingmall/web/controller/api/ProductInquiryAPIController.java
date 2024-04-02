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
        Long memberId = memberLoginService.getLogin(session).getMemberId();
        // id를 find  dto user
        ProductInquiryDTO inquiryDTO = productInquiryService.findById(requestDTO.getInquiry_Id());
        if (!inquiryDTO.getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "mesg");
        }
        productInquiryService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message", "문의가 수정되었습니다", "inquiryId", inquiryDTO.getInquiry_Id()));
    }

    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteProductInquiry(@PathVariable("inquiryId") Long inquiryId, HttpSession session) {
        // MemberLoginService에서 로그인 여부를 체크하므로, 여기서 별도의 로그인 체크는 필요 없음
        Long memberId = memberLoginService.getLogin(session).getMemberId(); // 로그인되지 않았다면, 여기서 ResponseStatusException이 발생


        boolean isDeleted = productInquiryService.deleteProductInquiry(inquiryId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "문의를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(Map.of("message", "문의가 삭제되었습니다."));
    }
}
