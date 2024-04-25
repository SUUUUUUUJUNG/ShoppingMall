package com.shoppingmall.web.controller;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;

    @GetMapping("/mail")
    @ResponseBody
    public String email(HttpSession session) throws MessagingException {
        emailService.sendVerificationEmail(new MemberDTO());
        return "ok";
    }
}
