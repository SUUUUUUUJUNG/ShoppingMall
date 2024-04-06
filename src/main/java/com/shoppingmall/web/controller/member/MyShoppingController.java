package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.MemberService;
import com.shoppingmall.domain.service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/myShopping")
@RequiredArgsConstructor
public class MyShoppingController {

    private final WishListService wishListService;
    private final MemberLoginService memberLoginService;

    @GetMapping
    public String findByMemberId(Principal principal, Model model){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        model.addAttribute("wishListItems", wishListService.findByMemberId(memberId));
        return "myShopping";
    }
}
