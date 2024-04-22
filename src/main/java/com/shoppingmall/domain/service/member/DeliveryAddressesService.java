package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.DeliveryAddressesDAO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryAddressesService {

    private final DeliveryAddressesDAO deliveryAddressesDAO;

    public Integer create(DeliveryAddressesCreateRequestDTO requestDTO) {
        return deliveryAddressesDAO.create(requestDTO);
    }

    public List<DeliveryAddressesDTO> findAllByMemberId(Long memberId) {
        return deliveryAddressesDAO.findAllByMemberId(memberId);
    }
}
