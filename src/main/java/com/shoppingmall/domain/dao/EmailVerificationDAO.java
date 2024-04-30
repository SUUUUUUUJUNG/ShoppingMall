package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationCreateRequestDTO;
import com.shoppingmall.domain.dto.EmailVerification.EmailVerificationDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationDAO {

    private final SqlSessionTemplate session;

    public Integer create(EmailVerificationCreateRequestDTO requestDTO) {
        return session.insert("EmailVerificationMapper.create", requestDTO);
    }

    public EmailVerificationDTO findByVerificationKey(String verificationKey) {
        return session.selectOne("EmailVerificationMapper.findByVerificationKey", verificationKey);
    }

    public void deleteByVerificationKey(String verificationKey) {
        session.delete("EmailVerificationMapper.deleteByVerificationKey", verificationKey);
    }
}
