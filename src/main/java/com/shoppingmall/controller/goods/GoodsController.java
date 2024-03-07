package com.shoppingmall.controller.goods;

import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

	private final GoodsService service;

	@RequestMapping(value="/list")
	public ModelAndView goodsList(@RequestParam("gCategory")String gCategory) {
		if(gCategory==null) {
			gCategory="top";
		}

		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		mav.setViewName("main");

		return mav;
	}
	
	@GetMapping("/detail")
	public String goodsRetrieve(@RequestParam("gCode")String gCode, Model model) {
		GoodsDTO goodsDTO = service.goodsRetrieve(gCode);
		model.addAttribute("goodsDTO", goodsDTO);
		return "goodsRetrieve";
	} 
}
