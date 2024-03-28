package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInquiryService {

    @Autowired
    ProductInquiryDAO productInquiryDAO;

    public Integer create(ProductInquiryDTO productInquiryDTO) {
        return productInquiryDAO.create(productInquiryDTO);
    }
}
