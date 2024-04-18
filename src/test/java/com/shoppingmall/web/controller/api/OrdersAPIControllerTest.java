package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import com.shoppingmall.domain.dto.order.OrderDTO;
import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.OrderService;
import com.shoppingmall.domain.service.PaymentsService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private OrderService orderService;

    @MockBean
    private PaymentsService paymentsService;

    @MockBean
    private MemberLoginService memberLoginService;

    private MemberDTO memberDTO;

    @BeforeEach
    void setUp() {
        memberDTO = new MemberDTO();
        memberDTO.setMemberId(1L);
    }

    @Test
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void createOrderShouldSucceed() throws Exception {

        when(memberLoginService.findByPrinciple(any(Principal.class))).thenReturn(memberDTO); // 가정: 회원 ID가 1로 설정된다.
        when(orderService.create(any(OrderCreateRequestDTO.class))).thenReturn(1L); // 성공한 경우의 반환값
        when(paymentsService.create(any(PaymentsCreateRequestDTO.class))).thenReturn(1); // 성공한 경우의 반환값

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderCreateRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("상품이 추가되었습니다."));
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void findAllByMemberIdShouldReturnOrders() throws Exception {
        when(memberLoginService.findByPrinciple(any(Principal.class))).thenReturn(memberDTO);// 가정: 회원 ID가 1로 설정된다.
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1L);
        orderDTOS.add(orderDTO);
        when(orderService.findAllByMemberId(memberDTO.getMemberId())).thenReturn(orderDTOS); // 가정: 회원 ID가 1로 설정된다.
        mockMvc.perform(get("/api/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(orderDTOS.get(0).getOrderId()));
    }
}
