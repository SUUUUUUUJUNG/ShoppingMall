package com.shoppingmall.controller.goods;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.GoodsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wishList")
@RequiredArgsConstructor
public class WishListController {

    private final GoodsService goodsService;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> wishList(HttpSession session, @RequestParam(value = "gCode") String gCode){
        System.out.println("GoodsController 찜하기 : " + gCode);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();
        Map<String,String> map = new HashMap<>();
        map.put("memberId", String.valueOf(memberId));
        map.put("gCode",gCode);
        goodsService.toggleWishlistItem(map);
        //찜하기 버튼 클릭하면 상품이 담아지고, 원래 있던 상품이면 찜 목록에서 삭제(userid, gCode로 같은 상품이 있는지 확인)
        return ResponseEntity.ok("성공했습니다.");
    }
}
