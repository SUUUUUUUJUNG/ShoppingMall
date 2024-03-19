package com.shoppingmall.service;

import com.shoppingmall.dao.InquiryDAO;
import com.shoppingmall.dto.InquiryDTO;
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
}
