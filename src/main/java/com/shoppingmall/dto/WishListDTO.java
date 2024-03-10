package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("WishListDTO")
public class WishListDTO {

    private Long wishListId;
    private String gCode;
    private String gName;
    private String gImage;
    private String gPrice;

    public WishListDTO(Long wishListId, String gCode, String gName, String gImage, String gPrice) {
        this.wishListId = wishListId;
        this.gCode = gCode;
        this.gName = gName;
        this.gImage = gImage;
        this.gPrice = gPrice;
    }

    public WishListDTO() {
    }

    @Override
    public String toString() {
        return "WishListDTO{" +
                "wishListId=" + wishListId +
                ", gCode='" + gCode + '\'' +
                ", gName='" + gName + '\'' +
                ", gImage='" + gImage + '\'' +
                ", gPrice='" + gPrice + '\'' +
                '}';
    }
}
