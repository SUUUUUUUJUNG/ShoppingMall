package com.shoppingmall.dao;

import java.util.List;
import java.util.Map;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.dto.cart.CartListResponseDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.shoppingmall.dto.cart.CartDTO;
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

	public void cartAdd(SqlSessionTemplate session,CartDTO dto) {
		System.out.println("dto = " + dto);
		session.insert("CartMapper.cartAdd",dto);
	}

	public List<CartListResponseDTO> cartList(SqlSessionTemplate session, String userId) {
        return session.selectList("CartMapper.cartByNum",userId);
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

	public CartDTO cartByNum(SqlSessionTemplate session,String cartId) {
        return session.selectOne("CartMapper.cartByNum",cartId);
	}

	public void orderDone(SqlSessionTemplate session,OrderDTO oDTO) {
		session.insert("CartMapper.orderDone",oDTO);

	}

	public void cartDelete(SqlSessionTemplate session,Integer orderNum) {
		session.delete("CartMapper.cartDel",orderNum);

	}


    public CartDTO findDuplicateCartItem(SqlSessionTemplate session, Map<String, String> map) {
		return session.selectOne("CartMapper.findDuplicateCartItem", map);
    }


	public void updateItemQuantity(SqlSessionTemplate session, Map<String,String> map) {
		session.update("CartMapper.updateItemQuantity",map);
	}

	public void insertWishlistItem(SqlSessionTemplate session, Map<String, String> map) {
		System.out.println("DAO map = " + map);
		session.insert("WishListMapper.insertWishlistItem",map);
	}


	public List<MemberDTO> checkWishlistItemExists(SqlSessionTemplate session, Map<String, String> map) {
		return session.selectList("WishListMapper.checkWishlistItemExists",map);
	}

	public void deleteWishlistItem(SqlSessionTemplate session, Map<String, String> map) {
		session.delete("WishListMapper.deleteWishlistItem",map);
	}
}
