package com.shoppingmall.domain.dto.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCreateRequestDTO {

    private String code;
    private String category;
    private String name;
    private String content;
    private int price;
    private String image;
}
