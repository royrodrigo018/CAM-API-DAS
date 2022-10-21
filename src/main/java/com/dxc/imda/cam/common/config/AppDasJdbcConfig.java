package com.dxc.imda.cam.common.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = { "com.dxc.imda.cam.das.dao", "com.dxc.imda.cam.das.entity" },
	entityManagerFactoryRef = "dasEntityManager",
	transactionManagerRef = "dasTransactionManager")
public class AppDasJdbcConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private Environment env;	
	    
	@Bean(name = "dasDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.das")
    public DataSource dasDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.das.driverClassName"));
    	dataSource.setUrl(env.getProperty("spring.datasource.das.url"));
    	logger.info("url: " + env.getProperty("spring.datasource.das.url"));
    	dataSource.setUsername(env.getProperty("spring.datasource.das.username"));
    	dataSource.setPassword(env.getProperty("spring.datasource.das.password"));
    	return dataSource;
    }

	@Bean(name = "dasJdbcTemplate")
	@Primary
	public JdbcTemplate dasJdbcTemplate(@Qualifier("dasDataSource") DataSource dasDS) {
		return new JdbcTemplate(dasDS);
	}
	
	@Bean(name = "dasEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean dasEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dasDataSource());
        em.setPackagesToScan(new String[] { "com.dxc.imda.cam.das.entity" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }
	
	@Bean
	@Primary    
    public PlatformTransactionManager dasTransactionManager() { 
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(dasEntityManager().getObject());
        return transactionManager;
    }
}
