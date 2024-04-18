package com.shoppingmall.domain.service.order;

import com.shoppingmall.domain.dao.order.PaymentsDAO;
import com.shoppingmall.domain.dto.payment.PaymentsCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsDAO paymentsDAO;

    public int create(PaymentsCreateRequestDTO requestDTO) {
        return paymentsDAO.create(requestDTO);
    }
}
