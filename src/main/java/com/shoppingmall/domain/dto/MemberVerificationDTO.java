package com.shoppingmall.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberVerificationDTO {
    private Long memberId;
    private String verificationKey;
    private LocalDate expiresAt;
}
