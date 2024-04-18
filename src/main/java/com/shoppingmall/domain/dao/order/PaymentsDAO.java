package com.shoppingmall.domain.dao.order;

import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentsDAO {

    private final SqlSessionTemplate session;

    public int create(PaymentsCreateRequestDTO requestDTO) {
        return session.insert("PaymentsMapper.create", requestDTO);
    }
}
