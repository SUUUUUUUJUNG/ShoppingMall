package com.shoppingmall.controller.goods;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.GoodsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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
}