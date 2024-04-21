package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.DeliveryAddressesDAO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressesService {

    private final DeliveryAddressesDAO deliveryAddressesDAO;

    public Integer create(DeliveryAddressesCreateRequestDTO requestDTO) {
        return deliveryAddressesDAO.create(requestDTO);
    }
}
