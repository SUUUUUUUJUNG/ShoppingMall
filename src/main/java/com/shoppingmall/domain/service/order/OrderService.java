package com.shoppingmall.domain.service.order;

import com.shoppingmall.domain.dao.order.OrderDAO;
import com.shoppingmall.domain.dto.ChartDateDTO;
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

    public List<ChartDateDTO> selectSalesPast30Days() {
        return orderDAO.selectSalesPast30Days();
    }

    public List<ChartDateDTO> selectMonthlySalesPastYear() {
        return orderDAO.selectMonthlySalesPastYear();
    }

    public List<ChartDateDTO> selectYearlySalesPastThreeYears() {
        return orderDAO.selectYearlySalesPastThreeYears();
    }
}
