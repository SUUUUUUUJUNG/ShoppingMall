package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
@Getter
@Setter
@ToString
@Alias("OrderDTO")
public class OrderDTO {
	Long id;
	String userId;
	String gCode;
	String gName;
	int gPrice;
	String gSize;
	String gColor;
	int gAmount;
	String orderName;
	String zip_Code;
	String address;
	String addr_Detail;
	String phoneNumber;
	String payMethod;
	String orderDay;

	public OrderDTO() {
	}

	public OrderDTO(Long id, String userId, String gCode, String gName, int gPrice, String gSize, String gColor, int gAmount, String orderName, String zip_Code, String address, String addr_Detail, String phoneNumber, String payMethod, String orderDay) {
		this.id = id;
		this.userId = userId;
		this.gCode = gCode;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.orderName = orderName;
		this.zip_Code = zip_Code;
		this.address = address;
		this.addr_Detail = addr_Detail;
		this.phoneNumber = phoneNumber;
		this.payMethod = payMethod;
		this.orderDay = orderDay;
	}


}
