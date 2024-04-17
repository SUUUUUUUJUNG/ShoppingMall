package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.order.OrdersCreateRequestDTO;
import com.shoppingmall.domain.dto.order.OrdersDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDAO {
    public void create(SqlSessionTemplate session, OrdersCreateRequestDTO ordersCreateRequestDTO) {
       session.insert("OrdersMapper.create",ordersCreateRequestDTO);
    }

    public List<OrdersDTO> findAllByMemberId(SqlSessionTemplate session, Long memberId) {
        return session.selectList("OrdersMapper.findAllByMemberId",memberId);
    }
}
