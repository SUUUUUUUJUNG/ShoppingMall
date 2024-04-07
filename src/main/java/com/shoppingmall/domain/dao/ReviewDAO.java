package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.ReviewDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(ReviewDTO reviewDTO) {
        return session.insert("ReviewMapper.create",reviewDTO);
    }
}
