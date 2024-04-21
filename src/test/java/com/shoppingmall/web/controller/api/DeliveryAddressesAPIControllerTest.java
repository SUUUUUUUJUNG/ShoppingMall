package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.member.DeliveryAddressesService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.member.MemberService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DeliveryAddressesAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryAddressesService deliveryAddressesService;

    @MockBean
    private Principal principal;

    @MockBean
    private MemberService memberService;

    private DeliveryAddressesCreateRequestDTO requestDTO;

    private DeliveryAddressesDTO deliveryDTO;

    private MemberDTO memberDTO;

    @Autowired
    private MemberLoginService memberLoginService;

    @BeforeEach
    void setUp() {
        requestDTO = new DeliveryAddressesCreateRequestDTO();
        requestDTO.setAddress("AAA");
        requestDTO.setAddr_Detail("삼원빌딩");
        requestDTO.setZip_Code("12345");
        requestDTO.setPhoneNumber("123-456-7890");
        requestDTO.setRecipient_name("홍길동");

        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
        memberDTO.setUsername("user1");

        when(principal.getName()).thenReturn("user1");  // Principal의 getName() 메서드가 "user1"을 반환하도록 설정
        when(memberLoginService.findByPrinciple(principal)).thenReturn(memberDTO);  // 적절한 사용자 ID 반환
        when(memberService.findByUsername("user1")).thenReturn(memberDTO);
    }

    @Test
    @WithMockUser(username="user1",roles = "USER")
    public void createDeliveryAddress_Success() throws Exception {

        when(deliveryAddressesService.create(any(DeliveryAddressesCreateRequestDTO.class))).thenReturn(1);

        mockMvc.perform(post("/api/delivery")
                 .principal(principal)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("주소가 추가되었습니다."));
    }
}