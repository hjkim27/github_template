package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:/database.properties")
})
@MapperScan(value = "com.example.demo.mapper.second", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class DatabaseSecondConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "secondHikariConfig")
    @ConfigurationProperties(prefix = "second.datasource.hikari")
    public HikariConfig hikariConfig() {

        HikariConfig hikariConfig = new HikariConfig();
        return hikariConfig;
    }

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource dataSource(@Autowired @Qualifier("secondHikariConfig") HikariConfig hikariConfig) throws Exception {
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig());
        return hikariDataSource;
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/second/*Mapper.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mapper/mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.demo");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "secondSqlSession")
    public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
