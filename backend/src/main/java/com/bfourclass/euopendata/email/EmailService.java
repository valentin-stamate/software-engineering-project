package com.bfourclass.euopendata.email;

import com.bfourclass.euopendata.globals.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private JavaMailSender emailSender;

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
            helper.setText("Thank you for registering <b>" + username + "</b> ." +
                    "To verify your account press on the button " +
                            "<a href='" + Globals.BACKEND_URL + "user/verify?user_verification_key=" + key +"' target='_blank'>Activate Account</button>.",
                    true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(mail);
    }
}
