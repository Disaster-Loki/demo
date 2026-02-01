package com.example.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailException;
import com.example.demo.dto.MailBody;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;
    
    @Value("${spring.mail.enabled:true}")
    private boolean emailEnabled;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(MailBody mailBody) {
        if (!emailEnabled) {
            logger.warn("⚠️ Email service is disabled. Skipping email to: {}", mailBody.to());
            return;
        }
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailBody.to());
            message.setFrom(from);
            message.setSubject(mailBody.subject());
            message.setText(mailBody.text());
            javaMailSender.send(message);
            logger.info("✅ Email enviado com sucesso para: {}", mailBody.to());
        } 
        catch (MailException e) {
            logger.error("❌ Erro ao enviar email para {}: {} (Verifique credenciais SMTP)", 
                mailBody.to(), e.getMessage());
            logger.warn("💡 Para usar SMTP real, configure EMAIL_USERNAME e EMAIL_PASSWORD");
        }
        catch (Exception e) {
            logger.error("❌ Erro inesperado ao enviar email: {}", e.getMessage(), e);
        }
    }
}

