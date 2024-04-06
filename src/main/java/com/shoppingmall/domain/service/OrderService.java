package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.CartDAO;
import com.shoppingmall.domain.dao.OrderDAO;
import com.shoppingmall.domain.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private  final OrderDAO orderDAO;
    private  final CartDAO cartDAO;
    private  final SqlSessionTemplate session;

    @Transactional
    public void orderDone(OrderDTO oDTO, Integer orderNum) {
        orderDAO.create(session, oDTO);//주문 정보 저장
//        cartDAO.cartDelete(session, orderNum); //카트에서 삭제 두 처리를 tx 처리함 root-context.xml에 tx-Manager등록 필요
    }
}
