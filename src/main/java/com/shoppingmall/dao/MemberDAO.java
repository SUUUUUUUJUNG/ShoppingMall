package com.shoppingmall.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate session;  // 자동 주입

	public int memberAdd(MemberDTO dto) {
		int n = session.insert("MemberMapper.memberAdd",dto);
		return n;
	}

	public MemberDTO login(Map<String, String> m) {
		MemberDTO dto = session.selectOne("MemberMapper.login",m);
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO dto = session.selectOne("MemberMapper.myPage",userid);
		System.out.println("dto = " + dto);
		return dto;
	}

	public int memberUpdate(MemberDTO dto) {
		int n = session.update("MemberMapper.memberUpdate",dto);
		return n;
	}

}
