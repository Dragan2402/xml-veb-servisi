package com.euprava.euprava.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendEmailWithAttachment(String to, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Podnesen zahtjev");
            helper.setText("Postovani,\n\nVas zahtjev je uspjesno podnesen. U prilogu vam saljemo pdf kopiju vaseg zahtjeva.\n\nVasa Euprava.");
            File pdfAttachment = new File(pathToAttachment);
            helper.addAttachment("zahtjev.pdf", pdfAttachment);

            emailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
