package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.OrderItemsDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemDAO {

    private final SqlSessionTemplate session;

    public int create(OrderItemsDTO orderItemsDTO) {
        return session.insert("OrderItemMapper.create", orderItemsDTO);
    }
}
