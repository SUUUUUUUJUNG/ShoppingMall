package com.shoppingmall.domain.dto.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCreateRequestDTO {

    private String gCode;
    private String gCategory;
    private String gName;
    private String gContent;
    private int gPrice;
    private String gImage;
}
