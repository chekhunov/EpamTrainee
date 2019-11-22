/* создание базы данных*/
DROP DATABASE practice8;

CREATE DATABASE practice8;

/* создание таблицы юзер*/
DROP TABLE IF EXISTS `users`;

SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'ivanov');

UNLOCK TABLES;

/* создание таблицы группа*/
DROP TABLE IF EXISTS `teams`;
SET character_set_client = utf8mb4 ;

CREATE TABLE `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `teams` WRITE;

INSERT INTO `teams` VALUES (1,'teamA');

UNLOCK TABLES;

/* создание таблицы юзер-группа*/
DROP TABLE IF EXISTS `users_teams`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `users_teams` (
  `user_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `users_teams` WRITE;

INSERT INTO `users_teams` VALUES (1,1);

UNLOCK TABLES;