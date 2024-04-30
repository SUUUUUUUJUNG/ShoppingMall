package com.shoppingmall.security.oauth2;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.enums.MemberStatus;
import com.shoppingmall.domain.service.member.MemberService;
import com.shoppingmall.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverLoginService implements SocialOauth2Service{

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Value("${jwt.expiredMs}") private String expiredMs;

    @Override
    public String login(Map<String, Object> attributes) {

        // 메시지 상태 확인 후, 성공적으로 정보를 가져왔는지 검증
        if (!"success".equals(attributes.get("message").toString())) {
            throw new OAuth2AuthenticationException("OAuth2 공급자로부터 사용자 정보를 성공적으로 가져오지 못했습니다.");
        }
        // attributes에서 response를 추출하여 사용자 정보 설정
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String username = response.get("id").toString();
        Optional<MemberDTO> userOpt = Optional.ofNullable(memberService.findByUsername(username));
        String role = "USER";
        MemberDTO memberDTO = new MemberDTO();
        if (userOpt.isEmpty()) {
            memberDTO.setUsername(response.get("id").toString());
            memberDTO.setEmail(response.get("email").toString());
            memberDTO.setRealName("test");
            memberDTO.setAddress("test");
            memberDTO.setPhoneNumber("01000000000");
            memberDTO.setAddr_Detail("test");
            memberDTO.setZip_Code("test");
            memberDTO.setRole(role);
            memberDTO.setPassword(UUID.randomUUID().toString());
            memberDTO.setStatus(MemberStatus.ACTIVE);
            memberService.create(memberDTO);
        } else {
            role = userOpt.get().getRole();
        }
        return jwtUtil.createJwt(response.get("id").toString(), role, Long.parseLong(expiredMs));
    }
}
