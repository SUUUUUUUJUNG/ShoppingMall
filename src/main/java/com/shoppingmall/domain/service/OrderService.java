package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.OrderDAO;
import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import com.shoppingmall.domain.dto.order.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;

    public Long create(OrderCreateRequestDTO orderCreateRequestDTO) {
        orderDAO.create(orderCreateRequestDTO);
        return orderCreateRequestDTO.getOrderId();
    }

    public List<OrderDTO> findAllByMemberId(Long memberId) {
        return orderDAO.findAllByMemberId(memberId);
    }

    public OrderDTO findById(Long orderId) {
        return orderDAO.findById(orderId);
    }
}
