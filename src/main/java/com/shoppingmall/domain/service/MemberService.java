package com.shoppingmall.domain.service;

import java.util.List;
import java.util.Map;

import com.shoppingmall.domain.dto.WishListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.domain.dao.MemberDAO;
import com.shoppingmall.domain.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;

	public int memberAdd(MemberDTO dto) {
		int n = dao.memberAdd(dto);
		return n;
	}

	public MemberDTO login(Map<String, String> m) {
        return dao.login(m);
	}

	public MemberDTO myPage(String userId) {
        return dao.myPage(userId);
	}

	public int memberUpdate(MemberDTO dto) {
		System.out.println("MemberService : " + dto);
		int n= dao.memberUpdate(dto);
		return n;
		
	}


	public List<WishListDTO> findWishListByMemberId(Long memberId) {
		return dao.findWishListByMemberId(memberId);
	}
}
