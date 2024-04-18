package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.cart.CartDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartDAO {

    public void create(SqlSessionTemplate session, Map<String,String> map) {
        session.insert("CartMapper.create",map);
    }

    public List<CartListResponseDTO> findByUsername(SqlSessionTemplate session, String userId) {
        return session.selectList("CartMapper.findByUsername",userId);
    }

    public void update(SqlSessionTemplate session, Map<String, String> m) {
        session.selectList("CartMapper.update",m);
    }

    public int delete(SqlSessionTemplate session, Long num) {
        return session.delete("CartMapper.delete",num);
    }

    public void deleteAll(SqlSessionTemplate session, List<String> list) {
        session.delete("CartMapper.deleteAll",list);
    }

    public CartListResponseDTO findByCartId(SqlSessionTemplate session, Long cartId) {
        return session.selectOne("CartMapper.findByCartId",cartId);
    }

    public CartDTO findDuplicateCartItem(SqlSessionTemplate session, Map<String, String> map) {
        return session.selectOne("CartMapper.findDuplicateCartItem", map);
    }

    public void updateItemQuantity(SqlSessionTemplate session, Map<String,String> map) {
        session.update("CartMapper.updateItemQuantity",map);
    }

    public void deleteByOrderNum(SqlSessionTemplate session, Integer orderNum){
        session.delete("CartMapper.deleteByOrderNum",orderNum);
    }

}
