package com.shoppingmall.controller.member;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRegisterController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String memberForm(Model model, MemberDTO memberDTO) {
        model.addAttribute("memberDTO", memberDTO);
        return "memberForm";
    }

    @PostMapping("/register")
    public String memberAdd(@Validated @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult, Model model) { // validated 이용
        if (bindingResult.hasErrors()) {
            return "memberForm";
        }

        int n = memberService.memberAdd(memberDTO);
        model.addAttribute("success","회원가입성공"); // request객체에 담겨있는거
        return "redirect:/";
    }

}
