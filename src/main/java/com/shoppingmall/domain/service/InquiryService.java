package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.InquiryDAO;
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

    public void submitInquiry(InquiryDTO inquiryDTO) {
        inquiryDAO.submitInquiry(session,inquiryDTO);
    }

    public List<InquiryDTO> inquiriesList(Long memberId) {
        return inquiryDAO.inquiriesList(session,memberId);
    }

    public void deleteInquiryByMemberId(Long inquiryId) {
        inquiryDAO.deleteInquiryByMemberId(session,inquiryId);
    }

    public List<InquiryDTO> viewInquiryDetails(Long inquiryId) {
         return inquiryDAO.viewInquiryDetails(session,inquiryId);
    }
}
