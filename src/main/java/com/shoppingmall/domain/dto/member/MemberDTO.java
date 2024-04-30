package com.shoppingmall.domain.dto.member;

import com.shoppingmall.domain.enums.MemberStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("MemberDTO")
@ToString
public class MemberDTO {

	private Long memberId;
	@NotBlank(message = "아이디를 올바르게 입력해주세요.")
	@Size(min=3, max=30, message = "아이디는 3자 이상 30자 이하로 입력해주세요.")
	private String username;

	@NotBlank(message = "이름을 정확히 입력해주세요.")
	@Size(min=2, max=30)
	private String realName;

	@NotBlank(message = "비밀번호를 정확히 입력해주세요.")
	@Size(min=8, max=30 , message = "영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.")
	private String password;

	@NotBlank(message = "이메일을 올바르게 입력해주세요.")
	@Email(message = "유효한 이메일 주소를 입력해주세요.")
	private String email;

	@NotBlank(message = "휴대폰 번호를 정확하게 입력하세요.")
	@Size(min=10, max=11, message = "휴대폰 번호를 확인해주세요.")
	private String phoneNumber;

	@NotBlank
	private String zip_Code;

	@NotBlank
	private String address;

	@NotBlank
	private String addr_Detail;

	private MemberStatus status;

	@NotBlank
	private String role;

	public MemberDTO() {
	}

	public MemberDTO(Long memberId, String username, String realName, String password, String email, String phoneNumber, String zip_Code, String address, String addr_Detail, MemberStatus status, String role) {
		this.memberId = memberId;
		this.username = username;
		this.realName = realName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.zip_Code = zip_Code;
		this.address = address;
		this.addr_Detail = addr_Detail;
		this.status = status;
		this.role = role;
	}

	public MemberDTO(long memberId) {
		this.memberId = memberId;
	}

	public void update(MemberUpdateRequestDTO requestDTO) {
		this.password = requestDTO.getNewPassword();
		this.zip_Code = requestDTO.getZip_Code();
		this.address = requestDTO.getAddress();
		this.addr_Detail = requestDTO.getAddr_Detail();
	}
}
