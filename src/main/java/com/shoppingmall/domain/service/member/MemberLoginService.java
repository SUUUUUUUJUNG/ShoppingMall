package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberService memberService;

    public MemberDTO findByPrinciple(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다.");
        }
        return memberService.findByUsername(principal.getName());
    }

    public MemberDTO findByUsername(String username) {
        return memberService.findByUsername(username);
    }
}
