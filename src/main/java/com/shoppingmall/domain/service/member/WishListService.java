package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.WishListDAO;
import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListDAO wishListDAO;
    private final SqlSessionTemplate session;

    public List<WishListDTO> findByMemberId(Long memberId) {
        return wishListDAO.findByMemberId(memberId);
    }

    public String toggleWishlistItem(Map<String, String> map) {
        if(isItemWishlisted(map)){
            wishListDAO.deleteWishlistItem(session, map);
            return "찜 목록에서 삭제되었습니다.";
        } else {
            wishListDAO.insertWishlistItem(session, map);
            return "찜 목록에 추가되었습니다.";
        }
    }

    public boolean isItemWishlisted(Map<String,String> map){
        List<MemberDTO> existingItems = wishListDAO.checkWishlistItemExists(session, map);
        return !existingItems.isEmpty();
    }




    public void deleteWishListItem(Map<String, String> map) {
        wishListDAO.deleteWishlistItem(session,map);
    }

}
