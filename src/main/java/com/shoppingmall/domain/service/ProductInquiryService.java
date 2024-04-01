package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInquiryService {

    @Autowired
    ProductInquiryDAO productInquiryDAO;

    public Integer create(ProductInquiryDTO productInquiryDTO) {
        return productInquiryDAO.create(productInquiryDTO);
    }

    public List<ProductInquiryDTO> findAllByMemberId(Long memberId) {
        return productInquiryDAO.findAllByMemberId(memberId);
    }

    public List<ProductInquiryDTO> findAllByGCode(String gCode) {
        return productInquiryDAO.findAllByGCode(gCode);
    }

    public List<ProductInquiryDTO> updateProductInquiry(Long inquiryId) {
        return productInquiryDAO.updateProductInquiry(inquiryId);
    }
}
