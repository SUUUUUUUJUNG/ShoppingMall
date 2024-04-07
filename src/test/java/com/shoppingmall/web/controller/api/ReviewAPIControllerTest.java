package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.ReviewDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ReviewService;
import com.shoppingmall.web.service.MemberLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReviewAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private MemberLoginService memberLoginService;

    @MockBean
    private ReviewDTO reviewDTO;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("login", new MemberDTO()); // 세션에 로그인 정보 설정
    }

    @Test
    void createReview_Success() throws Exception {
        // given
        ReviewDTO reviewDTO = new ReviewDTO(); // 필요한 경우 reviewDTO의 상세 정보 설정
        MemberDTO login = new MemberDTO(); // 로그인한 사용자의 정보 설정
        login.setMemberId(1L); // 예시용 멤버 ID 설정

        given(memberLoginService.getLogin(session)).willReturn(login);
        given(reviewService.create(reviewDTO)).willReturn(null); // create 메소드의 반환 타입에 맞게 수정

        // when & then
        mockMvc.perform(post("/api/review")
                        .session(session) // 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("리뷰가 등록되었습니다."));
    }


}