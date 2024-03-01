package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("OrderDTO")
@Getter
@Setter
public class OrderDTO {
	int num;
	String userid;
	String gCode;
	String gName;
	int gPrice;
	String gSize;
	String gColor;
	int gAmount;
	String gImage;
	String orderName;
	String zip_Code;
	String address;
	String addr_Detail;
	String phonenumber;
	String payMethod;
	String orderday;

	public OrderDTO(int num, String userid, String gCode, String gName, int gPrice, String gSize, String gColor, int gAmount, String gImage, String orderName, String zip_Code, String address, String addr_Detail, String phonenumber, String payMethod, String orderday) {
		this.num = num;
		this.userid = userid;
		this.gCode = gCode;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.gImage = gImage;
		this.orderName = orderName;
		this.zip_Code = zip_Code;
		this.address = address;
		this.addr_Detail = addr_Detail;
		this.phonenumber = phonenumber;
		this.payMethod = payMethod;
		this.orderday = orderday;
	}

	public OrderDTO() {
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"num=" + num +
				", userid='" + userid + '\'' +
				", gCode='" + gCode + '\'' +
				", gName='" + gName + '\'' +
				", gPrice=" + gPrice +
				", gSize='" + gSize + '\'' +
				", gColor='" + gColor + '\'' +
				", gAmount=" + gAmount +
				", gImage='" + gImage + '\'' +
				", orderName='" + orderName + '\'' +
				", zip_Code='" + zip_Code + '\'' +
				", address='" + address + '\'' +
				", addr_Detail='" + addr_Detail + '\'' +
				", phonenumber='" + phonenumber + '\'' +
				", payMethod='" + payMethod + '\'' +
				", orderday='" + orderday + '\'' +
				'}';
	}
}
