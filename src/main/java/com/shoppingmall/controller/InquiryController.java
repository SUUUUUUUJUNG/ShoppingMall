package com.shoppingmall.controller;

import com.shoppingmall.dto.InquiryDTO;
import com.shoppingmall.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    public String inquiry(){
        return "inquiry";
    }

    @PostMapping("/submitInquiry")
    @ResponseBody
    public ResponseEntity<?> submitInquiry(@RequestBody InquiryDTO inquiryDTO){
        inquiryService.submitInquiry(inquiryDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","등록이 완료 되었습니다. 문의하신 내역은 고객센터에서 확인하실 수 있습니다."));
    }
}
