package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
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

    public List<ProductInquiryDTO> findAllByGCode(String gCode) {
        return session.selectList("ProductInquiryMapper.findAllByGCode",gCode);
    }

    public int delete(Long inquiryId) {
        return session.delete("ProductInquiryMapper.delete",inquiryId);
    }

    public ProductInquiryDTO findById(Long inquiryId) {
        return session.selectOne("ProductInquiryMapper.findById", inquiryId);
    }

    public int update(ProductInquiryUpdateRequestDTO requestDTO) {
        return session.update("ProductInquiryMapper.update",requestDTO);
    }
}
