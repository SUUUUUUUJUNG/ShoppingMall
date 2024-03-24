package com.shoppingmall.web.controller.member;

import com.shoppingmall.domain.dto.MemberDTO;
import com.shoppingmall.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberRegisterDuplicateController {

    private final MemberService memberService;

    @PostMapping("/idDuplicateCheck")
    public ResponseEntity<?> idDuplicateCheck(@RequestParam("userId") String userId) {
        System.out.println("userId = " + userId);
        MemberDTO memberDTO = memberService.myPage(userId);
        System.out.println("memberDTO = " + memberDTO);
        if(memberDTO==null) {
            return ResponseEntity.ok(Map.of("message","사용 가능한 아이디입니다.","valid",true));
        }else {
            return ResponseEntity.ok(Map.of("message","중복된 아이디 입니다.","valid",false));
        }
    }
}
