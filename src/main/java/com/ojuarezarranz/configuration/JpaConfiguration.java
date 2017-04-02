package com.ojuarezarranz.configuration;

import java.util.Properties;
 
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
import com.zaxxer.hikari.HikariDataSource;
 
@Configuration
@EnableJpaRepositories(basePackages = "com.ojuarezarranz.repositories",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {
 
    private int maxPoolSize = 1;
 
   
    @Bean
    @Primary
    public DataSourceProperties dataSourceProperties(){
    	DataSourceProperties dataSourceProperties = new DataSourceProperties();
    	dataSourceProperties.setDriverClassName("org.postgresql.Driver");
    	dataSourceProperties.setUrl("jdbc:postgresql://192.168.1.130:5432/simpledatabase");
    	dataSourceProperties.setUsername("simpledatabase");
    	dataSourceProperties.setPassword("123456");
    	return dataSourceProperties;
    }
 
    @Bean
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
            HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                    .create(dataSourceProperties.getClassLoader())                    
                    .driverClassName(dataSourceProperties.getDriverClassName())
                    .url(dataSourceProperties.getUrl())
                    .username(dataSourceProperties.getUsername())
                    .password(dataSourceProperties.getPassword())
                    
                    .type(HikariDataSource.class)
                    .build();
            dataSource.setMaximumPoolSize(maxPoolSize);
            return dataSource;
    }
 
  
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "com.ojuarezarranz.entities" });
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }
 
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }
 
    
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",  "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql",  "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("transactionIsolation", "TRANSACTION_READ_COMMITTED");
       // properties.put("spring.jpa.show-sql","true");
        
        properties.put("hibernate.ddl-auto", "update");
        return properties;
    }
 
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
 
}