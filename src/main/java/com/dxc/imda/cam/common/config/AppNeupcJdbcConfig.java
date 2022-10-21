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
@EnableJpaRepositories(basePackages = { "com.dxc.imda.cam.neupc.dao", "com.dxc.imda.cam.neupc.entity" },
	entityManagerFactoryRef = "neupcEntityManager",
	transactionManagerRef = "neupcTransactionManager")
public class AppNeupcJdbcConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private Environment env;	
	    
	@Bean(name = "neupcDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.neupc")
    public DataSource neupcDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.neupc.driverClassName"));
    	dataSource.setUrl(env.getProperty("spring.datasource.neupc.url"));
    	dataSource.setUsername(env.getProperty("spring.datasource.neupc.username"));
    	dataSource.setPassword(env.getProperty("spring.datasource.neupc.password"));
    	return dataSource;
    }

	@Bean(name = "neupcJdbcTemplate")
	@Primary
	public JdbcTemplate neupcJdbcTemplate(@Qualifier("neupcDataSource") DataSource neupcDS) {
		return new JdbcTemplate(neupcDS);
	}
	
	@Bean(name = "neupcEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean neupcEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(neupcDataSource());
        em.setPackagesToScan(new String[] { "com.dxc.imda.cam.neupc.entity" });

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
    public PlatformTransactionManager neupcTransactionManager() { 
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(neupcEntityManager().getObject());
        return transactionManager;
    }
}
