package com.shoppingmall.domain.dto.review;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ReviewCreateRequestDTO {

    private String code;

    private Long memberId;

    private String review_Text;

    private int rating;

    private LocalDateTime createdAt = LocalDateTime.now();
}
