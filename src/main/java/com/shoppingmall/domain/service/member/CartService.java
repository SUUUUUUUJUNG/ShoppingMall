package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.CartDAO;
import com.shoppingmall.domain.dto.cart.CartDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDAO dao;
    private final SqlSessionTemplate session;

    public List<CartListResponseDTO> findByUsername(String userId) {
        return dao.findByUsername(session, userId);
    }

    public void update(Map<String, String> m) {
        dao.update(session, m);
    }

    public int delete(Long num) {
        return dao.delete(session, num);
    }

    public void deleteAll(List<String> list) {
        dao.deleteAll(session, list);
    }

    public CartListResponseDTO findByCartId(Long cartId) {
        return dao.findByCartId(session, cartId);
    }

    public void mergeCartItems(Map<String, String> map) {
        System.out.println("GoodsService map = " + map);
        CartDTO duplicateCart = dao.findDuplicateCartItem(session, map);
        System.out.println("GoodsService duplicateCart = " + duplicateCart);
        if (duplicateCart == null) {
            dao.create(session, map);
        } else {
            dao.updateItemQuantity(session, map);
        }
    }
}
