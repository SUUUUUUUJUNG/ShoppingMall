package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.ReviewDAO;
import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    public Integer create(ReviewCreateRequestDTO requestDTO) {
        return reviewDAO.create(requestDTO);
    }


    public ReviewDTO findByReviewId(Long reviewId) {
        return reviewDAO.findByReviewId(reviewId);
    }
}
