package com.shoppingmall.web.controller.goods;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wishList")
@RequiredArgsConstructor
public class WishListController {

    private final GoodsService goodsService;

    @PostMapping
    public ResponseEntity<?> save(HttpSession session, @RequestParam(value = "gCode") String gCode){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();

        Map<String,String> map = new HashMap<>();
        map.put("memberId", String.valueOf(memberId));
        map.put("gCode",gCode);

        String message = goodsService.toggleWishlistItem(map);

        return ResponseEntity.ok().body(Map.of("message", message));
    }

    @DeleteMapping("/delete") // DELETE 메서드를 사용하는 것이 적합
    public ResponseEntity<?> deleteWishListItem(@RequestParam("wishListId") Long wishListId,HttpSession session, Map<String,String> map) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();
        map.put("memberId", String.valueOf(memberId));
        map.put("wishListId", String.valueOf(wishListId));
        goodsService.deleteWishListItem(map);
        return ResponseEntity.ok().body(Map.of("message", "Item deleted successfully"));
    }

}
