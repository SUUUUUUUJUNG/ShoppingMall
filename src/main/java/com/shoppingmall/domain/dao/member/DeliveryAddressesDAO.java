package com.shoppingmall.domain.dao.member;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesCreateRequestDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesUpdateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryAddressesDAO {

    @Autowired
    SqlSessionTemplate session;

    public Integer create(DeliveryAddressesCreateRequestDTO requestDTO) {
        return session.insert("DeliveryAddressesMapper.create",requestDTO);
    }

    public List<DeliveryAddressesDTO> findAllByMemberId(Long memberId) {
        return session.selectList("DeliveryAddressesMapper.findAllByMemberId", memberId);
    }

    public DeliveryAddressesDTO findById(Long id) {
        return session.selectOne("DeliveryAddressesMapper.findById", id);
    }


    public int delete(Long id) {
        return session.delete("DeliveryAddressesMapper.delete", id);
    }

    public int update(DeliveryAddressesUpdateRequestDTO requestDTO) {
        return session.update("DeliveryAddressesMapper.update", requestDTO);
    }
}
