package com.shoppingmall.controller.order;

import com.shoppingmall.dto.CartDTO;
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
    public String orderConfirm(@RequestParam("num") String num, HttpSession session, Model model) {
        CartDTO cart = goodsService.cartByNum(num);
        String userid = cart.getUserid();
        MemberDTO member = memberService.myPage(userid);
        model.addAttribute("mDTO", member);
        model.addAttribute("cDTO", cart);
        return "orderConfirm";
    }

    @RequestMapping(value="/done")
    public String orderDone(@RequestParam("orderNum") Integer orderNum, OrderDTO oDTO,
                            HttpSession session, Model model) {
        MemberDTO dto = (MemberDTO)session.getAttribute("login");
        oDTO.setUserid(dto.getUserid());
        goodsService.orderDone(oDTO,orderNum);//insert,delete
        model.addAttribute("oDTO", oDTO);
        return "orderDone";

    }
}
