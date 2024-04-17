package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.OrdersDAO;
import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.dto.order.OrdersDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersDAO ordersDAO;
    private final SqlSessionTemplate session;


    public Long create(OrdersCreateRequestDTO ordersCreateRequestDTO) {
        ordersDAO.create(session, ordersCreateRequestDTO);
        return ordersCreateRequestDTO.getOrderId();
    }

    public List<OrdersDTO> findAllByMemberId(Long memberId) {
        return ordersDAO.findAllByMemberId(session,memberId);
    }
}
