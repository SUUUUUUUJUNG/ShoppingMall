package com.shoppingmall.domain.dto.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewUpdateRequestDTO {

    private Long review_Id;
    private String review_Text;
    private int rating;


}
