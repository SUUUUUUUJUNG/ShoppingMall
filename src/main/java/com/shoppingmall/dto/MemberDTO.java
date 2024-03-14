package com.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {

	private Long memberId;
	private String userId;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private String zip_Code;
	private String address;
	private String addr_Detail;
	private String status;
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
}
