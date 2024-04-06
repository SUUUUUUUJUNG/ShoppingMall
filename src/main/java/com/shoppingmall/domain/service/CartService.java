package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.CartDAO;
import com.shoppingmall.domain.dto.OrderDTO;
import com.shoppingmall.domain.dto.cart.CartDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void delete(int num) {
        dao.delete(session, num);
    }

    public void deleteAll(List<String> list) {
        dao.deleteAll(session, list);
    }

    public CartListResponseDTO findByCartId(String cartid) {
        return dao.findByCartId(session, cartid);
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
