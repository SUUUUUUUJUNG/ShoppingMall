package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.GoodsImagesDAO;
import com.shoppingmall.domain.dto.goodsImages.GoodsImagesCreateRequestDTO;
import com.shoppingmall.domain.dto.goodsImages.GoodsImagesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsImagesService {

    private final GoodsImagesDAO goodsImagesDAO;

    @Transactional
    public void create(GoodsImagesCreateRequestDTO requestDTO) {
        goodsImagesDAO.deleteAll(requestDTO.getCode());

        List<String> images = requestDTO.getImages();
        for (String image : images) {
            goodsImagesDAO.create(image, requestDTO.getCode());
        }
    }

    public int delete(Long id) {
        return goodsImagesDAO.delete(id);
    }

    public List<GoodsImagesDTO> findAllByGCode(String gCode) {
        return goodsImagesDAO.findAllByGCode(gCode);
    }
}
