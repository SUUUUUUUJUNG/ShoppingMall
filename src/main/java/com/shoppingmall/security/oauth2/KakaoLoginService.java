package com.shoppingmall.security.oauth2;


import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.MemberService;
import com.shoppingmall.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoLoginService implements SocialOauth2Service{

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Value("${jwt.expiredMs}") private String expiredMs;

    @Override
    public String login(Map<String, Object> attributes) {

        MemberDTO memberDTO = new MemberDTO();
        String username = attributes.get("id").toString();
        Optional<MemberDTO> kakaoUserOpt = Optional.ofNullable(memberService.findByUsername(username));
        String role = "ROLE_USER";
        if (kakaoUserOpt.isEmpty()) {
            memberDTO.setUsername(username);
            memberDTO.setEmail(UUID.randomUUID().toString());
            memberDTO.setRealName("test");
            memberDTO.setAddress("test");
            memberDTO.setPhoneNumber("01000000000");
            memberDTO.setAddr_Detail("test");
            memberDTO.setZip_Code("test");
            memberDTO.setRole(role);
            memberDTO.setPassword(UUID.randomUUID().toString());
            memberDTO.setStatus("ACTIVE");
            memberService.create(memberDTO);
        }

        return jwtUtil.createJwt(username, role, Long.parseLong(expiredMs));
    }
}
