package com.shoppingmall.domain.dto.goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsResponseDTO {

    private String code;
    private String category;
    private String name;
    private String content;
    private int price;
    private String image;

    public GoodsResponseDTO() {
    }

    public GoodsResponseDTO(GoodsDTO goodsDTO) {
        this.code = goodsDTO.getGCode();
        this.category = goodsDTO.getGCategory();
        this.name = goodsDTO.getGName();
        this.content = goodsDTO.getGContent();
        this.price = goodsDTO.getGPrice();
        this.image = goodsDTO.getGImage();
    }
}
