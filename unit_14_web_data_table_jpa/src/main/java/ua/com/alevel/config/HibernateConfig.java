package ua.com.alevel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;

    @Value("${spring.jpa.properties.hibernate.jdbc.max_size}")
    private String maxSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.min_size}")
    private String minSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String batchSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.fetch_size}")
    private String fetchSize;

    @Value("${spring.jpa.properties.hibernate.enable_lazy_load_no_trans}")
    private String lazyLoad;

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";
    private static final String MAX_SIZE = "hibernate.c3p0.max_size";
    private static final String MIN_SIZE = "hibernate.c3p0.min_size";
    private static final String BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String ENTITY_MANAGER_PACKAGES_TO_SCAN = "ua.com.alevel.persistence.entity";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITY_MANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        try {
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactoryBean().getObject());
        return manager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, dialect);
        properties.put(HIBERNATE_HBM2DDL_AUTO, hbm2ddl);
        properties.put(HIBERNATE_SHOW_SQL, showSql);
        properties.put(HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS, lazyLoad);
        properties.put(MAX_SIZE, maxSize);
        properties.put(MIN_SIZE, minSize);
        properties.put(BATCH_SIZE, batchSize);
        properties.put(FETCH_SIZE, fetchSize);
        return properties;
    }
}
