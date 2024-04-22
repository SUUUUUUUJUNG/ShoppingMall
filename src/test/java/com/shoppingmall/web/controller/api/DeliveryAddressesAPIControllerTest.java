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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private MemberDTO memberDTO;

    @Autowired
    private MemberLoginService memberLoginService;

    @BeforeEach
    void setUp() {
        requestDTO = new DeliveryAddressesCreateRequestDTO();
        requestDTO.setAddress("AAA");
        requestDTO.setAddr_Detail("삼원빌딩");
        requestDTO.setZip_Code("12345");
        requestDTO.setPhoneNumber("123-4567-7890");
        requestDTO.setRecipient_name("홍길동");

        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
        memberDTO.setUsername("user1");

        when(principal.getName()).thenReturn("user1");  // Principal의 getName() 메서드가 "user1"을 반환하도록 설정
        when(memberLoginService.findByPrinciple(principal)).thenReturn(memberDTO);  // 적절한 사용자 ID 반환
        when(memberService.findByUsername("user1")).thenReturn(memberDTO);
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void createDeliveryAddress_Success() throws Exception {

        when(deliveryAddressesService.create(any(DeliveryAddressesCreateRequestDTO.class))).thenReturn(1);

        mockMvc.perform(post("/api/delivery")
                        .principal(principal)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("주소가 추가되었습니다."));
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void findAllByMemberId_Success() throws Exception {
        List<DeliveryAddressesDTO> addresses = new ArrayList<>();
        DeliveryAddressesDTO deliveryAddress1 = new DeliveryAddressesDTO();
        deliveryAddress1.setAddress("서울시 강남구");
        deliveryAddress1.setAddr_Detail("타워팰리스");
        deliveryAddress1.setZip_Code("135-080");
        deliveryAddress1.setPhoneNumber("010-1234-5678");
        deliveryAddress1.setRecipient_name("홍길동");

        addresses.add(deliveryAddress1);

        when(deliveryAddressesService.findAllByMemberId(1L)).thenReturn(addresses);

        mockMvc.perform(get("/api/delivery")
                        .principal(principal)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("서울시 강남구"))
                .andExpect(jsonPath("$[0].addr_Detail").value("타워팰리스"))
                .andExpect(jsonPath("$[0].zip_Code").value("135-080"))
                .andExpect(jsonPath("$[0].phoneNumber").value("010-1234-5678"))
                .andExpect(jsonPath("$[0].recipient_name").value("홍길동"));
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void deleteDeliveryAddress_Success() throws Exception {

        Long id = 1L;
        MemberDTO member = new MemberDTO();
        member.setMemberId(1L);
        DeliveryAddressesDTO existingAddress = new DeliveryAddressesDTO();
        existingAddress.setId(id);
        existingAddress.setMemberId(1L);

        when(memberLoginService.findByPrinciple(principal)).thenReturn(member);
        when(deliveryAddressesService.findById(id)).thenReturn(existingAddress);
        when(deliveryAddressesService.delete(id)).thenReturn(1);

        mockMvc.perform(delete("/api/delivery/{id}", id)
                        .principal(principal)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("배송지가 삭제되었습니다."));
    }
}