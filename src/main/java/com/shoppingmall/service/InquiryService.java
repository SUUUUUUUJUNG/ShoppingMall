package com.shoppingmall.service;

import com.shoppingmall.dao.InquiryDAO;
import com.shoppingmall.dto.InquiryDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDAO inquiryDAO;
    private final SqlSessionTemplate session;

    public void submitInquiry(InquiryDTO inquiryDTO) {
        inquiryDAO.submitInquiry(session,inquiryDTO);
    }
}
