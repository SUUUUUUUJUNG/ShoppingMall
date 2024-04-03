package com.shoppingmall.domain.dto.ProductInquiry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductInquiryCreateRequestDTO {

    private Long memberId;
    private String code;
    private String inquiry_Content;
}
