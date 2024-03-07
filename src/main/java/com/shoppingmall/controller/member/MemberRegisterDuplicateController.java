package com.shoppingmall.controller.member;

import com.shoppingmall.dto.MemberDTO;
import com.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberRegisterDuplicateController {

    private final MemberService memberService;

    @RequestMapping(value="/idDuplicateCheck", produces="text/plain;charset=UTF-8" )
    public String idDuplicateCheck(@RequestParam("userid") String userid) {
        MemberDTO memberDTO = memberService.myPage(userid);
        String mesg = "";
        if(memberDTO==null) {
            mesg="아이디 사용가능";
        }else {
            mesg="사용 불가능";
        }
        return mesg;
    }
}
