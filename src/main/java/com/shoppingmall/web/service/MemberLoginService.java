package com.shoppingmall.web.service;

import com.shoppingmall.domain.dto.member.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    public MemberDTO getLogin(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("login");
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "로그인이 필요한 서비스입니다.");
        }
        return member;
    }
}
