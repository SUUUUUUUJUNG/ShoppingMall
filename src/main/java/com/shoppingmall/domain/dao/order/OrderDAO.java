package com.shoppingmall.domain.dao.order;

import com.shoppingmall.domain.dto.ChartDateDTO;
import com.shoppingmall.domain.dto.order.OrderCreateRequestDTO;
import com.shoppingmall.domain.dto.order.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {

    private final SqlSessionTemplate session;

    public void create(OrderCreateRequestDTO orderCreateRequestDTO) {
        session.insert("OrderMapper.create", orderCreateRequestDTO);
    }

    public List<OrderDTO> findAllByMemberId(Long memberId) {
        return session.selectList("OrderMapper.findAllByMemberId", memberId);
    }

    public OrderDTO findById(Long orderId) {
        return session.selectOne("OrderMapper.findById", orderId);
    }

    public List<ChartDateDTO> selectSalesPast30Days() {
        return session.selectList("OrderMapper.selectSalesPast30Days");
    }

    public List<ChartDateDTO> selectMonthlySalesPastYear() {
        return session.selectList("OrderMapper.selectMonthlySalesPastYear");
    }

    public List<ChartDateDTO> selectYearlySalesPastThreeYears() {
        return session.selectList("OrderMapper.selectYearlySalesPastThreeYears");
    }
}
