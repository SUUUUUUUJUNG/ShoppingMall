package com.shoppingmall.dao;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.dto.CartDTO;
import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.dto.OrderDTO;

@Repository
public class GoodsDAO {

	public List<GoodsDTO> goodsList(SqlSessionTemplate session,String gCategory) {
        return session.selectList("GoodsMapper.goodsList",gCategory);
	}

	public GoodsDTO goodsRetrieve(SqlSessionTemplate session,String gCode) {
        return session.selectOne("GoodsMapper.goodsRetrieve",gCode);
	}

	public void cartAdd(SqlSessionTemplate session,CartDTO cdto) {
		session.insert("CartMapper.cartAdd",cdto);
		
	}

	public List<CartDTO> cartList(SqlSessionTemplate session,String userid) {
        return session.selectList("CartMapper.cartList",userid);
	}

	public void cartUpdate(SqlSessionTemplate session,Map<String, String> m) {
		session.selectList("CartMapper.cartUpdate",m);
	}

	public void cartDel(SqlSessionTemplate session,int num) {
		session.delete("CartMapper.cartDel",num);
		
	}

	public void cartAllDel(SqlSessionTemplate session,List<String> list) {
		session.delete("CartMapper.cartAllDel",list);
		
	}

	public CartDTO cartByNum(SqlSessionTemplate session,String num) {
        return session.selectOne("CartMapper.cartByNum",num);
	}

	public void orderDone(SqlSessionTemplate session,OrderDTO oDTO) {
		session.insert("CartMapper.orderDone",oDTO);
		
	}

	public void cartDelete(SqlSessionTemplate session,Integer orderNum) {
		session.delete("CartMapper.cartDel",orderNum);
		
	}


}
