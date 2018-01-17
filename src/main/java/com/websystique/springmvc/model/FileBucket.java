package com.websystique.springmvc.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

  MultipartFile file;

  String description;

  String emailText;

  String emailAdresse;

  public void setEmailText(final String emailText) {
    this.emailText = emailText;
  }

  public void setEmailAdresse(final String emailAdresse) {
    this.emailAdresse = emailAdresse;
  }

  public String getEmailAdresse() {
    return emailAdresse;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getDescription() {
    return description;
  }

  public String getEmailText() {
    return emailText;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}