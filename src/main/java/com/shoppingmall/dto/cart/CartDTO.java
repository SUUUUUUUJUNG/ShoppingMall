package com.shoppingmall.dto.cart;

import org.apache.ibatis.type.Alias;

@Alias("CartDTO")
public class CartDTO {

	private Long cartId;
	private String userId;
	private String gCode;
	private String gSize;
	private String gColor;
	private int gAmount;

	public CartDTO(Long cartId, String userId, String gCode, String gSize, String gColor, int gAmount) {
		this.cartId = cartId;
		this.userId = userId;
		this.gCode = gCode;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
	}

	public CartDTO() {
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
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

	@Override
	public String toString() {
		return "CartDTO{" +
				"cartId=" + cartId +
				", userId='" + userId + '\'' +
				", gCode='" + gCode + '\'' +
				", gSize='" + gSize + '\'' +
				", gColor='" + gColor + '\'' +
				", gAmount=" + gAmount +
				'}';
	}
}
