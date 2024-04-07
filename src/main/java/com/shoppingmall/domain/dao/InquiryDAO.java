package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.InquiryDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InquiryDAO {
    public void create(SqlSessionTemplate session, InquiryDTO inquiryDTO) {
       session.insert("InquiryMapper.create",inquiryDTO);
    }

    public List<InquiryDTO> findByMemberId(SqlSessionTemplate session, Long memberId) {
        return session.selectList("InquiryMapper.findByMemberId",memberId);
    }

    public void delete(SqlSessionTemplate session, Long inquiryId) {
        session.delete("InquiryMapper.delete",inquiryId);
    }

    public List<InquiryDTO> findByInquiryId(SqlSessionTemplate session, Long inquiryId) {
        return session.selectList("InquiryMapper.findByInquiryId",inquiryId);
    }
}
