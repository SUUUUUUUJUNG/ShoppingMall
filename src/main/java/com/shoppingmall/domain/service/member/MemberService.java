package com.shoppingmall.domain.service.member;

import com.shoppingmall.domain.dao.member.MemberDAO;
import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
	

	private  final MemberDAO dao;

	public int create(MemberDTO dto) {
        return dao.create(dto);
	}

	public MemberDTO findByUsername(String userId) {
        return dao.findByUsername(userId);
	}

	public int update(MemberUpdateRequestDTO requestDTO) {
        return dao.update(requestDTO);
	}


    public int setPrimaryAddress(DeliveryAddressesDTO deliveryAddressesDTO) {
		return dao.setPrimaryAddress(deliveryAddressesDTO);
    }
}
