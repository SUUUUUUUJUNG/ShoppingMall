package com.shoppingmall.web.controller.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import com.shoppingmall.domain.dto.goods.GoodsDTO;
import com.shoppingmall.domain.dto.goods.GoodsResponseDTO;
import com.shoppingmall.domain.dto.goods.GoodsUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.goods.GoodsService;
import com.shoppingmall.domain.service.member.MemberLoginService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private GoodsCreateRequestDTO createRequestDTO;

    private MemberDTO memberDTO;

    private GoodsDTO sampleGoodsDTO;


    @BeforeEach
    void setUp() {
        createRequestDTO = new GoodsCreateRequestDTO();
        createRequestDTO.setName("New Product");
        createRequestDTO.setPrice(999);
        createRequestDTO.setContent("A description of the new product");

        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
        memberDTO.setRole("ROLE_ADMIN");

        sampleGoodsDTO = new GoodsDTO();
        sampleGoodsDTO.setGCode("001");
        sampleGoodsDTO.setGCategory("top");
        sampleGoodsDTO.setGName("니트");
        sampleGoodsDTO.setGContent("니트 입니다.");
        sampleGoodsDTO.setGPrice(9999);
        sampleGoodsDTO.setGImage("top001.jpg");
    }

    @Test
    @WithMockUser(username = "testadmin",roles = "ADMIN")
    void createAsAdminShouldSucceed() throws Exception {
        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(post("/api/goods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("상품이 정상적으로 등록되었습니다."));
    }

    @Test
    @WithMockUser(username = "testuser",roles = "USER")
    void createAsUserShouldFail() throws Exception {
        memberDTO.setRole("ROLE_USER");

        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(post("/api/goods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test")
    void findByCodeShouldReturnGoodsInfo() throws Exception {
        when(goodsService.findByCode(eq("001"))).thenReturn(sampleGoodsDTO);
        GoodsResponseDTO responseDTO = new GoodsResponseDTO(sampleGoodsDTO);
        String expectedJson = objectMapper.writeValueAsString(responseDTO);
        mockMvc.perform(get("/api/goods")
                        .param("gCode", "001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    @WithMockUser(username = "testAdmin",roles = "ADMIN")
    void deleteGoodsAsAdminShouldSucceed() throws Exception{
        String gCode = "001";
        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(delete("/api/goods/{gCode}",gCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("상품이 삭제되었습니다."));
    }

    @Test
    @WithMockUser(username = "testUser",roles = "USER")
    void deleteGoodsAsUserShouldFail() throws Exception{
        String gCode = "001";
        memberDTO.setRole("ROLE_USER");
        when(memberLoginService.findByPrinciple(any())).thenReturn(memberDTO);

        mockMvc.perform(delete("/api/goods/{gCode}",gCode))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testAdmin",roles = "ADMIN")
    void updateGoodsAsAdminShouldSucceed() throws Exception {
        when(memberLoginService.findByPrinciple(any(Principal.class))).thenReturn(memberDTO);
        when(goodsService.update(any())).thenReturn(1);  // Assume the update is successful
        GoodsUpdateRequestDTO updateRequestDTO = new GoodsUpdateRequestDTO();

        mockMvc.perform(patch("/api/goods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("상품이 수정되었습니다."));
    }
}
