package com.shoppingmall.web.controller.goods;

import com.shoppingmall.domain.dto.goods.GoodsDTO;
import com.shoppingmall.domain.service.goods.GoodsService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

	private final GoodsService goodsService;
	private final WishListService wishListService;
	private final MemberLoginService memberLoginService;

	@RequestMapping(value="/list")
	public ModelAndView goodsList(@RequestParam("gCategory")String gCategory) {
		if(gCategory==null) {
			gCategory="top";
		}

		List<GoodsDTO> list = goodsService.findByCategory(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		mav.setViewName("main");

		return mav;
	}
	
	@GetMapping("/detail")
	public String goodsRetrieve(@RequestParam("gCode")String gCode, Model model, Principal principal) {
		boolean itemWishlisted = false;
		if (principal != null) {
			Map<String, String> map = new HashMap<>();
			map.put("memberId", String.valueOf(memberLoginService.findByPrinciple(principal).getMemberId()));
			map.put("gCode",gCode);
			itemWishlisted = wishListService.isItemWishlisted(map);
		}

		GoodsDTO goodsDTO = goodsService.findByCode(gCode);
		model.addAttribute("itemWishlisted",itemWishlisted);
		model.addAttribute("goodsDTO", goodsDTO);

		return "goodsRetrieve";
	}
}
