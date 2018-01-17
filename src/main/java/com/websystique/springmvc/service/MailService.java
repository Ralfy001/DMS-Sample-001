package com.websystique.springmvc.service;

public interface MailService {

  public void sendEmail(final String adresse, final String text);
}
