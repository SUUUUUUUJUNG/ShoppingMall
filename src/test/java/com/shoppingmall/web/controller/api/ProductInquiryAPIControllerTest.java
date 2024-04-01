package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dao.ProductInquiryDAO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryCreateRequestDTO;
import com.shoppingmall.domain.dto.ProductInquiry.ProductInquiryDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void givenUserLoggedIn_whenFindAllByMemberId_thenReturnsInquiriesList() throws Exception{
        ProductInquiryCreateRequestDTO requestDTO = new ProductInquiryCreateRequestDTO();
        requestDTO.setMemberId(1L);

        mockMvc.perform(get("/api/productInquiry")
                .sessionAttr("login",memberDTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isOk());
    }

    @Test
    void whenGCodeIsMissingOrInvalid_thenReturnsBadRequest() throws Exception{
        mockMvc.perform(get("/api/productInquiry")
                .sessionAttr("login",memberDTO))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenFindAllByGCode_thenReturnInquiriesList() throws  Exception{
        //Given
        String testGCode = "testGCode";
        List<ProductInquiryDTO> expectedInquiries = new ArrayList<>();
        ProductInquiryDTO inquiry1 = new ProductInquiryDTO(1L,"1문의 내용입니다.");
        ProductInquiryDTO inquiry2 = new ProductInquiryDTO(1L, "2문의 내용입니다.");
        expectedInquiries.add(inquiry1);
        expectedInquiries.add(inquiry2);

        given(productInquiryService.findAllByGCode(testGCode)).willReturn(expectedInquiries);

        //when & Then
        mockMvc.perform(get("/api/productInquiry")
                .param("gCode",testGCode)
                .sessionAttr("login",memberDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedInquiries.size()))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    void updateProductInquiry_ReturnsOkWithUpdatedInquiry() throws Exception {

        Long inquiryId = 1L;
        ProductInquiryCreateRequestDTO requestDTO = new ProductInquiryCreateRequestDTO();


        MemberDTO mockMember = new MemberDTO();
        mockMember.setMemberId(1L);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("login", mockMember);

        List<ProductInquiryDTO> mockResponse = new ArrayList<>();

        given(memberLoginService.getLogin(any(HttpSession.class))).willReturn(mockMember);
        given(productInquiryService.updateProductInquiry(eq(inquiryId))).willReturn(mockResponse);

        mockMvc.perform(post("/api/productInquiry/update/{inquiry_Id}", inquiryId)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}