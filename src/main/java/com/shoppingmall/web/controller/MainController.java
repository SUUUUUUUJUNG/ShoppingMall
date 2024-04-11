package com.shoppingmall.web.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.domain.dto.goods.GoodsDTO;
import com.shoppingmall.domain.service.GoodsService;

@Controller
@Slf4j
public class MainController {

	@Autowired
	GoodsService service;

	@GetMapping("/") // 리스트 무조건 뿌리기
	public String mainView(@RequestParam(value = "gCategory", required = false) String gCategory, Model model) {
		if (gCategory == null) {
			List<GoodsDTO> list = service.findByCategory("top");
			model.addAttribute("goodsList", list);
		} else{
			List<GoodsDTO> list = service.findByCategory(gCategory);
			model.addAttribute("goodsList", list);
		}
		return "main";
	}
}
