package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductInquiryDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(ProductInquiryDTO productInquiryDTO) {
        return session.insert("ProductInquiryMapper.create", productInquiryDTO);
    }

    public List<ProductInquiryDTO> findAllByMemberId(Long memberId) {
        return session.selectList("ProductInquiryMapper.findAllByMemberId",memberId);
    }
}
