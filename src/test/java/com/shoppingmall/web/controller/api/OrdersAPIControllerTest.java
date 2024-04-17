package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrdersAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private MemberLoginService memberLoginService;

    private OrdersCreateRequestDTO createRequestDTO;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void createOrderShouldSucceed() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
        when(memberLoginService.findByPrinciple(any(Principal.class))).thenReturn(memberDTO); // 가정: 회원 ID가 1로 설정된다.
        when(ordersService.create(any(OrdersCreateRequestDTO.class))).thenReturn(1); // 성공한 경우의 반환값

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrdersCreateRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("상품이 추가되었습니다."));
    }

}