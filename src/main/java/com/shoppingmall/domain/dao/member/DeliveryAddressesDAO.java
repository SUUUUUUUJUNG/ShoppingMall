package com.shoppingmall.domain.dao.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryAddressesDAO {

    @Autowired
    SqlSessionTemplate session;

}
