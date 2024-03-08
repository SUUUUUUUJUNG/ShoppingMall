package com.shoppingmall.service;

import java.util.List;
import java.util.Map;

import com.shoppingmall.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.dao.GoodsDAO;
import com.shoppingmall.dto.CartDTO;
import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.dto.OrderDTO;

@Service
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsDAO dao;
	private final SqlSessionTemplate session;

	public List<GoodsDTO> goodsList(String gCategory) {
		return dao.goodsList(session, gCategory);
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		return dao.goodsRetrieve(session, gCode);
	}

//	public void cartAdd(CartDTO cdto) {
//		dao.cartAdd(session,cdto);
//	}

	public List<CartDTO> cartList(String userid) {
		return dao.cartList(session, userid);
	}

	public void cartUpdate(Map<String, String> m) {
		dao.cartUpdate(session, m);
	}

	public void cartDel(int num) {
		dao.cartDel(session, num);
	}

	public void cartAllDel(List<String> list) {
		dao.cartAllDel(session, list);
	}

	public CartDTO cartByNum(String num) {
		return dao.cartByNum(session, num);
	}

	@Transactional
	public void orderDone(OrderDTO oDTO, Integer orderNum) {
		dao.orderDone(session, oDTO);//주문 정보 저장
		dao.cartDelete(session, orderNum); //카트에서 삭제 두 처리를 tx 처리함 root-context.xml에 tx-Manager등록 필요

	}

	public void mergeCartItems(Map<String, String> map, CartDTO dto) {
		CartDTO duplicateCart = dao.findDuplicateCartItem(session, map);
		System.out.println("GoodsService duplicateCart = " + duplicateCart);
		if (duplicateCart == null) {
			dao.cartAdd(session, dto);
		} else {
			dao.updateItemQuantity(session, map);
		}
	}

	public void toggleWishlistItem(Map<String, String> map) {
		if(isItemWishlisted(map)){
			dao.deleteWishlistItem(session, map);
		} else {
			dao.insertWishlistItem(session, map);
		}
	}

	public boolean isItemWishlisted(Map<String,String> map){
		List<MemberDTO> existingItems = dao.checkWishlistItemExists(session, map);
		return !existingItems.isEmpty();
	}
}