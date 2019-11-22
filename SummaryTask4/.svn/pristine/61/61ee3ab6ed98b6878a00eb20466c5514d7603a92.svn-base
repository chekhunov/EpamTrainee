-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: dhotel
-- ------------------------------------------------------
-- Server version	8.0.16

 SET NAMES utf8 ;

--
-- Table structure for table `apt_text`
--

DROP TABLE IF EXISTS `apt_text`;

CREATE TABLE `apt_text` (
  `text_id` int(11) NOT NULL AUTO_INCREMENT,
  `locale` enum('en','ru') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `apt_loc_text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `text_number` int(11) NOT NULL,
  PRIMARY KEY (`text_id`),
  UNIQUE KEY `text_number` (`text_number`,`locale`)
);
--
-- Dumping data for table `apt_text`
--

LOCK TABLES `apt_text` WRITE;

INSERT INTO `apt_text` VALUES (1,'en','Great room with TV and perfect design.',1),(2,'ru','Отличная комната с телевизором и прекрасным оформлением.',1),(3,'en','Comfortable room with a great view. Additional chair provided.',2),(4,'ru','Комфортабельная комната с прекрасным видом из окна. Предоставляется дополнительное креслою',2),(5,'en','Homely room with small balcony. ',3),(6,'ru','Уютная комната с небольшим балконом.',3),(7,'en','Old fashioned room with vintage chandelier.',4),(8,'ru','Комната в старинном стиле с знаменитым канделябром 19ого века.',4),(9,'en','Luxurious room in scythians treasures style design.',5),(10,'ru','Роскошная комната. Декор выполнен в стиле сюжетов со скифских украшений',5),(11,'en','Extra luxurious room. Pink carpet and white walls make it look futuristic.',6),(12,'ru','Роскошнейшая комната. Розовый ковер и белые стены переносят в мир будущего.',6),(13,'en','Lux room where you can take any of your pets you want.',7),(14,'ru','Прекрасная комната. Вы можете заселиться туда с любым домашним животным.',7),(15,'en','Nice room with magnificent view. Big balcony.',8),(16,'ru','Комната с приятным дизайном и великолепным видом из окна. Большой балкон.',8),(17,'en','Large lux room in penthouse. The best one we can offer. Awesome view from the terrace. Two nice cats can be provided.',9),(18,'ru','Просторная комната в пентхаусе, одна из лучших, которую может предложить отель. Бесподобный вид из окна, выход на терассу.\n Есть возможность предоставить двух пушистых котов.',9),(19,'en','Simple but big and well designed room.\n Perfect for people with pets and without.',10),(20,'ru','Простая, но очень большая и стильная комната. Идеальна для людей с домашними животными и без.',10);

UNLOCK TABLES;

-- Dump completed on 2019-11-04 15:35:53
