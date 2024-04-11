package com.shoppingmall.domain.service;

import java.util.List;

import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.shoppingmall.domain.dao.GoodsDAO;
import com.shoppingmall.domain.dto.goods.GoodsDTO;

@Service
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsDAO dao;
	private final SqlSessionTemplate session;

	public List<GoodsDTO> findByCategory(String gCategory) {
		return dao.findByCategory(session, gCategory);
	}

	public GoodsDTO findByCode(String gCode) {
		return dao.findByCode(session, gCode);
	}

	public Integer create(GoodsCreateRequestDTO requestDTO) {
		return dao.create(session,requestDTO);
	}
}