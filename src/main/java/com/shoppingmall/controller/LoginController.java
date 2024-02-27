package com.shoppingmall.controller;

import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;

	@RequestMapping("/login")
	public String login(@RequestParam Map<String,String> m,HttpSession session, Model model) {
		//System.out.println(m);
		MemberDTO dto=service.login(m);
		//System.out.println(dto);


		if(dto!=null) {
			session.setAttribute("login", dto);
			return "redirect:/goodsList?gCategory=top";//로그인시 top카테고리를 보이도록 작성
		}else {
			model.addAttribute("mesg","아이디 또는 비번이 잘못되었습니다.");
			return "loginForm";
		}




	}

	@RequestMapping("/loginCheck/logout")
	public String logout(HttpSession session) {
		//인터셉터 통과
		//System.out.println("/loginCheck/logout   ");
		session.invalidate();
		return "redirect:../"; //loginForm.jsp

	}





}
