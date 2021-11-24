package com.cgd.xxljobexecutor.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 15:41
 */
@Configuration
@MapperScan(basePackages = "com.cgd.xxljobexecutor.dao.halo", sqlSessionFactoryRef = "SecondarySqlSessionFactory")
public class SecondaryDataSourceConfig {

    @Bean(name = "SecondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource getSecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SecondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("SecondaryDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/halo/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean("SecondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("SecondarySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}