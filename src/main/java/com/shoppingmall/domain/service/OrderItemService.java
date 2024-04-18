package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.OrderItemDAO;
import com.shoppingmall.domain.dto.OrderItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public int create(OrderItemsDTO orderItemsDTO) {
        return orderItemDAO.create(orderItemsDTO);
    }
}
