package com.shoppingmall.domain.dao;

import java.util.List;
import java.util.Map;

import com.shoppingmall.domain.dto.cart.CartDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.shoppingmall.domain.dto.GoodsDTO;
import com.shoppingmall.domain.dto.OrderDTO;

@Repository
public class GoodsDAO {

	public List<GoodsDTO> findByCategory(SqlSessionTemplate session, String gCategory) {
        return session.selectList("GoodsMapper.findByCategory",gCategory);
	}

	public GoodsDTO findByCode(SqlSessionTemplate session, String gCode) {
        return session.selectOne("GoodsMapper.findByCode",gCode);
	}
}
