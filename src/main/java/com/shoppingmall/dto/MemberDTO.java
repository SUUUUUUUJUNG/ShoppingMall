package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
@Getter
@Setter
public class MemberDTO {

	private String memberid;
	private String userid;
	private String username;
	private String password;
	private String email;
	private String phonenumber;
	private String zip_Code;
	private String address;
	private String addr_Detail;
	private String status;
	private String role;

	public MemberDTO(String memberid, String userid, String username, String password, String email, String phonenumber, String zip_Code, String address, String addr_Detail, String status, String role) {
		this.memberid = memberid;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phonenumber = phonenumber;
		this.zip_Code = zip_Code;
		this.address = address;
		this.addr_Detail = addr_Detail;
		this.status = status;
		this.role = role;
	}

	public MemberDTO() {
	}

	@Override
	public String toString() {
		return "MemberDTO{" +
				"memberid='" + memberid + '\'' +
				", userid='" + userid + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", phonenumber='" + phonenumber + '\'' +
				", zip_Code='" + zip_Code + '\'' +
				", address='" + address + '\'' +
				", addr_Detail='" + addr_Detail + '\'' +
				", status='" + status + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
