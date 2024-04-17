package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.OrdersDAO;
import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersDAO ordersDAO;
    private final SqlSessionTemplate session;


    public void create(OrdersCreateRequestDTO ordersCreateRequestDTO) {
        ordersDAO.create(session, ordersCreateRequestDTO);
    }
}
