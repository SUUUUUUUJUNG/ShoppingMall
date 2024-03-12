package com.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

@Alias("GoodsDTO")
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
	public String geGCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	public String getGCategory() {
		return gCategory;
	}
	public void setgCategory(String gCategory) {
		this.gCategory = gCategory;
	}
	public String getGName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getGContent() {
		return gContent;
	}
	public void setgContent(String gContent) {
		this.gContent = gContent;
	}
	public int getGPrice() {
		return gPrice;
	}
	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}
	public String getGImage() {
		return gImage;
	}
	public void setgImage(String gImage) {
		this.gImage = gImage;
	}
	@Override
	public String toString() {
		return "GoodsDTO [gCode=" + gCode + ", gCategory=" + gCategory + ", gName=" + gName + ", gContent=" + gContent
				+ ", gPrice=" + gPrice + ", gImage=" + gImage + "]";
	}
	
	
	
}
