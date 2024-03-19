package com.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Alias("InquiryDTO")
public class InquiryDTO {

    private Long inquiryId;
    private Long memberId;
    private String inquiryType;
    private String inquiryTitle;
    private String inquiryContent;
    LocalDateTime inquiryData = LocalDateTime.now();
    private String status = "UNPROCESSED";

    public InquiryDTO() {
    }

    public InquiryDTO(Long inquiryId, Long memberId, String inquiryType, String inquiryTitle, String inquiryContent, LocalDateTime inquiryData, String status) {
        this.inquiryId = inquiryId;
        this.memberId = memberId;
        this.inquiryType = inquiryType;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryData = inquiryData;
        this.status = status;
    }
}
