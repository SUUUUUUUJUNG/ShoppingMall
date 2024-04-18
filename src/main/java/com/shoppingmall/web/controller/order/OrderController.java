package com.shoppingmall.web.controller.order;

import com.shoppingmall.domain.dto.OrderItemsDTO;
import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import com.shoppingmall.domain.dto.order.OrderDTO;
import com.shoppingmall.domain.service.member.CartService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.order.OrderItemService;
import com.shoppingmall.domain.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final MemberLoginService memberLoginService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    public String orderForm(@RequestParam("cartId") List<Long> cartIds, Model model, Principal principal) {
        List<CartListResponseDTO> list = cartIds.stream().map(cartService::findByCartId).toList();
        model.addAttribute("cartList", list);
        model.addAttribute("member", memberLoginService.findByPrinciple(principal));
        model.addAttribute("cartIds",cartIds);
        return "order/orderForm";
    }

    @GetMapping("/{orderId}")
    public String orderInfo(@PathVariable("orderId") Long orderId, Model model, Principal principal) {
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        OrderDTO orderDTO = orderService.findById(orderId);
        if (!memberId.equals(orderDTO.getMemberId())) {
            return "redirect:/login";
        }
        List<OrderItemsDTO> orderItemsDTOS = orderItemService.findByOrderId(orderId);

        model.addAttribute("orderItems", orderItemsDTOS);
        model.addAttribute("orderDTO", orderDTO);
        return "order/orderInfo";
    }
}
