package com.shoppingmall.domain.dto.member;

import jakarta.validation.constraints.*;
import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {

	private Long memberId;
	@NotBlank(message = "아이디를 올바르게 입력해주세요.")
	@Size(min=3, max=30, message = "아이디는 3자 이상 30자 이하로 입력해주세요.")
	private String userId;

	@NotBlank(message = "이름을 정확히 입력해주세요.")
	@Size(min=2, max=30)
	private String username;

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

	private String status;

	@NotBlank
	private String role;

	public MemberDTO() {
	}

	public MemberDTO(Long memberId, String userId, String username, String password, String email, String phoneNumber, String zip_Code, String address, String addr_Detail, String status, String role) {
		this.memberId = memberId;
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.zip_Code = zip_Code;
		this.address = address;
		this.addr_Detail = addr_Detail;
		this.status = status;
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemberDTO{" +
				"memberId=" + memberId +
				", userId='" + userId + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", zip_Code='" + zip_Code + '\'' +
				", address='" + address + '\'' +
				", addr_Detail='" + addr_Detail + '\'' +
				", status='" + status + '\'' +
				", role='" + role + '\'' +
				'}';
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getZip_Code() {
		return zip_Code;
	}

	public void setZip_Code(String zip_Code) {
		this.zip_Code = zip_Code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddr_Detail() {
		return addr_Detail;
	}

	public void setAddr_Detail(String addr_Detail) {
		this.addr_Detail = addr_Detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void update(MemberUpdateRequestDTO requestDTO) {
		this.password = requestDTO.getNewPassword();
		this.zip_Code = requestDTO.getZip_Code();
		this.address = requestDTO.getAddress();
		this.addr_Detail = requestDTO.getAddr_Detail();
	}
}
