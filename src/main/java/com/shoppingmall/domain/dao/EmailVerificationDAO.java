package com.shoppingmall.domain.dao;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationDAO {

    private final SqlSessionTemplate session;

}
