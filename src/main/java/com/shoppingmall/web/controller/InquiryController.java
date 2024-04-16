package com.shoppingmall.web.controller;

import com.shoppingmall.domain.dto.InquiryDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.InquiryService;
import com.shoppingmall.domain.service.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final MemberLoginService memberLoginService;

    @GetMapping
    public String inquiry() {
        return "inquiry";
    }

    @PostMapping("/submitInquiry")
    @ResponseBody
    public ResponseEntity<?> submitInquiry(@RequestBody InquiryDTO inquiryDTO,Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        inquiryDTO.setMemberId(memberId);
        inquiryService.create(inquiryDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "등록이 완료 되었습니다. 문의하신 내역은 고객센터에서 확인하실 수 있습니다."));
    }

    @GetMapping("/inquiriesList")
    public String inquiriesList(Principal principal, Model model) {
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        Long memberId = member.getMemberId();
        List<InquiryDTO> inquiries = inquiryService.findByMemberId(memberId);
        model.addAttribute("inquiries", inquiries);
        return "inquiriesList";
    }

    @GetMapping("/delete")
    public String deleteInquiryByMemberId(@RequestParam("inquiry_Id") Long inquiry_Id){
        inquiryService.delete(inquiry_Id);
        return "redirect:/inquiry/inquiriesList";
    }

    @GetMapping("/detail/{inquiryId}")
    public String viewInquiryDetails(@PathVariable("inquiryId") Long inquiryId,Model model){
        List<InquiryDTO> inquiries = inquiryService.findByInquiryId(inquiryId);
        model.addAttribute("inquiries",inquiries);
        return "inquiryDetail";
    }
}
