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
	private String passwd;
	private String email;
	private String phonenumber;
	private String zipCode;
	private String addr;
	private String addrDetail;
	private String status;
	private String role;

	public MemberDTO(String memberid, String userid, String username, String passwd, String email, String phonenumber, String zipCode, String addr, String addrDetail, String status, String role) {
		this.memberid = memberid;
		this.userid = userid;
		this.username = username;
		this.passwd = passwd;
		this.email = email;
		this.phonenumber = phonenumber;
		this.zipCode = zipCode;
		this.addr = addr;
		this.addrDetail = addrDetail;
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
				", passwd='" + passwd + '\'' +
				", email='" + email + '\'' +
				", phonenumber='" + phonenumber + '\'' +
				", zipCode='" + zipCode + '\'' +
				", addr='" + addr + '\'' +
				", addrDetail='" + addrDetail + '\'' +
				", status='" + status + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
