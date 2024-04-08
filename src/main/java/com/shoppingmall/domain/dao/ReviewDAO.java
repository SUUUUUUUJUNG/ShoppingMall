package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import com.shoppingmall.domain.dto.review.ReviewUpdateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(ReviewCreateRequestDTO requestDTO) {
        return session.insert("ReviewMapper.create",requestDTO);
    }

    public ReviewDTO findByReviewId(Long reviewId) {
        return session.selectOne("ReviewMapper.findByReviewId",reviewId);
    }

    public List<ReviewDTO> findAllByGCode(String gCode) {
        return session.selectList("ReviewMapper.findAllByGCode",gCode);
    }

    public List<ReviewDTO> findAllByMemberId(Long memberId) {
        return session.selectList("ReviewMapper.findAllByMemberId",memberId);
    }

    public int update(ReviewUpdateRequestDTO requestDTO) {
        return session.update("ReviewMapper.update",requestDTO);
    }

    public int delete(Long reviewId) {
        return session.delete("ReviewMapper.delete",reviewId);
    }
}
