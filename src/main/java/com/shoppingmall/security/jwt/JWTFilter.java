package com.shoppingmall.security.jwt;


import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        // 쿠키에서 JWT 토큰 추출
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("AuthToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsername(token);
            String role = jwtUtil.getRole(token);

            MemberDTO user = new MemberDTO();
            user.setUsername(username);
            user.setPassword("temppassword"); // 비밀번호는 사용되지 않으므로 임시 값 설정
            user.setRole(role);

            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } /*else {
            log.info("토큰이 유효하지 않거나, 유효 기간이 지났습니다.");
        }*/

        filterChain.doFilter(request, response);
    }
}
