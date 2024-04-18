package com.shoppingmall.domain.dto.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("CartListResponseDTO")
@Getter
@Setter
@ToString
public class CartListResponseDTO {

	private Long cartId;
	private String userId;
	private String gCode;
	private String gName;
	private int gPrice;
	private String gSize;
	private String gColor;
	private int gAmount;
	private String gImage;

	public CartListResponseDTO(Long cartId, String userId, String gCode, String gName, int gPrice, String gSize, String gColor, int gAmount, String gImage) {
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
}