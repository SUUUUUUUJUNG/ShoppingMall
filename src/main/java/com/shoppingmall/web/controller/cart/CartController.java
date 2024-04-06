package com.shoppingmall.web.controller.cart;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.service.GoodsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final GoodsService goodsService;

    @RequestMapping("/list")
    public String cartList(HttpSession session, Model model) {
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
        String userId = memberDTO.getUsername();
        List<CartListResponseDTO> list = goodsService.cartList(userId);
        model.addAttribute("cartList",list);
        return "cartList";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> cartAddV2(@RequestBody Map<String, String> map, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        String userId = memberDTO.getUsername();
        map.put("userId", userId);
        goodsService.mergeCartItems(map);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","장바구니에 추가되었습니다."));
    }
}
