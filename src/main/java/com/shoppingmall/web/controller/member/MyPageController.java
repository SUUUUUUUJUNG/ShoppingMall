package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberLoginService memberLoginService;

    @GetMapping
    public String myPage(Principal principal, Model model) {
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        model.addAttribute("member", memberDTO);
        return "member/myPage";
    }

    @GetMapping("/deliveryAddresses")
    public String deliveryAddresses() {
        return "member/deliveryAddresses";
    }
}
