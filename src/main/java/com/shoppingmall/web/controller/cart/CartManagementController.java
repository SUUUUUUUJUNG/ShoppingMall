package com.shoppingmall.web.controller.cart;

import com.shoppingmall.domain.service.CartService;
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

    private final CartService cartService;

    @RequestMapping("/update")
    public void cartUpdate(@RequestParam Map<String, String> m) {
        cartService.update(m);

    }

    @RequestMapping("/delete")
    public String cartDelete(int cartId) {
        cartService.delete(cartId);
        return "ok";
    }

    @RequestMapping("/delete/all") // ajax 처리로 바꿔야함 지금 동작 안함.
    public String cartAllDel(@RequestParam("check") String[] array) {
        List<String> list = Arrays.asList(array);
        cartService.deleteAll(list);
        return "redirect:cartList";
    }
}
