package com.shoppingmall.domain.dto.ProductInquiry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInquiryCreateRequestDTO {

    private Long memberId;
    private String gCode;
    private String inquiry_Content;
}
