package com.shoppingmall.dao;

import com.shoppingmall.dto.InquiryDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InquiryDAO {
    public void submitInquiry(SqlSessionTemplate session, InquiryDTO inquiryDTO) {
       session.insert("InquiryMapper.submitInquiry",inquiryDTO);
    }

    public List<InquiryDTO> inquiriesList(SqlSessionTemplate session, Long memberId) {
        return session.selectList("InquiryMapper.inquiriesList",memberId);
    }

    public void deleteInquiryByMemberId(SqlSessionTemplate session, Long inquiryId) {
        session.delete("InquiryMapper.deleteInquiryByMemberId",inquiryId);
    }

    public List<InquiryDTO> viewInquiryDetails(SqlSessionTemplate session, Long inquiryId) {
        return session.selectList("InquiryMapper.viewInquiryDetails",inquiryId);
    }
}
