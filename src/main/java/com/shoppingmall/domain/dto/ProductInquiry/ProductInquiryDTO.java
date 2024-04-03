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
        this.gCode = requestDTO.getCode();
        this.inquiry_Content = requestDTO.getInquiry_Content();
    }

    public ProductInquiryDTO(Long memberId, String inquiry_Content) {
        this.memberId = memberId;
        this.inquiry_Content = inquiry_Content;
    }

    public ProductInquiryDTO(Long inquiry_Id, Long memberId, String gCode, String inquiry_Content) {
        this.inquiry_Id = inquiry_Id;
        this.memberId = memberId;
        this.gCode = gCode;
        this.inquiry_Content = inquiry_Content;
    }

    public ProductInquiryDTO() {

    }
}
