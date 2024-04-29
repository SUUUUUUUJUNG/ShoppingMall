package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.EmailService;
import com.shoppingmall.domain.service.member.MemberService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class EmailVerificationController {


    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping
    public String emailVerifyForm(@RequestParam(value = "username", required = false) String username, Model model) {
        if(username == null) {
            return "redirect:/login";
        }
        MemberDTO user = memberService.findByUsername(username);
        model.addAttribute("user", user);
        return "verificationEmail";
    }

    @PostMapping("/send")
    public String emailVerify(@RequestParam("username") String username, @RequestParam("email") String email) throws MessagingException {
        emailService.sendVerificationEmail(username, email);
        return "redirect:/login";
    }
}
