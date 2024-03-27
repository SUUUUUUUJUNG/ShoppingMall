package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.MemberService;
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
        String userId=mDto.getUserId();
        MemberDTO dto = service.myPage(userId);
        session.setAttribute("login", dto);
        return "myPage";
    }
}
