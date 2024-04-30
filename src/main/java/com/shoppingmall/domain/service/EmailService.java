package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationCreateRequestDTO;
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

    public void sendVerificationEmail(String username, String email, EmailVerificationCreateRequestDTO requestDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("admin@shopping.com");
        helper.setTo(email);
        helper.setSubject(username + " 님 이메일 test");

        String mailContent =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; color: #333;'>" +
                        "   <div style='max-width: 600px; margin: 20px auto; border: 1px solid #ddd; padding: 20px; background-color: #f0f8f7;'>" +
                        "       <h2 style='color: #2e8b57; text-align: center;'>오늘의 쇼핑 회원 인증</h2>" +
                        "       <p style='font-size: 16px; text-align: center;'>" +
                        "           안녕하세요, <strong>" + username + "</strong>님! 오늘의 쇼핑에 오신 것을 환영합니다.<br>" +
                        "           계정 활성화를 위해 아래의 버튼을 클릭해 주세요." +
                        "       </p>" +
                        "       <div style='text-align: center; margin: 40px 0;'>" +
                        "           <a href='http://localhost:8092/verify/result?verificationKey=" + requestDTO.getVerificationKey() + "'" +
                        "              style='display: inline-block; background-color: #2e8b57; color: #ffffff; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>" +
                        "               계정 활성화" +
                        "           </a>" +
                        "       </div>" +
                        "       <p style='font-size: 14px; color: #666;'>" +
                        "           만약 위의 버튼이 작동하지 않는다면, 아래의 링크를 복사하여 브라우저에 붙여넣기 해주세요:<br>" +
                        "           <a href='http://localhost:8092/verify/result?verificationKey=" + requestDTO.getVerificationKey() + "' style='color: #007bff;'>http://localhost:8092/verify/result?token=" + requestDTO.getVerificationKey() + "</a>" +
                        "       </p>" +
                        "   </div>" +
                        "   <footer style='text-align: center; font-size: 12px; color: #777;'>" +
                        "       이 메일은 오늘의 쇼핑 회원가입 과정 중 생성되었습니다. 문의사항이 있는 경우, 고객 지원 센터에 연락해 주세요.<br>" +
                        "       &copy; 오늘의 쇼핑 All Rights Reserved" +
                        "   </footer>" +
                        "</body>" +
                        "</html>";

        helper.setText(mailContent, true); // true를 설정하여 HTML 컨텐츠로 인식하도록 합니다.

        mailSender.send(message);
    }
}
