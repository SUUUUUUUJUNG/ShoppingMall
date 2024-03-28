package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ProductInquiryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductInquiryAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductInquiryService productInquiryService;


    private MemberDTO memberDTO;

    @BeforeEach
    void setup() {
        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);

        given(productInquiryService.create(any(ProductInquiryDTO.class))).willReturn(1);
    }

    @Test
    void createProductInquiry_ReturnsOkWithMessage_IfUserLoggedIn() throws Exception {
        // 상품 문의 생성 요청 데이터를 준비
        ProductInquiryCreateRequestDTO requestDTO = new ProductInquiryCreateRequestDTO();
        requestDTO.setGCode("sampleCode");
        requestDTO.setInquiry_Content("상품에 대한 문의 내용입니다.");

        // API 요청을 실행하고 응답을 검증
        mockMvc.perform(post("/api/productInquiry")
                                .sessionAttr("login", memberDTO)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andExpect(status().isOk()) // HTTP 200 상태 코드를 기대함
                .andExpect(jsonPath("$.message").value("문의가 등록되었습니다.")); // 응답 본문에 특정 메시지가 포함되어 있는지 검증
    }
}