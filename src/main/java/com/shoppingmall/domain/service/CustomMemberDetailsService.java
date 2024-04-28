package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.enums.MemberStatus;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.security.CustomUserDetails;
import com.shoppingmall.security.exception.MemberLoginAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberLoginService memberLoginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberDTO memberDTO = memberLoginService.findByUsername(username);

        if (memberDTO.getStatus() == MemberStatus.INACTIVE) {
            throw new MemberLoginAuthenticatedException("이메일 인증이 필요합니다.");
        }

        return new CustomUserDetails(memberDTO);
    }
}
