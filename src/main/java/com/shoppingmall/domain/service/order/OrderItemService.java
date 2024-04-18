package com.shoppingmall.domain.service.order;

import com.shoppingmall.domain.dao.order.OrderItemDAO;
import com.shoppingmall.domain.dto.OrderItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public int create(OrderItemsDTO orderItemsDTO) {
        return orderItemDAO.create(orderItemsDTO);
    }

    public List<OrderItemsDTO> findByOrderId(Long orderId) {
        return orderItemDAO.findByOrderId(orderId);
    }
}
