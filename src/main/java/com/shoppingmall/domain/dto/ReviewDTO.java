package com.shoppingmall.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("ReviewDTO")
@Getter
@Setter
public class ReviewDTO {

    private Long review_Id;

    private String gCode;

    private Long memberId;

    private String review_Text;

    private int rating;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public ReviewDTO(Long memberId) {
    }

    public ReviewDTO() {
    }
}
