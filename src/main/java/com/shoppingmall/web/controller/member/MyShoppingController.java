package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/myShopping")
@RequiredArgsConstructor
public class MyShoppingController {

    private final WishListService wishListService;
    private final MemberLoginService memberLoginService;

    @GetMapping
    public String findByMemberId(Principal principal, Model model){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
//        Long memberId = memberLoginService.getLogin(session).getMemberId();
        model.addAttribute("wishListItems", wishListService.findByMemberId(memberId));
        return "member/wishList";
    }
}
