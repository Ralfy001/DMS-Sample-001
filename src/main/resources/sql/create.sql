CREATE DATABASE `websystique` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE websystique;

create table ALBUM (
   id BIGINT NOT NULL AUTO_INCREMENT,
   album_id VARCHAR(30) NOT NULL,
   title VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (album_id)
);
  
  
create table FOTO (
   id BIGINT NOT NULL AUTO_INCREMENT,
   album_id BIGINT NOT NULL,
   name  VARCHAR(100) NOT NULL,
   description VARCHAR(255) ,
   type VARCHAR(100) NOT NULL,
   content longblob NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT foto_album FOREIGN KEY (album_id) REFERENCES ALBUM (id) ON UPDATE CASCADE ON DELETE CASCADE
);