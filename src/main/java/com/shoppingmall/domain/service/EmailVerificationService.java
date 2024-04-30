package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.EmailVerificationDAO;
import com.shoppingmall.domain.dao.member.MemberDAO;
import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationCreateRequestDTO;
import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationDAO emailVerificationDAO;
    private final MemberDAO memberDAO;

    public EmailVerificationCreateRequestDTO create(Long memberId) {
        EmailVerificationCreateRequestDTO requestDTO = new EmailVerificationCreateRequestDTO();
        requestDTO.setMemberId(memberId);
        requestDTO.setVerificationKey(UUID.randomUUID().toString());
        requestDTO.setExpiresAt(LocalDateTime.now().plusHours(1));
        emailVerificationDAO.create(requestDTO);
        return requestDTO;
    }

    public boolean verifyToken(String verificationKey) {
        EmailVerificationDTO byVerificationKey = emailVerificationDAO.findByVerificationKey(verificationKey);
        if (byVerificationKey == null || byVerificationKey.getExpiresAt().isBefore(LocalDateTime.now())) {
            return false;
        }
        memberDAO.activateMemberById(byVerificationKey.getMemberId());
        emailVerificationDAO.deleteByVerificationKey(verificationKey);
        return true;
    }
}
