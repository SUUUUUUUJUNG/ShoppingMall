package com.shoppingmall.config;

import com.shoppingmall.typeHandler.MemberStatusTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // Type aliases 패키지 설정
        sessionFactory.setTypeAliasesPackage("com.shoppingmall.domain.dto");

        // TypeHandler 등록
        sessionFactory.setTypeHandlers(new TypeHandler<?>[]{ new MemberStatusTypeHandler() });

        // XML 매퍼 파일 위치 설정
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(resources);

        return sessionFactory.getObject();
    }
}
