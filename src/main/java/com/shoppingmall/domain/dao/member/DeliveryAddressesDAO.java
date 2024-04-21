package com.shoppingmall.domain.dao.member;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryAddressesDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(DeliveryAddressesCreateRequestDTO requestDTO) {
        return session.insert("DeliveryAddressesMapper.create",requestDTO);
    }
}
