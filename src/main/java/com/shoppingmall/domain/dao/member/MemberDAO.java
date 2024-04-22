package com.shoppingmall.domain.dao.member;

import com.shoppingmall.domain.dto.delivery.DeliveryAddressesDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;

	public int create(MemberDTO dto) {
		return session.insert("MemberMapper.create", dto);
	}

	public MemberDTO findByUsername(String userid) {
        return session.selectOne("MemberMapper.findByUsername",userid);
	}

	public int update(MemberUpdateRequestDTO requestDTO) {
        return session.update("MemberMapper.update",requestDTO);
	}

    public int setPrimaryAddress(DeliveryAddressesDTO deliveryAddressesDTO) {
		return session.update("MemberMapper.setPrimaryAddress",deliveryAddressesDTO);
    }
}
