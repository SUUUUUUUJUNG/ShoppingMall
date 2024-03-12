package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
@Getter
@Setter
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

	public MemberDTO() {
	}


}
