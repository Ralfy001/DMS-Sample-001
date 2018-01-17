package com.websystique.springmvc.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

  final
  JavaMailSender mailSender;

  @Autowired
  public MailServiceImpl(final JavaMailSender mailSender) {this.mailSender = mailSender;}

  @Override
  public void sendEmail(final String adresse, final String text) {

    MimeMessagePreparator preparator = getMessagePreparator(adresse, text);

    try {
      mailSender.send(preparator);
      System.out.println("Verschicke email");
    } catch (MailException ex) {
      System.err.println(ex.getMessage() + " :email wurde nicht verschickt");
    }
  }

  private MimeMessagePreparator getMessagePreparator(final String adresse, final String text) {
    return mimeMessage -> {
      mimeMessage.setFrom(adresse);
      mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("davidmerlin.pawellek@localhost.com"));
      mimeMessage.setText(text);
      mimeMessage.setSubject("Email Service");
    };
  }
}
