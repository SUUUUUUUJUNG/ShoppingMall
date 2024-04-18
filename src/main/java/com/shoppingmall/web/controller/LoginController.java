package com.shoppingmall.web.controller;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.domain.service.member.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	private final MemberService memberService;

	@GetMapping("/login")
	public String loginForm() { //void로 처리 loginForm.jsp로 이동
		return "loginForm";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("login")!=null){
			session.invalidate();
		}
		return "loginForm";
	}
}
