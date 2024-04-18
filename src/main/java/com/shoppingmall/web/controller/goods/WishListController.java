package com.shoppingmall.web.controller.goods;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.goods.GoodsService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wishList")
@RequiredArgsConstructor
public class WishListController {

    private final GoodsService goodsService;
    private final WishListService wishListService;
    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<?> save(Principal principal, @RequestParam(value = "gCode") String gCode){
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        Long memberId = memberDTO.getMemberId();

        Map<String,String> map = new HashMap<>();
        map.put("memberId", String.valueOf(memberId));
        map.put("gCode",gCode);

        String message = wishListService.toggleWishlistItem(map);

        return ResponseEntity.ok().body(Map.of("message", message));
    }

    @DeleteMapping("/delete") // DELETE 메서드를 사용하는 것이 적합
    public ResponseEntity<?> deleteWishListItem(@RequestParam("wishListId") Long wishListId,HttpSession session, Map<String,String> map) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();
        map.put("memberId", String.valueOf(memberId));
        map.put("wishListId", String.valueOf(wishListId));
        wishListService.deleteWishListItem(map);
        return ResponseEntity.ok().body(Map.of("message", "Item deleted successfully"));
    }

}
