package com.shoppingmall.web.controller;

import com.shoppingmall.domain.dto.InquiryDTO;
import com.shoppingmall.domain.dto.MemberDTO;
import com.shoppingmall.domain.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    public String inquiry() {
        return "inquiry";
    }

    @PostMapping("/submitInquiry")
    @ResponseBody
    public ResponseEntity<?> submitInquiry(@RequestBody InquiryDTO inquiryDTO) {
        System.out.println("inquiryDTO = " + inquiryDTO);
        inquiryService.submitInquiry(inquiryDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "등록이 완료 되었습니다. 문의하신 내역은 고객센터에서 확인하실 수 있습니다."));
    }

    @GetMapping("/inquiriesList")
    public String inquiriesList(HttpSession session, Model model) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();
        List<InquiryDTO> inquiries = inquiryService.inquiriesList(memberId);
        model.addAttribute("inquiries", inquiries);
        return "inquiriesList";
    }

    @GetMapping("/delete")
    public String deleteInquiryByMemberId(@RequestParam("inquiry_Id") Long inquiry_Id){
        inquiryService.deleteInquiryByMemberId(inquiry_Id);
        return "redirect:/inquiry/inquiriesList";
    }

    @GetMapping("/detail/{inquiryId}")
    public String viewInquiryDetails(@PathVariable("inquiryId") Long inquiryId,Model model){
        List<InquiryDTO> inquiries = inquiryService.viewInquiryDetails(inquiryId);
        model.addAttribute("inquiries",inquiries);
        return "inquiryDetail";
    }
}
