package com.shoppingmall.dao;

import com.shoppingmall.dto.InquiryDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryDAO {
    public void submitInquiry(SqlSessionTemplate session, InquiryDTO inquiryDTO) {
       session.insert("InquiryMapper.submitInquiry",inquiryDTO);
    }
}
