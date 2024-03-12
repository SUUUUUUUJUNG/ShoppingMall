package com.shoppingmall.controller.cart;

import com.shoppingmall.dto.CartDTO;
import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.GoodsService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final GoodsService goodsService;

    @RequestMapping("/list")
    public String cartList(RedirectAttributes attr, HttpSession session, Model model) {
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
        String userid = memberDTO.getUserid();
        List<CartDTO> list = goodsService.cartList(userid);
        model.addAttribute("cartList",list);
        return "cartList";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> cartAddV2(@RequestBody CartDTO cdto, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        String userid = memberDTO.getUserid();

        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("gCode", cdto.getgCode());
        map.put("gSize", cdto.getgSize());
        map.put("gColor", cdto.getgColor());
        //userid, gCode, gSize, gColor 세션에 저장되어있는 userid랑 db cart테이블에 저장되어있는 userid,
        //gCode, gSize, gColor가 다 똑같을 때 수량이 추가되는 로직
        cdto.setUserid(userid);
        goodsService.mergeCartItems(map, cdto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","장바구니에 추가되었습니다."));
    }
}
