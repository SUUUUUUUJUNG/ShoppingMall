package com.shoppingmall.domain.service;

import java.util.List;

import com.shoppingmall.domain.dto.WishListDTO;
import com.shoppingmall.domain.dto.member.MemberUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.domain.dao.MemberDAO;
import com.shoppingmall.domain.dto.member.MemberDTO;

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


}
