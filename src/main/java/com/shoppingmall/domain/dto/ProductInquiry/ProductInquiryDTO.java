package com.shoppingmall.domain.dto.ProductInquiry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProductInquiryDTO {

    private Long inquiry_Id;
    private Long memberId;
    private String gCode;
    private String inquiry_Content;
    private LocalDateTime inquiry_Date = LocalDateTime.now();
    private String status = "UNPROCESSED";
    private String response;

    public ProductInquiryDTO(ProductInquiryCreateRequestDTO requestDTO, Long memberId) {
        this.memberId = memberId;
        this.gCode = requestDTO.getGCode();
        this.inquiry_Content = requestDTO.getInquiry_Content();
    }
}
