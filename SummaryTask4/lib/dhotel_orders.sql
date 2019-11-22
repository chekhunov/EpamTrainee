-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: dhotel
-- ------------------------------------------------------
-- Server version	8.0.16

 SET NAMES utf8 ;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `places` int(11) NOT NULL,
  `class` tinyint(4) NOT NULL,
  `date_in` date NOT NULL,
  `date_out` date NOT NULL,
  `order_apt_id` int(11) DEFAULT NULL,
  `order_additional_info` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `status` enum('REQUESTED','IN_DISCUSSION','APPROVED','PAID','REJECTED') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `order_apt_id` (`order_apt_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`order_apt_id`) REFERENCES `apartments` (`apt_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;

UNLOCK TABLES;

-- Dump completed on 2019-11-04 15:35:53
