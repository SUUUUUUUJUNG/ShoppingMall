package com.shoppingmall.domain.dao.member;

import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class WishListDAO {

    private final SqlSessionTemplate session;

    public void insertWishlistItem(SqlSessionTemplate session, Map<String, String> map) {
        System.out.println("DAO map = " + map);
        session.insert("WishListMapper.insertWishlistItem", map);
    }

    public List<WishListDTO> findByMemberId(Long memberId) {
        return session.selectList("WishListMapper.findWishListByMemberId", memberId);
    }

    public List<MemberDTO> checkWishlistItemExists(SqlSessionTemplate session, Map<String, String> map) {
        return session.selectList("WishListMapper.checkWishlistItemExists", map);
    }

    public void deleteWishlistItem(SqlSessionTemplate session, Map<String, String> map) {
        session.delete("WishListMapper.deleteWishlistItem", map);
    }

    public void deleteByWishListIdAndMemberId(SqlSessionTemplate session, Map<String, String> map) {
        session.delete("WishListMapper.deleteByWishListIdAndMemberId", map);
    }
}
