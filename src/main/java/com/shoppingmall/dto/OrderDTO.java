package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("OrderDTO")

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

	public int getNum() {
		return num;
	}

	public String getUserid() {
		return userid;
	}

	public String getgCode() {
		return gCode;
	}

	public String getgName() {
		return gName;
	}

	public int getgPrice() {
		return gPrice;
	}

	public String getgSize() {
		return gSize;
	}

	public String getgColor() {
		return gColor;
	}

	public int getgAmount() {
		return gAmount;
	}

	public String getgImage() {
		return gImage;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getZip_Code() {
		return zip_Code;
	}

	public String getAddress() {
		return address;
	}

	public String getAddr_Detail() {
		return addr_Detail;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public String getOrderday() {
		return orderday;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public void setgSize(String gSize) {
		this.gSize = gSize;
	}

	public void setgColor(String gColor) {
		this.gColor = gColor;
	}

	public void setgAmount(int gAmount) {
		this.gAmount = gAmount;
	}

	public void setgImage(String gImage) {
		this.gImage = gImage;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setZip_Code(String zip_Code) {
		this.zip_Code = zip_Code;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddr_Detail(String addr_Detail) {
		this.addr_Detail = addr_Detail;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public void setOrderday(String orderday) {
		this.orderday = orderday;
	}
}
