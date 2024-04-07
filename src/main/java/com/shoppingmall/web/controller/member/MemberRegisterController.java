package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.MemberService;
import com.shoppingmall.web.service.RegistrationValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final RegistrationValidationService registrationValidationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/register")
    public String memberForm(Model model, MemberDTO memberDTO) {
        model.addAttribute("memberDTO", memberDTO);
        return "memberForm";
    }

    @PostMapping("/register")
    public String memberAdd(@Validated @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult, Model model) { // validated 이용
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult = " + bindingResult.getAllErrors());
            return "memberForm";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);
        int n = memberService.create(memberDTO);
        model.addAttribute("success","회원가입성공"); // request객체에 담겨있는거
        return "redirect:/";
    }

}
