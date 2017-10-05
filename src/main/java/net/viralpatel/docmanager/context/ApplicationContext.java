package net.viralpatel.docmanager.context;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.viralpatel.docmanager.service.config.PropertyConfig;
import net.viralpatel.docmanager.service.model.Document;

@EnableWebMvc
@Configuration
@ComponentScan("net.viralpatel.docmanager.service")
public class ApplicationContext {

  private PropertyConfig config;

  @Autowired
  public ApplicationContext(PropertyConfig config) {
    this.config = config;
  }

  @Bean
  public SessionFactory sessionFactory() {
    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(restDataSource());
    sessionBuilder.addAnnotatedClasses(Document.class);
    sessionBuilder.setProperties(hibernateProperties());

    return sessionBuilder.buildSessionFactory();
  }

  @Bean
  public DataSource restDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(config.getDriverClassName());
    dataSource.setUrl(config.getDatabaseurl());
    dataSource.setUsername(config.getUsername());
    dataSource.setPassword(config.getPassword());

    return dataSource;
  }

  Properties hibernateProperties() {

    Properties properties = new Properties();
    properties.put("hibernate.dialect", config.getDialect());
    properties.put("hibernate.show_sql", true);
    properties.put("hibernate.connection.SetBigStringTryClob", true);
    properties.put("hibernate.jdbc.batch_size", 0);

    return properties;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

  HibernateTransactionManager txManager = new HibernateTransactionManager();
  txManager.setSessionFactory(sessionFactory);

  return txManager;}
}