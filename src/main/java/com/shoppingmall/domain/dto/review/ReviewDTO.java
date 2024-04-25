package com.shoppingmall.domain.dto.review;

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

    private LocalDateTime created_At = LocalDateTime.now();

    private LocalDateTime updated_At;

    public ReviewDTO(Long memberId) {
    }

    public ReviewDTO() {
    }
}
