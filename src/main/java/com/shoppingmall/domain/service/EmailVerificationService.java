package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.EmailVerificationDAO;
import com.shoppingmall.domain.dao.member.MemberDAO;
import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationCreateRequestDTO;
import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationDAO emailVerificationDAO;
    private final MemberDAO memberDAO;

    public Integer create(EmailVerificationCreateRequestDTO requestDTO) {
        requestDTO.setVerificationKey(UUID.randomUUID().toString());
        requestDTO.setExpiresAt(LocalDateTime.now().plusHours(1));
        return emailVerificationDAO.create(requestDTO);
    }

    public void activateMemberUsingVerificationKey(String verificationKey) {
        EmailVerificationDTO byVerificationKey = emailVerificationDAO.findByVerificationKey(verificationKey);
        if (byVerificationKey == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효하지 않은 인증키입니다.");
        }
        if (byVerificationKey.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "인증키의 만료 시간이 지났습니다.");
        }
        int num = memberDAO.activateMemberById(byVerificationKey.getMemberId());
    }

}
