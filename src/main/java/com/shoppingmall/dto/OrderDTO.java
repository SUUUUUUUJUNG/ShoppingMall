package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Alias("OrderDTO")
public class OrderDTO {
	Long order_Id;
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
	Double totalPrice;
	LocalDateTime orderDay = LocalDateTime.now();
}
