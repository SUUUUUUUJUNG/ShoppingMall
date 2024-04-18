package com.shoppingmall.domain.dao.order;

import com.shoppingmall.domain.dto.OrderItemsDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemDAO {

    private final SqlSessionTemplate session;

    public int create(OrderItemsDTO orderItemsDTO) {
        return session.insert("OrderItemMapper.create", orderItemsDTO);
    }

    public List<OrderItemsDTO> findByOrderId(Long orderId) {
        return session.selectList("OrderItemMapper.findByOrderId", orderId);
    }
}
