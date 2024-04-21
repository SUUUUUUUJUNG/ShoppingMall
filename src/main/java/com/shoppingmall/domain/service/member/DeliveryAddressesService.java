package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.DeliveryAddressesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressesService {

    private final DeliveryAddressesDAO deliveryAddressesDAO;

}
