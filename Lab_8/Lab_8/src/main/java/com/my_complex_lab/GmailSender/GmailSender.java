package com.my_complex_lab.GmailSender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {
    public static void fatalSend(String text) {
        final String gmail = "ironciks@gmail.com"; // замініть на вашу електронну адресу
        final String passcode = "tvssqsnlhxbylbgk"; // замініть на ваш пароль або токен

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmail, passcode);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(gmail)); // замініть на адресу отримувача
            message.setSubject("Java");
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {

        }
    }
}