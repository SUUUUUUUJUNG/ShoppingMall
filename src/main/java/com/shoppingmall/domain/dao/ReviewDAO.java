package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(ReviewCreateRequestDTO requestDTO) {
        return session.insert("ReviewMapper.create",requestDTO);
    }

    public List<ReviewDTO> findByReviewId(Long reviewId) {
        return session.selectList("ReviewMapper.findByReviewId",reviewId);
    }
}
