package com.shoppingmall.web.controller;

import com.shoppingmall.domain.dto.goods.GoodsResponseNewDTO;
import com.shoppingmall.domain.service.goods.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "admin-page/goods/goodsManagement";
    }

    @GetMapping("/statistics")
    public String statistics(){
        return "admin-page/statistics";
    }

    @GetMapping("/coupon")
    public String coupon(){
        return "admin-page/couponManagement";
    }

    @GetMapping("/goods/add")
    public String create(){
        return "admin-page/goods/goodsPost";
    }

    @GetMapping("/goods/edit/{gCode}")
    public String update(@PathVariable("gCode") String gCode, Model model){
        model.addAttribute("gCode", gCode);
        return "admin-page/goods/goodsUpdate";
    }
}
