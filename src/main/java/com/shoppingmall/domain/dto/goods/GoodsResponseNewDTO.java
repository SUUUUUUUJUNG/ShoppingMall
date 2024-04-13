package com.shoppingmall.domain.dto.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodsResponseNewDTO {
    private String gCode;
    private String gCategory;
    private String gName;
    private String gContent;
    private int gPrice;
    private String gImage;
    private List<String> images;
}
