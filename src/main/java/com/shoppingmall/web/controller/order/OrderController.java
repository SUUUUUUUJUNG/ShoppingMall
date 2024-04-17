package com.shoppingmall.web.controller.order;

import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.service.CartService;
import com.shoppingmall.domain.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final MemberLoginService memberLoginService;

    @GetMapping
    public String orderForm(@RequestParam("cartId") List<String> cartIds, Model model, Principal principal) {
        List<CartListResponseDTO> list = cartIds.stream().map(cartService::findByCartId).toList();
        model.addAttribute("cartList", list);
        model.addAttribute("member", memberLoginService.findByPrinciple(principal));
        return "order/orderForm";
    }
}
