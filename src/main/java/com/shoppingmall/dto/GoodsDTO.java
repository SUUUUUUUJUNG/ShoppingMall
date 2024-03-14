package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("GoodsDTO")
@Getter
@Setter
public class GoodsDTO {

	private String gCode;
	private String gCategory;
	private String gName;
	private String gContent;
	private int gPrice;
	private String gImage;
	public GoodsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsDTO(String gCode, String gCategory, String gName, String gContent, int gPrice, String gImage) {
		super();
		this.gCode = gCode;
		this.gCategory = gCategory;
		this.gName = gName;
		this.gContent = gContent;
		this.gPrice = gPrice;
		this.gImage = gImage;
	}
	@Override
	public String toString() {
		return "GoodsDTO{" +
				"gCode='" + gCode + '\'' +
				", gCategory='" + gCategory + '\'' +
				", gName='" + gName + '\'' +
				", gContent='" + gContent + '\'' +
				", gPrice=" + gPrice +
				", gImage='" + gImage + '\'' +
				'}';
	}
}
