package com.example.servicepay.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String email;
    	      
	@Value("${security.message.change-password.title}")
	private String messageTitle;
	
	@Value("${security.message.change-password.body}")
	private String messageBody;
	
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    	      
    
    public void sendEmailWithLink(String to, String subject, String link) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String htmlMsg = messageTitle + messageBody
                + "<a href=\"" + link + "\">" + link + "</a>";

        helper.setTo(to);
        helper.setFrom(email);
        helper.setSubject(subject);
        helper.setText(htmlMsg, true);  // true indicates that this is HTML content

        mailSender.send(mimeMessage);
    }

}

