package com.shoppingmall.domain.dao;

import java.util.List;

import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.domain.dto.member.MemberDTO;

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

}
