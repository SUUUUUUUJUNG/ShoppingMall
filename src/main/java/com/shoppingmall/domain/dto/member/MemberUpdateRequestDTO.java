package com.shoppingmall.domain.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberUpdateRequestDTO {

    private Long memberId;

    @NotBlank(message = "비밀번호를 정확히 입력해주세요.")
    @Size(min=8, max=30 , message = "영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.")
    private String currentPassword;

    @NotBlank(message = "비밀번호를 정확히 입력해주세요.")
    @Size(min=8, max=30 , message = "영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.")
    private String newPassword;

    @NotBlank
    private String zip_Code;

    @NotBlank
    private String address;

    @NotBlank
    private String addr_Detail;
}
