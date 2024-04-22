package com.shoppingmall.web.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping
    public String myPage() {
        return "member/myPage";
    }

    @GetMapping("/deliveryAddresses")
    public String deliveryAddresses() {
        return "member/deliveryAddresses";
    }
}
