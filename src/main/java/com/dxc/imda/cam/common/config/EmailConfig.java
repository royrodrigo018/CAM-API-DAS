package com.dxc.imda.cam.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	
	@Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.mail.nors")
    public JavaMailSender norsMailSender() {
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        return javaMailSenderWithProperties(javaMailSender);    
    }

	@Qualifier
    @Bean
    @ConfigurationProperties(prefix = "spring.mail.neupc")
    public JavaMailSender neupcMailSender() {
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        return javaMailSenderWithProperties(javaMailSender);    
    }
	
	@Qualifier
    @Bean
    @ConfigurationProperties(prefix = "spring.mail.das")
    public JavaMailSender dasMailSender() {
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        return javaMailSenderWithProperties(javaMailSender);    
    }
	
    private JavaMailSender javaMailSenderWithProperties(JavaMailSenderImpl javaMailSender) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
