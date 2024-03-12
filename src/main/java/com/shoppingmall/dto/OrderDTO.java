package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("OrderDTO")
public class OrderDTO {
	int num;
	String userId;
	String gCode;
	String gName;
	int gPrice;
	String gSize;
	String gColor;
	int gAmount;
	String gImage;
	String orderName;
	String zipCode;
	String address;
	String addrDetail;
	String phoneNumber;
	String payMethod;
	String orderDay;

	public OrderDTO(int num, String userId, String gCode, String gName, int gPrice, String gSize, String gColor, int gAmount, String gImage, String orderName, String zipCode, String address, String addr_Detail, String phoneNumber, String payMethod, String orderDay) {
		this.num = num;
		this.userId = userId;
		this.gCode = gCode;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.gImage = gImage;
		this.orderName = orderName;
		this.zipCode = zipCode;
		this.address = address;
		this.addrDetail = addrDetail;
		this.phoneNumber = phoneNumber;
		this.payMethod = payMethod;
		this.orderDay = orderDay;
	}

	public OrderDTO() {
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGCode() {
		return gCode;
	}

	public void setGCode(String gCode) {
		this.gCode = gCode;
	}

	public String getGName() {
		return gName;
	}

	public void setGName(String gName) {
		this.gName = gName;
	}

	public int getGPrice() {
		return gPrice;
	}

	public void setGPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public String getGSize() {
		return gSize;
	}

	public void setGSize(String gSize) {
		this.gSize = gSize;
	}

	public String getGColor() {
		return gColor;
	}

	public void setGColor(String gColor) {
		this.gColor = gColor;
	}

	public int getGAmount() {
		return gAmount;
	}

	public void setGAmount(int gAmount) {
		this.gAmount = gAmount;
	}

	public String getGImage() {
		return gImage;
	}

	public void setGImage(String gImage) {
		this.gImage = gImage;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddr_Detail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderDay() {
		return orderDay;
	}

	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"num=" + num +
				", userId='" + userId + '\'' +
				", gCode='" + gCode + '\'' +
				", gName='" + gName + '\'' +
				", gPrice=" + gPrice +
				", gSize='" + gSize + '\'' +
				", gColor='" + gColor + '\'' +
				", gAmount=" + gAmount +
				", gImage='" + gImage + '\'' +
				", orderName='" + orderName + '\'' +
				", zipCode='" + zipCode + '\'' +
				", address='" + address + '\'' +
				", addrDetail='" + addrDetail + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", payMethod='" + payMethod + '\'' +
				", orderDay='" + orderDay + '\'' +
				'}';
	}
}
