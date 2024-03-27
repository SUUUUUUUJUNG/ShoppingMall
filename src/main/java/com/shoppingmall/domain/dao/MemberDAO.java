package com.shoppingmall.domain.dao;

import java.util.List;
import java.util.Map;

import com.shoppingmall.domain.dto.WishListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.domain.dto.member.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;  // 자동 주입

	public int memberAdd(MemberDTO dto) {
		int n = session.insert("MemberMapper.memberAdd",dto);
		return n;
	}

	public MemberDTO login(Map<String, String> m) {
		return session.selectOne("MemberMapper.login",m);
	}

	public MemberDTO myPage(String userid) {
        return session.selectOne("MemberMapper.myPage",userid);
	}

	public int memberUpdate(MemberDTO dto) {
		System.out.println("MemberDAO : " + dto);
		int n = session.update("MemberMapper.memberUpdate",dto);
		return n;
	}


	public List<WishListDTO> findWishListByMemberId(Long memberId) {
		return session.selectList("WishListMapper.findWishListByMemberId",memberId);
	}
}
