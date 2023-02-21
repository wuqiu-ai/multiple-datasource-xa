package com.fly.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author HelloWoodes
 */
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@MapperScan(basePackages = "com.fly.dao.product", sqlSessionTemplateRef = "storageSqlSessionTemplate")
public class StorageDataSourceConfig {


    @Bean("originStorage")
    @ConfigurationProperties(prefix = "spring.datasource.storage")
    public DataSource dataSourceStorage() {
        return new AtomikosDataSourceBean();
    }

    @Bean("storageMybatisSqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean storageSqlSessionFactoryBean(@Qualifier("originStorage") DataSource dataSource) {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage("com.fly.dao.product");
        return mybatisSqlSessionFactoryBean;
    }

    @Bean(name = "storageSqlSessionTemplate")
    public SqlSessionTemplate storageSqlSessionTemplate(
        @Qualifier("storageMybatisSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
