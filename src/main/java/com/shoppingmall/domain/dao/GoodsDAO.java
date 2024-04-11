package com.shoppingmall.domain.dao;

import java.util.List;

import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.shoppingmall.domain.dto.goods.GoodsDTO;

@Repository
public class GoodsDAO {

	public List<GoodsDTO> findByCategory(SqlSessionTemplate session, String gCategory) {
        return session.selectList("GoodsMapper.findByCategory",gCategory);
	}

	public GoodsDTO findByCode(SqlSessionTemplate session, String gCode) {
        return session.selectOne("GoodsMapper.findByCode",gCode);
	}

	public Integer create(SqlSessionTemplate session, GoodsCreateRequestDTO requestDTO) {
		return session.insert("GoodsMapper.create",requestDTO);
	}

    public int delete(SqlSessionTemplate session, String gCode) {
		return session.delete("GoodsMapper.delete",gCode);

    }
}
