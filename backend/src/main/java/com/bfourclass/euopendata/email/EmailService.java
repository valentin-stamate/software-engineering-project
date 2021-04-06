package com.bfourclass.euopendata.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmailVerificationEmail(String username, String destination, String key) {
        MimeMessage mail = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);

        try {
            helper.setFrom("noreply@euopendata.com");
            helper.setTo(destination);
            helper.setSubject("Email Verification");
            helper.setText("Thank you for registering <b>" + username + "</b> ", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(mail);

    }
}
