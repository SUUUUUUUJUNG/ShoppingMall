package com.shoppingmall.controller.member;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService service;

    @GetMapping
    public String mypage(HttpSession session ) {
        MemberDTO mDto =(MemberDTO)session.getAttribute("login");
        String userid=mDto.getUserid();
        MemberDTO dto = service.myPage(userid);
        session.setAttribute("login", dto);
        return "myPage";
    }

    @PostMapping("/update")
    public String memberUpdate(MemberDTO dto, HttpSession session){
        System.out.println("MemberController " + dto);
        int n = service.memberUpdate(dto);
        session.setAttribute("login", dto);
        session.setAttribute("mesg", "회원정보가 수정되었습니다.");
        return "redirect:/myPage";
        //1. myPage.jsp바로 응답처리
        //2. /loginCheck/myPage로 재요청 -> servlet-context/myPage -> myPage.jsp
    }
}
