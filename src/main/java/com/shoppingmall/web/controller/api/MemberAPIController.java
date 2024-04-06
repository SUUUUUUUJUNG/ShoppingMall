package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import com.shoppingmall.domain.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberAPIController {

    private final MemberService memberService;

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody @Validated MemberUpdateRequestDTO requestDTO, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");

        if (memberDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }

        if (!Objects.equals(memberDTO.getMemberId(), requestDTO.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 접근입니다.");
        }

        if (!Objects.equals(memberDTO.getPassword(), requestDTO.getCurrentPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 확인해주세요.");
        }

        memberDTO.update(requestDTO);
        memberService.update(requestDTO);

        return ResponseEntity.ok(Map.of("message", "회원정보가 수정되었습니다."));
    }
}
