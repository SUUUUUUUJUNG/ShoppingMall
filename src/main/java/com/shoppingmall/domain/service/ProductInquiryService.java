package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
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


    public boolean delete(Long inquiryId) {
        int deletedRows = productInquiryDAO.delete(inquiryId);
        return deletedRows > 0;
    }

    public ProductInquiryDTO findById(Long inquiryId){
        return productInquiryDAO.findById(inquiryId);
    }

    public int update(ProductInquiryUpdateRequestDTO requestDTO) {
        return productInquiryDAO.update(requestDTO);
    }
}
