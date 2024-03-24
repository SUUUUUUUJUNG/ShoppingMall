package com.shoppingmall.domain.dto;

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

    private Long inquiry_Id;
    private Long memberId;
    private String inquiry_Type;
    private String inquiry_Title;
    private String inquiry_Content;
    private LocalDateTime inquiry_Date = LocalDateTime.now();
    private String status = "UNPROCESSED";
    private String response;
}
