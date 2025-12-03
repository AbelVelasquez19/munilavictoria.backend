package pe.gob.mlvictoria.utility;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
@Configuration
public class MyBatisDataSourceConfig {

    //SIGMUN
    @Primary
    @Bean(name = "sigmunDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sigmun")
    public DataSource sigmunDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "sigmunSqlSessionFactory")
    public SqlSessionFactory sigmunSqlSessionFactory(
            @Qualifier("sigmunDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "sigmunSqlSessionTemplate")
    public SqlSessionTemplate sigmunSqlSessionTemplate(
            @Qualifier("sigmunSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //SIGMUN APPS
    @Bean(name = "sigmunAppsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sigmunapps")
    public DataSource sigmunAppsDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "sigmunAppsSqlSessionFactory")
    public SqlSessionFactory sigmunAppsSqlSessionFactory(
            @Qualifier("sigmunAppsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();
    }
    @Bean(name = "sigmunAppsSqlSessionTemplate")
    public SqlSessionTemplate sigmunAppsSqlSessionTemplate(
            @Qualifier("sigmunAppsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //SISTEMAS_AUDIT
    @Bean(name = "auditDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.audit")
    public DataSource auditDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "auditSqlSessionFactory")
    public SqlSessionFactory auditSqlSessionFactory(
            @Qualifier("auditDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "auditSqlSessionTemplate")
    public SqlSessionTemplate auditSqlSessionTemplate(
            @Qualifier("auditSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
