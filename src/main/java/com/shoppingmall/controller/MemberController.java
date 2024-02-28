package com.shoppingmall.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
//	//UI 처리하는 주소는 servlet-context.xml에서 처리
//	@RequestMapping("/memberAdd")
//	public String memberAdd(MemberDTO dto) {
//		return "main";
//	}
//	
	@GetMapping ("/loginForm")
	public void loginForm() { //void로 처리 loginForm.jsp로 이동

	}

	@GetMapping("/memberForm")
	public String memberForm(){
		return "memberForm";
	}
	
	@PostMapping ("/memberAdd")
	public String memberAdd(MemberDTO dto,Model model) {
		int n = service.memberAdd(dto);
		model.addAttribute("success","회원가입성공"); // request객체에 담겨있는거 
		//System.out.println("insert 개수 : " + n);
		//System.out.println("MemberController : " +dto);
		return "main";
	}	
	
	@GetMapping ("/myPage")
	public String mypage(HttpSession session ) {
		MemberDTO mDto =(MemberDTO)session.getAttribute("login");
		String userid=mDto.getUserid();
		MemberDTO dto = service.myPage(userid);
		session.setAttribute("login", dto);
		//System.out.println(dto);
		return "myPage";	
	}
	
	@RequestMapping(value="/idDuplicateCheck", produces="text/plain;charset=UTF-8" )
	@ResponseBody
	public String idDuplicateCheck(@RequestParam("userid") String userid) {
		//System.out.println(userid);
		MemberDTO dto = service.myPage(userid);
		String mesg = "";
		if(dto==null) {
			mesg="아이디 사용가능";
		}else {
			mesg="사용 불가능";
		}
		//System.out.println(mesg);
		return mesg;
	}
	
	@RequestMapping(value="/loginCheck/memberUpdate",method = RequestMethod.POST)
	public String memberUpdate(MemberDTO dto,HttpSession session){
		//System.out.println("update"+dto);
		int n = service.memberUpdate(dto);
		session.setAttribute("login", dto);
		session.setAttribute("mesg", "회원정보가 수정되었습니다.");
		return "redirect:../loginCheck/myPage";
		//1. myPage.jsp바로 응답처리
		//2. /loginCheck/myPage로 재요청 -> servlet-context/myPage -> myPage.jsp
	}

}
