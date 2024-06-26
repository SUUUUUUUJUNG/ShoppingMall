package com.shoppingmall.domain.service.goods;

import com.shoppingmall.domain.dao.goods.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInquiryService {

   private final ProductInquiryDAO productInquiryDAO;

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
