package net.viralpatel.docmanager.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Component
@PropertySource("classpath:jdbc.properties")
@NoArgsConstructor
public class PropertyConfig {

  @Value("${jdbc.driverClassName}")
  private String driverClassName;

  @Value("${jdbc.dialect}")
  private String dialect;

  @Value("${jdbc.databaseurl}")
  private String databaseurl;

  @Value("${jdbc.username}")
  private String username;

  @Value("${jdbc.password}")
  private String password;
}
