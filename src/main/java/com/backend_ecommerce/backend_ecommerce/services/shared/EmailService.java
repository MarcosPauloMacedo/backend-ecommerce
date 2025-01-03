package com.backend_ecommerce.backend_ecommerce.services.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        
        javaMailSender.send(message);
    }

    public void sendWelcomeEmail(String to) {
        sendEmail(to, "Welcome", "Welcome to our platform");
    }
}
