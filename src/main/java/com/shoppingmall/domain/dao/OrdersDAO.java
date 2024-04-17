package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDAO {
    public void create(SqlSessionTemplate session, OrdersCreateRequestDTO ordersCreateRequestDTO) {
        session.insert("OrdersMapper.create",ordersCreateRequestDTO);
    }
}
