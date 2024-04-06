package com.shoppingmall.domain.service;

import java.util.List;
import java.util.Map;

import com.shoppingmall.domain.dto.cart.CartDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.domain.dao.GoodsDAO;
import com.shoppingmall.domain.dto.GoodsDTO;
import com.shoppingmall.domain.dto.OrderDTO;

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

}