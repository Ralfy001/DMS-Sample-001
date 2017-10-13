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

import net.viralpatel.docmanager.service.config.PropertyConfig;
import net.viralpatel.docmanager.service.model.Document;

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
    
    properties.put("hibernate.connection.driver_class", config.getDriverClassName());
    properties.put("hibernate.connection.url", config.getDatabaseurl());
    properties.put("hibernate.connection.username", config.getUsername());
    properties.put("hibernate.connection.password", config.getPassword());
        
    properties.put("hibernate.dialect", config.getDialect());
    properties.put("hibernate.show_sql", true);
    properties.put("hibernate.connection.SetBigStringTryClob", true);
    properties.put("hibernate.jdbc.batch_size", 0);
    
    
    
    properties.put("hibernate.c3p0.min_size", 5);
    properties.put("hibernate.c3p0.max_size", 20);
    properties.put("hibernate.c3p0.timeout", 300);
    properties.put("hibernate.c3p0.max_statements", 50);
    properties.put("hibernate.c3p0.idle_test_period", 3000);
    

    return properties;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

  HibernateTransactionManager txManager = new HibernateTransactionManager();
  txManager.setSessionFactory(sessionFactory);

  return txManager;}
}