package com.shoppingmall.web.controller.cart;

import com.shoppingmall.domain.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartManagementController {

    private final GoodsService goodsService;

    @RequestMapping("/update")
    public void cartUpdate(@RequestParam Map<String, String> m) {
        goodsService.cartUpdate(m);

    }

    @RequestMapping("/delete")
    public String cartDelete(int cartId) {
        goodsService.cartDel(cartId);
        return "ok";
    }

    @RequestMapping("/delete/all") // ajax 처리로 바꿔야함 지금 동작 안함.
    public String cartAllDel(@RequestParam("check") String[] array) {
        List<String> list = Arrays.asList(array);
        goodsService.cartAllDel(list);
        return "redirect:cartList";
    }
}
