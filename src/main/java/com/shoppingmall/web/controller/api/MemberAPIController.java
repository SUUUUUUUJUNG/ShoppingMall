package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberAPIController {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody @Validated MemberUpdateRequestDTO requestDTO, Principal principal) {
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);

        if (memberDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }

        if (!Objects.equals(memberDTO.getMemberId(), requestDTO.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 접근입니다.");
        }

        if (!passwordEncoder.matches(requestDTO.getCurrentPassword(), memberDTO.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 확인해주세요.");
        }
        requestDTO.setNewPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
        memberDTO.update(requestDTO);
        memberService.update(requestDTO);

        return ResponseEntity.ok(Map.of("message", "회원정보가 수정되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        MemberUpdateRequestDTO memberDTO = new MemberUpdateRequestDTO();
        memberDTO.setMemberId(53L);
        memberDTO.setNewPassword(passwordEncoder.encode("aaaa1111"));
        memberDTO.setAddress("1");
        memberDTO.setAddr_Detail("1");
        memberDTO.setZip_Code("1");
        memberService.update(memberDTO);
        return null;
    }
}
