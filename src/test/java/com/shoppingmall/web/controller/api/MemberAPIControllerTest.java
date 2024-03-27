package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MemberUpdateRequestDTO requestDTO;

    private MemberDTO memberDTO;

    @BeforeEach
    void setup() {
        requestDTO = new MemberUpdateRequestDTO(
                1L, // memberId
                "original-password", // currentPassword
                "changed-password", // newPassword
                "123123", // zip_Code
                "경기도 산본", // address
                "1층" // addr_Detail
        );

        memberDTO = new MemberDTO();
    }

    @Test
    void updateMemberInfo_whenSessionNotAuthenticated_shouldReturnUnauthorized() throws Exception{
        //총 4가지 경우 실패1,2,3 그리고 성공
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("로그인이 필요한 작업입니다."));
    }

    @Test
    void updateMemberInfo_whenMemberIdMismatch_shouldReturnUnauthorized() throws Exception{
        // given : 로그인 된 아이디와 수정하려는 회원이 다른 경우
        memberDTO.setMemberId(999L);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member")
                        .sessionAttr("login", memberDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("잘못된 접근입니다."));
    }

    @Test
    void updateMemberInfo_whenPasswordMismatch_shouldReturnBadRequest() throws Exception{
        //현재 비밀번호와 입력된 번호가 다르다는 가정

        memberDTO.setMemberId(1L);
        memberDTO.setPassword("different-password");

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member")
                        .sessionAttr("login", memberDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("비밀번호를 확인해주세요."));
    }

    @Test
    void updateMemberInfo_whenAuthenticatedAndAuthorized_shouldSucceed () throws Exception {
        //인증되고 권한이 있는 상황에서 회원정보 업데이트가 성공적으로 이루어지는 상황을 가정
        memberDTO.setMemberId(1L);
        memberDTO.setPassword("original-password");

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member")
                        .sessionAttr("login", memberDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("회원정보가 수정되었습니다."));
    }
}