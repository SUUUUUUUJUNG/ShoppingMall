package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryUpdateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ProductInquiryService;
import com.shoppingmall.web.service.MemberLoginService;
import jakarta.servlet.http.HttpSession;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private MemberLoginService memberLoginService;


    private MemberDTO memberDTO;

    @BeforeEach
    void setup() {
        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);

        given(productInquiryService.create(any(ProductInquiryDTO.class))).willReturn(1);
    }

    @Test
    void createProductInquiry_ReturnsOkWithMessage_IfUserLoggedIn() throws Exception {
        ProductInquiryCreateRequestDTO requestDTO = new ProductInquiryCreateRequestDTO();
        requestDTO.setGCode("sampleCode");
        requestDTO.setInquiry_Content("상품에 대한 문의 내용입니다.");

        mockMvc.perform(post("/api/productInquiry")
                        .sessionAttr("login", memberDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("문의가 등록되었습니다."));
    }

    @Test
    void givenUserLoggedIn_whenFindAllByMemberId_thenReturnsInquiriesList() throws Exception {
        ProductInquiryCreateRequestDTO requestDTO = new ProductInquiryCreateRequestDTO();
        requestDTO.setMemberId(1L);

        mockMvc.perform(get("/api/productInquiry")
                        .sessionAttr("login", memberDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andExpect(status().isOk());
    }

    @Test
    void whenGCodeIsMissingOrInvalid_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/productInquiry")
                        .sessionAttr("login", memberDTO))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenFindAllByGCode_thenReturnInquiriesList() throws Exception {
        //Given
        String testGCode = "testGCode";
        List<ProductInquiryDTO> expectedInquiries = new ArrayList<>();
        ProductInquiryDTO inquiry1 = new ProductInquiryDTO(1L, "1문의 내용입니다.");
        ProductInquiryDTO inquiry2 = new ProductInquiryDTO(1L, "2문의 내용입니다.");
        expectedInquiries.add(inquiry1);
        expectedInquiries.add(inquiry2);

        given(productInquiryService.findAllByGCode(testGCode)).willReturn(expectedInquiries);

        //when & Then
        mockMvc.perform(get("/api/productInquiry")
                        .param("gCode", testGCode)
                        .sessionAttr("login", memberDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedInquiries.size()))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    public void updateProductInquiryTest() throws Exception {
        // 세션 모의 객체 생성
        MockHttpSession session = new MockHttpSession();

        // 요청 DTO 모의 데이터
        ProductInquiryUpdateRequestDTO requestDTO = new ProductInquiryUpdateRequestDTO();
        requestDTO.setInquiry_Id(1L);
        requestDTO.setInquiry_Content("데이트해요..!");

        // 서비스 메소드 호출 결과 모의 설정
        ProductInquiryDTO mockInquiryDTO = new ProductInquiryDTO();
        mockInquiryDTO.setInquiry_Id(1L);
        mockInquiryDTO.setMemberId(1L); // 이 부분은 실제 테스트 시나리오에 맞게 조정해야 합니다.

        given(memberLoginService.getLogin(any())).willReturn(memberDTO);
        given(productInquiryService.findById(any())).willReturn(mockInquiryDTO);
        given(productInquiryService.update(any())).willReturn(1);

        // PATCH 요청 실행 및 검증
        mockMvc.perform(patch("/api/productInquiry")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("문의가 수정되었습니다."))
                .andExpect(jsonPath("$.inquiryId").value(1));
    }


}