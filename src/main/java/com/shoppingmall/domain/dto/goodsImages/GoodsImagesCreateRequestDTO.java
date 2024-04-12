package com.shoppingmall.domain.dto.goodsImages;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GoodsImagesCreateRequestDTO {

    private String code;
    private List<String> images;
}
