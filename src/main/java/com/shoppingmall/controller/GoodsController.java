package com.shoppingmall.controller;

import com.shoppingmall.dto.CartDTO;
import com.shoppingmall.dto.GoodsDTO;
import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.dto.OrderDTO;
import com.shoppingmall.service.GoodsService;
import com.shoppingmall.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	@GetMapping("goodsRetrieve")
	public String goodsRetrieve(@RequestParam("gCode")String gCode, Model model) {
		GoodsDTO goodsDTO = service.goodsRetrieve(gCode);
		model.addAttribute("goodsDTO", goodsDTO);
		return "goodsRetrieve";
	} 
	
	@RequestMapping(value="/loginCheck/cartAdd")
	public String cartAdd(CartDTO cdto,HttpSession session) {
		MemberDTO mdto = (MemberDTO)session.getAttribute("login");
		String userid = mdto.getUserid();
		Map<String,String> map = new HashMap<>();
		map.put("userid",userid);
		map.put("gCode", cdto.getgCode());
		map.put("gSize", cdto.getgSize());
		map.put("gColor", cdto.getgColor());
		//userid, gCode, gSize, gColor 세션에 저장되어있는 userid랑 db cart테이블에 저장되어있는 userid,
		//gCode, gSize, gColor가 다 똑같을 때 수량이 추가되는 로직
		session.setAttribute("mesg", cdto.getgCode());
		cdto.setUserid(userid);
		service.mergeCartItems(map,cdto);
//		service.cartAdd(cdto);
		return "redirect:../goodsRetrieve?gCode="+cdto.getgCode();
	}
	
	@RequestMapping(value="/cartList") // 인터셉터 통과
	public String cartList(RedirectAttributes attr, HttpSession session, Model model) {
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		String userid = member.getUserid();
		//System.out.println(userid);
		List<CartDTO> list = service.cartList(userid);
		model.addAttribute("cartList",list);// 리다이렉트시 데이터 유지
		return "cartList"; //servlet-context에 등록
		
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
	
	@RequestMapping(value="/orderConfirm")
	public String orderConfirm(@RequestParam("num") String num, HttpSession session,Model model) {
		CartDTO cart = service.cartByNum(num);
		String userid = cart.getUserid();
		MemberDTO member = mService.myPage(userid);
		model.addAttribute("mDTO", member);
		model.addAttribute("cDTO", cart);
		//System.out.println("orderConfirm : " + cart);
		//System.out.println("orderConfirm : " + member);
		return "orderConfirm";
	}
	
	@RequestMapping(value="/orderDone")
	public String orderDone(@RequestParam("orderNum") Integer orderNum, OrderDTO oDTO,
			HttpSession session, Model model) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		oDTO.setUserid(dto.getUserid());
		service.orderDone(oDTO,orderNum);//insert,delete
		model.addAttribute("oDTO", oDTO);
		return "orderDone";
		
	}

	@RequestMapping("wishList")
	@ResponseBody
	public ResponseEntity<?> wishList(HttpSession session, @RequestParam(value = "gCode") String gCode){
		System.out.println("GoodsController 찜하기 : " + gCode);
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
		Long memberId = memberDTO.getMemberId();
		Map<String,String> map = new HashMap<>();
		map.put("memberId", String.valueOf(memberId));
		map.put("gCode",gCode);
		service.toggleWishlistItem(map);
		//찜하기 버튼 클릭하면 상품이 담아지고, 원래 있던 상품이면 찜 목록에서 삭제(userid, gCode로 같은 상품이 있는지 확인)
//		MemberDTO wishdto = (MemberDTO) session.getAttribute("login");
//		String userid = wishdto.getUserid();
//		Map<String,String> map = new HashMap<>();
//		map.put("userid",userid);
//		map.put("gCode",)
		return ResponseEntity.ok("성공했습니다.");
	}

	

}


