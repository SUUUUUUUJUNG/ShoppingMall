package com.shoppingmall.web.controller.cart;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.service.CartService;
import com.shoppingmall.domain.service.MemberLoginService;
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

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final MemberLoginService memberLoginService;

    @RequestMapping("/list")
    public String cartList(Principal principal, Model model) {
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        String username = memberDTO.getUsername();
        List<CartListResponseDTO> list = cartService.findByUsername(username);
        model.addAttribute("cartList",list);
        return "cartList";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> cartAdd(@RequestBody Map<String, String> map, Principal principal) {
        String userId = memberLoginService.findByPrinciple(principal).getUsername();
        map.put("userId", userId);
        cartService.mergeCartItems(map);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","장바구니에 추가되었습니다."));
    }
}
