package com.dxc.imda.cam.common.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	   
	public void sendEmail(JavaMailSender javaMailSender, String from, String to, String subject, String msgText){	
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(msgText);
		javaMailSender.send(mailMessage);
        logger.info("sendEmail Successful. ");
	}
	
	public void sendMail(JavaMailSender javaMailSender, String from, String to, String subject, String msgText){		
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); // <--- THIS IS IMPORTANT
			helper.setTo(to);
	        helper.setSubject(subject);	       
	        helper.setText(msgText); //true indicates body is html
	        javaMailSender.send(message);
	        logger.info("sendMail Successful. ");
		} catch (MessagingException e) {
			logger.error("sendMail Error. ");
			e.printStackTrace();
		}       
    }
}

