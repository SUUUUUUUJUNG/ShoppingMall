package com.shoppingmall.web.controller.goods;

import com.shoppingmall.domain.dto.GoodsDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsService;
import com.shoppingmall.domain.service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

	private final GoodsService goodsService;
	private final WishListService wishListService;

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
	public String goodsRetrieve(@RequestParam("gCode")String gCode, Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
		Long memberId = memberDTO.getMemberId();
		GoodsDTO goodsDTO = goodsService.findByCode(gCode);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", String.valueOf(memberId));
		map.put("gCode",gCode);
		boolean itemWishlisted = wishListService.isItemWishlisted(map);
		model.addAttribute("itemWishlisted",itemWishlisted);
		model.addAttribute("goodsDTO", goodsDTO);


		return "goodsRetrieve";
	} 
}
