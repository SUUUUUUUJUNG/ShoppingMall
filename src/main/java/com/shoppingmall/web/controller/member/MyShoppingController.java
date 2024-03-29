package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/myShopping")
@RequiredArgsConstructor
public class MyShoppingController {

    private final MemberService service;

    @GetMapping
    public String findWishListByMemberId(HttpSession session,Model model){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
        Long memberId = memberDTO.getMemberId();
        List<WishListDTO> wishListItems = service.findWishListByMemberId(memberId);
        model.addAttribute("wishListItems",wishListItems);
        return "myShopping";
    }
}
