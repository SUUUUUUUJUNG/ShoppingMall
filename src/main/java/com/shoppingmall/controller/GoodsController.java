package com.shoppingmall.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingmall.dto.CartDTO;
import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.dto.OrderDTO;
import com.shoppingmall.service.GoodsService;
import com.shoppingmall.service.MemberService;
@Controller
public class GoodsController {
	
	@Autowired
	GoodsService service;
	
	@Autowired
	MemberService mService;
	
	@RequestMapping(value="/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory")String gCategory) {
		if(gCategory==null) {
			gCategory="top";
		}
		

		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		mav.setViewName("main");
		//ModelAndView main.jsp goodsList 전달
	
		return mav;
		
	}
	
	@RequestMapping(value="goodsRetrieve")
	public GoodsDTO goodsRetrieve(@RequestParam("gCode")String gCode) {
		GoodsDTO dto = service.goodsRetrieve(gCode);
		return dto;
	} 
	
	@RequestMapping(value="/loginCheck/cartAdd")
	public String cartAdd(CartDTO cdto,HttpSession session) {
		MemberDTO mdto = (MemberDTO)session.getAttribute("login");
		String userid = mdto.getUserid();
		cdto.setUserid(userid);
		session.setAttribute("mesg", cdto.getgCode());
		service.cartAdd(cdto);
		return "redirect:../goodsRetrieve?gCode="+cdto.getgCode();
	}
	
	@RequestMapping(value="/loginCheck/cartList") // 인터셉터 통과
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		String userid = member.getUserid();
		//System.out.println(userid);
		List<CartDTO> list = service.cartList(userid);
		//System.out.println(list);
		attr.addFlashAttribute("cartList",list);// 리다이렉트시 데이터 유지
		return "redirect:../cartList"; //servlet-context에 등록
		
	}	
	
	@RequestMapping(value="/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String,String> m) {
		//System.out.println("GoodsController cartUpdate" + m);
		service.cartUpdate(m);
	
	}
	
	@RequestMapping(value="/loginCheck/cartDelete")
	@ResponseBody
	public String cartDelete(int num) {
		//System.out.println("num 값 : " +num);
		service.cartDel(num);
		return "ok";
	}
	
	@RequestMapping(value="/loginCheck/delAllCart")
	public String cartAllDel(@RequestParam("check") String[] array) {
		List<String> list = Arrays.asList(array);
		service.cartAllDel(list);
		return "redirect:cartList";
	}
	
	@RequestMapping(value="/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") String num, HttpSession session, RedirectAttributes xxx) {
		CartDTO cart = service.cartByNum(num);
		String userid = cart.getUserid();
		MemberDTO member = mService.myPage(userid);
		xxx.addFlashAttribute("mDTO", member);
		xxx.addFlashAttribute("cDTO", cart);
		//System.out.println("orderConfirm : " + cart);
		//System.out.println("orderConfirm : " + member);
		return "redirect:../orderConfirm";
	}
	
	@RequestMapping(value="/loginCheck/orderDone")
	public String orderDone(@RequestParam("orderNum") Integer orderNum, OrderDTO oDTO,
			HttpSession session, RedirectAttributes xxx) {
//		System.out.println(num);
//		System.out.println(oDTO);
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		oDTO.setUserid(dto.getUserid());
		service.orderDone(oDTO,orderNum);//insert,delete
		xxx.addFlashAttribute("oDTO", oDTO);
		return "redirect:../orderDone";
		
	}
	

}


