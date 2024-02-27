package com.shoppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.service.GoodsService;

@Controller
public class MainController {

	@Autowired
	GoodsService service;

	@GetMapping("/") // 리스트 무조건 뿌리기
	public String mainView(Model model) {
		System.out.println("model = " + model);
		List<GoodsDTO> list = service.goodsList("top");
		System.out.println("list = " + list);
		model.addAttribute("goodsList", list);
		return "main";
	}
//	@RequestMapping("/") // 리스트 무조건 뿌리기
//	public ModelAndView main() {
//		ModelAndView mav = new ModelAndView();
//		List<GoodsDTO> list = service.goodsList("top");
//		System.out.println("list = " + list);
//		mav.addObject("goodsList", list);
//		mav.setViewName("main");
//		return mav;
//	}




}
