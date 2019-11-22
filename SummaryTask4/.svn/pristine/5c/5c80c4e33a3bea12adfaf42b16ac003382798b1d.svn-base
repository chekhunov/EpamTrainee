-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: dhotel
-- ------------------------------------------------------
-- Server version	8.0.16

 SET NAMES utf8 ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `passhash` varchar(355) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `access_level` enum('admin','user') CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(355) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`)
);

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'admin@admin.adm','admin','21232f297a57a5a743894a0e4a801fc3','admin','Admin'),
(2,'chel@chel.el','chel','ab93ebb3f9c0f13ec887610129fa7910','user','Chel'),
(3,'user@user.us','user','ee11cbb19052e40b07aac0ca060c23ee','user','UserName'),
(4,'tolya@gmail.com','tolay','3bab67ba192bd14f0af04ca80e9e2080','user','tolya');

UNLOCK TABLES;

-- Dump completed on 2019-11-04 15:35:54
