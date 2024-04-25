package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dto.member.MemberDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationEmail(MemberDTO memberDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("admin@seed.com");
        helper.setTo("shinssjj79@gmail.com");
        helper.setSubject("이메일 test");
        helper.setText("test", true); // true를 설정하여 HTML 컨텐츠로 인식하도록 합니다.

        mailSender.send(message);
    }

}
