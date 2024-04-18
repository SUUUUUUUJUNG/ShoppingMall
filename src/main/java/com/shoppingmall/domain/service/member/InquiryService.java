package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.InquiryDAO;
import com.shoppingmall.domain.dto.InquiryDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDAO inquiryDAO;
    private final SqlSessionTemplate session;

    public void create(InquiryDTO inquiryDTO) {
        inquiryDAO.create(session,inquiryDTO);
    }

    public List<InquiryDTO> findByMemberId(Long memberId) {
        return inquiryDAO.findByMemberId(session,memberId);
    }

    public void delete(Long inquiryId) {
        inquiryDAO.delete(session,inquiryId);
    }

    public List<InquiryDTO> findByInquiryId(Long inquiryId) {
         return inquiryDAO.findByInquiryId(session,inquiryId);
    }
}
