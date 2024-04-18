package com.shoppingmall.domain.service.goods;

import java.util.List;

import com.shoppingmall.domain.dao.goods.GoodsImagesDAO;
import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import com.shoppingmall.domain.dto.goods.GoodsResponseNewDTO;
import com.shoppingmall.domain.dto.goods.GoodsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.shoppingmall.domain.dao.goods.GoodsDAO;
import com.shoppingmall.domain.dto.goods.GoodsDTO;

@Service
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsDAO goodsDAO;
	private final GoodsImagesDAO goodsImagesDAO;
	private final SqlSessionTemplate session;

	public List<GoodsDTO> findByCategory(String gCategory) {
		return goodsDAO.findByCategory(session, gCategory);
	}

	public GoodsDTO findByCode(String gCode) {
		return goodsDAO.findByCode(session, gCode);
	}

	public Integer create(GoodsCreateRequestDTO requestDTO) {
		return goodsDAO.create(session,requestDTO);
	}

	public int delete(String gCode) {
		return goodsDAO.delete(session,gCode);
	}

    public int update(GoodsUpdateRequestDTO requestDTO) {
		return goodsDAO.update(session, requestDTO);
    }

	public List<GoodsResponseNewDTO> findAll() {
		List<GoodsResponseNewDTO> list = goodsDAO.findAll(session);
		return list;
	}
}