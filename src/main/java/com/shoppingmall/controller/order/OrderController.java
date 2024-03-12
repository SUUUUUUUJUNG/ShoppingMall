package com.shoppingmall.controller.order;

import com.shoppingmall.dto.cart.CartDTO;
import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.dto.OrderDTO;
import com.shoppingmall.service.GoodsService;
import com.shoppingmall.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final GoodsService goodsService;
    private final MemberService memberService;

    @RequestMapping(value="/confirm")
    public String orderConfirm(@RequestParam("cartId") String cartId, HttpSession session, Model model) {
        CartDTO cart = goodsService.cartByNum(cartId);
        String userId = cart.getUserId();
        MemberDTO member = memberService.myPage(userId);
        model.addAttribute("mDTO", member);
        model.addAttribute("cDTO", cart);
        return "orderConfirm";
    }

    @RequestMapping(value="/done")
    public String orderDone(@RequestParam("orderNum") Integer orderNum, OrderDTO oDTO,
                            HttpSession session, Model model) {
        MemberDTO dto = (MemberDTO)session.getAttribute("login");
        oDTO.setUserId(dto.getUserId());
        goodsService.orderDone(oDTO,orderNum);//insert,delete
        model.addAttribute("oDTO", oDTO);
        return "orderDone";

    }
}
