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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 15:39
 */
@Configuration
//basePackages是接口路径
@MapperScan(basePackages = "com.cgd.xxljobexecutor.dao.executor", sqlSessionFactoryRef = "PrimarySqlSessionFactory")//basePackages:接口文件的包路径
public class PrimaryDataSourceConfig {

    @Bean(name = "PrimaryDataSource")
    // 表示这个数据源是默认数据源
    @Primary//这个一定要加，如果两个数据源都没有@Primary会报错
    @ConfigurationProperties(prefix = "spring.datasource.primary")//我们配置文件中的前缀
    public DataSource getPrimaryDateSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "PrimarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("PrimaryDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        //这里需要mybatis的xml的路径
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/executor/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean("PrimarySqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("PrimarySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}