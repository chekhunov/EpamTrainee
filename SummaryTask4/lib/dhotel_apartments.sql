-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: dhotel
-- ------------------------------------------------------
-- Server version	8.0.16

 SET NAMES utf8 ;

--
-- Table structure for table `apartments`
--

DROP TABLE IF EXISTS `apartments`;

CREATE TABLE `apartments` (
  `apt_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `places` int(11) NOT NULL,
  `class` tinyint(4) NOT NULL,
  `cost` int(11) NOT NULL,
  `description` int(11) DEFAULT NULL,
  PRIMARY KEY (`apt_id`),
  UNIQUE KEY `number` (`number`),
  KEY `description` (`description`),
  CONSTRAINT `apartments_ibfk_1` FOREIGN KEY (`description`) REFERENCES `apt_text` (`text_number`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `apartments`
--

LOCK TABLES `apartments` WRITE;

INSERT INTO `apartments` VALUES (1,'101',1,1,50,5),(2,'102',2,1,100,6),(3,'103',3,1,150,7),(4,'104',4,1,200,8),(5,'105',1,2,25,1),(6,'106',2,2,50,3),(7,'107',3,2,75,2),(8,'108',4,2,100,2),(9,'201',1,1,50,8),(10,'202',2,1,100,7),(11,'203',3,1,150,6),(12,'204',4,1,200,5),(13,'205',1,2,25,4),(14,'206',2,2,50,3),(15,'207',3,2,75,1),(16,'208',4,2,100,2),(17,'301',1,1,50,5),(18,'302',2,1,100,7),(19,'303',3,1,150,6),(20,'304',4,1,200,6),(21,'305',1,2,25,2),(22,'306',2,2,50,3),(23,'307',3,2,75,1),(24,'308',4,2,100,3),(25,'401',2,1,100,9),(26,'402',4,2,150,10);

UNLOCK TABLES;

-- Dump completed on 2019-11-04 15:35:52
