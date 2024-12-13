package com.example.servicepay.service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String emailServer;
	
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    
    
    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async("taskExecutor")
    public void sendEmail(String to, String subject, String templateName, Map<String, Object> model) throws MessagingException {
 
    	// Prepare the email context
        Context context = new Context();
        context.setVariables(model);

        // Render the HTML content using Thymeleaf template
        String htmlContent = templateEngine.process(templateName, context);

        // Create the email message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set email details
        helper.setFrom(emailServer);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true for HTML content

        
        // Send the email
        mailSender.send(message);
    }
    

}
