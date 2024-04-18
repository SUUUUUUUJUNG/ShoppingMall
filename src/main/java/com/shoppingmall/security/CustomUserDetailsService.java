package com.shoppingmall.security;

import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO MemberDTO = memberService.findByUsername(username);
        if (MemberDTO == null) {
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }
        return new CustomUserDetails(MemberDTO);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}