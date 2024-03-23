package com.shoppingmall.domain.dto.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("CartListResponseDTO")
public class CartListResponseDTO {

	private int cartId;
	private String userId;
	private String gCode;
	private String gName;
	private int gPrice;
	private String gSize;
	private String gColor;
	private int gAmount;
	private String gImage;

	public CartListResponseDTO(int cartId, String userId, String gCode, String gName, int gPrice, String gSize, String gColor, int gAmount, String gImage) {
		this.cartId = cartId;
		this.userId = userId;
		this.gCode = gCode;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.gImage = gImage;
	}

	public CartListResponseDTO() {
	}

	@Override
	public String toString() {
		return "CartListResponseDTO{" +
				"cartId=" + cartId +
				", userId='" + userId + '\'' +
				", gCode='" + gCode + '\'' +
				", gName='" + gName + '\'' +
				", gPrice=" + gPrice +
				", gSize='" + gSize + '\'' +
				", gColor='" + gColor + '\'' +
				", gAmount=" + gAmount +
				", gImage='" + gImage + '\'' +
				'}';
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
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
}