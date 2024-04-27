package com.shoppingmall.domain.dto.EmailVerification;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailVerificationCreateRequestDTO {
    private Long memberId;
    private String verificationKey;
    private LocalDateTime expiresAt;
}
