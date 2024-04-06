package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.OrderDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

    public void create(SqlSessionTemplate session, OrderDTO oDTO) {
        session.insert("OrderMapper.create",oDTO);
    }


}
