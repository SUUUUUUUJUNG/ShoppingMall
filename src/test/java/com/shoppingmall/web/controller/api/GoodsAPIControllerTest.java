package com.shoppingmall.web.controller.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsService;
import com.shoppingmall.domain.service.MemberLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class GoodsAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GoodsService goodsService;

    @MockBean
    private MemberLoginService memberLoginService;

    private GoodsCreateRequestDTO requestDTO;

    private MemberDTO memberDTO;


    @BeforeEach
    void setUp() {
        // Set up the request DTO with sample data
        requestDTO = new GoodsCreateRequestDTO();
        requestDTO.setGName("New Product");
        requestDTO.setGPrice(999);
        requestDTO.setGContent("A description of the new product");

        // Set up the MemberDTO for the admin user
        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
        memberDTO.setRole("ROLE_ADMIN");
    }

    @Test
    void createAsAdminShouldSucceed() throws Exception {
        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(post("/api/goods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO))
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("상품이 정상적으로 등록되었습니다."));
    }

    @Test
    void createAsUserShouldFail() throws Exception {
        memberDTO.setRole("ROLE_USER");

        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(post("/api/goods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO))
                .with(user("user").roles("USER")))
                .andExpect(status().isUnauthorized());
    }
}
