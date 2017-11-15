CREATE DATABASE `websystique` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE websystique;

create table ALBUM (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
  
  
create table FOTO (
   id BIGINT NOT NULL AUTO_INCREMENT,
   user_id BIGINT NOT NULL,
   name  VARCHAR(100) NOT NULL,
   description VARCHAR(255) ,
   type VARCHAR(100) NOT NULL,
   content longblob NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT document_user FOREIGN KEY (user_id) REFERENCES ALBUM (id) ON UPDATE CASCADE ON DELETE CASCADE
);