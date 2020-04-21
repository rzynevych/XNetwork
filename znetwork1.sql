-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: znetwork1
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `dtype` varchar(31) NOT NULL,
  `userid` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `validate` tinyint DEFAULT NULL,
  `friend` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES ('AppUser',1,'ruslanzinewich@gmail.com',NULL,'$2a$10$BVFzWU.WpYVsMqFJO2eW1O2QHPLeAEp5xZUJjnrVy5LTPo3p2iCB.',NULL,'Ruslan',NULL,NULL),('AppUser',2,'spad20@yandex.ru',NULL,'$2a$10$MZlccn/A0S18yI.CFJH5ouplbkUTP7WpA9sVN0/IhCMGA952Nlh9G',NULL,'Ilya',NULL,NULL),('AppUser',5,'example@example.com',NULL,'$2a$10$CjvDNva2MYYK3Fhp.97.meNyJj8g8/9YRlUKCbpwf3SHM5OsU6uLu',NULL,'example',NULL,NULL),('AppUser',6,'xman@xxx.com',NULL,'$2a$10$tHJBI09u2qTtUfr8d7R/OO1mip1BLSLN5hdN2n0WzOIcoHMhEC5q.',NULL,'X-MAN',NULL,NULL);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `chatid` bigint NOT NULL,
  `id1` int NOT NULL,
  `id2` int NOT NULL,
  `last_used` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`chatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (4,1,2,'2020-04-21 00:00:00.000000'),(9,1,5,'2020-04-21 17:06:41.621000');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship`
--

DROP TABLE IF EXISTS `friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friendship` (
  `noteid` bigint NOT NULL,
  `add_date` datetime(6) DEFAULT NULL,
  `friendid` int DEFAULT NULL,
  `usrid` int DEFAULT NULL,
  PRIMARY KEY (`noteid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
INSERT INTO `friendship` VALUES (3,NULL,2,1),(7,NULL,5,1),(8,NULL,6,1);
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (10),(10);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `messageid` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `parentid` int DEFAULT NULL,
  `receiver` int DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (3,'2020-04-21 08:57:10.323953',1,2,'hello','Ruslan'),(4,'2020-04-21 09:01:24.957373',1,2,'Привет!','Ruslan'),(5,'2020-04-21 09:05:57.600187',1,2,'Check!','Ruslan'),(6,'2020-04-21 09:29:03.305052',1,2,'asdf','Ruslan'),(7,'2020-04-21 09:34:15.901813',1,2,'Привет!','Ruslan'),(8,'2020-04-21 09:34:40.186631',1,2,'123','Ruslan'),(9,'2020-04-21 09:34:46.209074',1,2,'234','Ruslan'),(10,'2020-04-21 09:34:55.818606',1,2,'Привет!','Ruslan'),(11,'2020-04-21 09:42:27.803091',1,0,'123','Ruslan'),(12,'2020-04-21 09:53:13.637598',1,2,'123','Ruslan'),(13,'2020-04-21 09:53:18.250085',1,2,'ewfrg','Ruslan'),(14,'2020-04-21 09:53:21.580204',1,2,'ntymuy,k','Ruslan'),(15,'2020-04-21 09:53:27.473386',1,2,'1234567890','Ruslan'),(16,'2020-04-21 11:16:31.625673',2,0,'Hello!','Ilya'),(17,'2020-04-21 11:16:54.076051',2,0,'Checking!','Ilya'),(18,'2020-04-21 11:17:01.350345',2,0,'It works!','Ilya'),(19,'2020-04-21 15:22:32.913828',1,0,'Some post','Ruslan'),(20,'2020-04-21 15:23:21.986271',1,0,'Some another post','Ruslan'),(21,'2020-04-21 15:23:45.592329',1,0,'Other post','Ruslan'),(22,'2020-04-21 17:01:37.719000',1,0,'C','Ruslan'),(23,'2020-04-21 17:03:35.898000',1,0,'Checking sending messages...','Ruslan'),(24,'2020-04-21 17:04:30.221000',1,2,'Hello!','Ruslan'),(25,'2020-04-21 17:06:41.621000',1,5,'Hello, example!','Ruslan');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-21 20:10:21
