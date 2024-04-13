package com.shoppingmall.web.controller;

import com.shoppingmall.domain.dto.goods.GoodsResponseNewDTO;
import com.shoppingmall.domain.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GoodsService goodsService;

    @GetMapping
    public String adminHome(){
        return "admin-page/admin";
    }

    @GetMapping("/goods")
    public String goods(Model model){
        List<GoodsResponseNewDTO> list = goodsService.findAll();
        model.addAttribute("list", list);
        return "admin-page/goodsManagement";
    }

    @GetMapping("/statistics")
    public String statistics(){
        return "admin-page/statistics";
    }

    @GetMapping("/coupon")
    public String coupon(){
        return "admin-page/couponManagement";
    }
}
