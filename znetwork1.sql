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
INSERT INTO `app_user` VALUES ('AppUser',1,'ruslanzinewich@gmail.com',NULL,'$2a$10$BVFzWU.WpYVsMqFJO2eW1O2QHPLeAEp5xZUJjnrVy5LTPo3p2iCB.',NULL,'Ruslan',NULL,NULL),('AppUser',2,'spad20@yandex.ru',NULL,'$2a$10$MZlccn/A0S18yI.CFJH5ouplbkUTP7WpA9sVN0/IhCMGA952Nlh9G',NULL,'Ilya',NULL,NULL),('AppUser',5,'example@example.com',NULL,'$2a$10$CjvDNva2MYYK3Fhp.97.meNyJj8g8/9YRlUKCbpwf3SHM5OsU6uLu',NULL,'example',NULL,NULL),('AppUser',6,'xman@xxx.com',NULL,'$2a$10$tHJBI09u2qTtUfr8d7R/OO1mip1BLSLN5hdN2n0WzOIcoHMhEC5q.',NULL,'X-MAN',NULL,NULL),('AppUser',11,'cool@cool.com',NULL,'$2a$10$0dRztG4AyP9VcJrMWZ.FMeeSIsBgl44Gyk80zdbjmjc6nVlAL9tB.',NULL,'Cool User',NULL,NULL);
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
INSERT INTO `chat` VALUES (4,1,2,'2020-04-22 00:00:00.000000'),(9,1,5,'2020-04-21 17:06:41.621000'),(15,11,5,'2020-04-21 22:07:57.437000'),(16,11,2,'2020-04-21 22:08:15.835000'),(92,1,1,'2020-04-22 09:56:23.452000');
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
INSERT INTO `friendship` VALUES (7,NULL,5,1),(12,NULL,1,11),(13,NULL,2,11),(14,NULL,5,11),(90,NULL,6,1),(91,NULL,2,1),(93,NULL,1,2);
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
INSERT INTO `hibernate_sequence` VALUES (94),(94);
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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (3,'2020-04-21 08:57:10.323953',1,2,'hello','Ruslan'),(4,'2020-04-21 09:01:24.957373',1,2,'Привет!','Ruslan'),(5,'2020-04-21 09:05:57.600187',1,2,'Check!','Ruslan'),(6,'2020-04-21 09:29:03.305052',1,2,'asdf','Ruslan'),(7,'2020-04-21 09:34:15.901813',1,2,'Привет!','Ruslan'),(8,'2020-04-21 09:34:40.186631',1,2,'123','Ruslan'),(9,'2020-04-21 09:34:46.209074',1,2,'234','Ruslan'),(10,'2020-04-21 09:34:55.818606',1,2,'Привет!','Ruslan'),(11,'2020-04-21 09:42:27.803091',1,0,'123','Ruslan'),(12,'2020-04-21 09:53:13.637598',1,2,'123','Ruslan'),(13,'2020-04-21 09:53:18.250085',1,2,'ewfrg','Ruslan'),(14,'2020-04-21 09:53:21.580204',1,2,'ntymuy,k','Ruslan'),(15,'2020-04-21 09:53:27.473386',1,2,'1234567890','Ruslan'),(16,'2020-04-21 11:16:31.625673',2,0,'Hello!','Ilya'),(17,'2020-04-21 11:16:54.076051',2,0,'Checking!','Ilya'),(18,'2020-04-21 11:17:01.350345',2,0,'It works!','Ilya'),(19,'2020-04-21 15:22:32.913828',1,0,'Some post','Ruslan'),(20,'2020-04-21 15:23:21.986271',1,0,'Some another post','Ruslan'),(21,'2020-04-21 15:23:45.592329',1,0,'Other post','Ruslan'),(22,'2020-04-21 17:01:37.719000',1,0,'C','Ruslan'),(23,'2020-04-21 17:03:35.898000',1,0,'Checking sending messages...','Ruslan'),(24,'2020-04-21 17:04:30.221000',1,2,'Hello!','Ruslan'),(25,'2020-04-21 17:06:41.621000',1,5,'Hello, example!','Ruslan'),(26,'2020-04-21 22:07:11.360000',11,0,'Hello everyone!','Cool User'),(27,'2020-04-21 22:07:28.093000',11,0,'I\'m Cool User!','Cool User'),(28,'2020-04-21 22:07:57.437000',11,5,'Hello, example!','Cool User'),(29,'2020-04-21 22:08:15.835000',11,2,'Hello, Ilya!','Cool User'),(30,'2020-04-22 09:56:23.452000',1,1,'hello','Ruslan'),(31,'2020-04-22 09:56:39.123000',1,2,'How are you?','Ruslan'),(32,'2020-04-22 10:32:50.284000',1,2,'Checking...','Ruslan'),(33,'2020-04-22 10:32:58.493000',1,2,'Checking...','Ruslan'),(34,'2020-04-22 10:33:02.968000',1,2,'123','Ruslan'),(35,'2020-04-22 10:33:46.763000',1,2,'qwerty','Ruslan'),(36,'2020-04-22 10:33:54.214000',1,2,'reagrehte','Ruslan'),(37,'2020-04-22 10:36:02.055000',1,2,'123','Ruslan'),(38,'2020-04-22 10:36:35.305000',1,2,'Привет!','Ruslan'),(39,'2020-04-22 10:36:38.311000',1,2,'12345','Ruslan'),(40,'2020-04-22 10:36:41.255000',1,2,'asdf','Ruslan'),(41,'2020-04-22 10:36:44.668000',1,2,'1','Ruslan'),(42,'2020-04-22 10:36:49.845000',1,2,'Checking sending messages...','Ruslan'),(43,'2020-04-22 10:36:54.822000',1,2,'Check!','Ruslan'),(44,'2020-04-22 10:36:58.396000',1,2,'Привет!','Ruslan'),(45,'2020-04-22 10:37:01.624000',1,2,'123','Ruslan'),(46,'2020-04-22 10:37:06.870000',1,2,'12345','Ruslan'),(47,'2020-04-22 10:37:10.827000',1,2,'Привет!','Ruslan'),(48,'2020-04-22 10:37:15.365000',1,2,'Checking...','Ruslan'),(49,'2020-04-22 10:37:19.579000',1,2,'12345','Ruslan'),(50,'2020-04-22 10:39:58.515000',1,2,'More messages!','Ruslan'),(51,'2020-04-22 10:40:02.713000',1,2,'gsthhs','Ruslan'),(52,'2020-04-22 10:40:10.909000',1,2,'Checking...','Ruslan'),(53,'2020-04-22 10:40:15.352000',1,2,'qwerty','Ruslan'),(54,'2020-04-22 10:40:20.978000',1,2,'Asdf','Ruslan'),(55,'2020-04-22 10:41:13.674000',1,2,'123456','Ruslan'),(56,'2020-04-22 10:41:18.139000',1,2,'123456asdfgh','Ruslan'),(57,'2020-04-22 10:41:21.069000',1,2,'zxcvbnm','Ruslan'),(58,'2020-04-22 10:41:23.254000',1,2,'asdfghj','Ruslan'),(59,'2020-04-22 10:41:25.236000',1,2,'qwertyu','Ruslan'),(60,'2020-04-22 10:41:27.169000',1,2,'asdfghjk','Ruslan'),(61,'2020-04-22 10:41:32.642000',1,2,'zxcvbnm','Ruslan'),(62,'2020-04-22 10:41:34.711000',1,2,'hjkl;\'','Ruslan'),(63,'2020-04-22 10:41:36.677000',1,2,'fghjkl','Ruslan'),(64,'2020-04-22 10:41:39.125000',1,2,'wertyuiop','Ruslan'),(65,'2020-04-22 10:41:53.608000',1,2,'1qwertyu','Ruslan'),(66,'2020-04-22 10:41:56.553000',1,2,'2qwertyu','Ruslan'),(67,'2020-04-22 10:41:58.985000',1,2,'3qwertyui','Ruslan'),(68,'2020-04-22 10:42:03.411000',1,2,'4asdfghjk','Ruslan'),(69,'2020-04-22 10:42:17.224000',1,2,'Checking...','Ruslan'),(70,'2020-04-22 10:42:20.359000',1,2,'qwerty','Ruslan'),(71,'2020-04-22 10:42:23.545000',1,2,'Привет!','Ruslan'),(72,'2020-04-22 10:42:28.451000',1,2,'Checking sending messages...','Ruslan'),(73,'2020-04-22 10:42:34.378000',1,2,'Check!','Ruslan'),(74,'2020-04-22 11:08:16.280000',1,2,'Hello!','Ruslan'),(75,'2020-04-22 11:08:29.730000',1,2,'qwerty','Ruslan'),(76,'2020-04-22 14:27:59.000000',1,0,'Message76','Ruslan'),(77,'2020-04-22 14:29:31.000000',1,0,'Message77','Ruslan'),(78,'2020-04-22 14:29:31.000000',1,0,'Message78','Ruslan'),(79,'2020-04-22 14:29:31.000000',1,0,'Message79','Ruslan'),(80,'2020-04-22 14:29:31.000000',1,0,'Message80','Ruslan'),(81,'2020-04-22 14:29:31.000000',1,0,'Message81','Ruslan'),(82,'2020-04-22 14:29:31.000000',1,0,'Message82','Ruslan'),(83,'2020-04-22 14:29:31.000000',1,0,'Message83','Ruslan'),(84,'2020-04-22 14:29:31.000000',1,0,'Message84','Ruslan'),(85,'2020-04-22 14:29:31.000000',1,0,'Message85','Ruslan'),(86,'2020-04-22 14:29:31.000000',1,0,'Message86','Ruslan'),(87,'2020-04-22 14:29:31.000000',1,0,'Message87','Ruslan'),(88,'2020-04-22 14:29:31.000000',1,0,'Message88','Ruslan'),(89,'2020-04-22 14:29:31.000000',1,0,'Message89','Ruslan'),(90,'2020-04-22 14:29:31.000000',1,0,'Message90','Ruslan'),(91,'2020-04-22 14:29:31.000000',1,0,'Message91','Ruslan'),(92,'2020-04-22 14:29:31.000000',1,0,'Message92','Ruslan'),(93,'2020-04-22 14:29:31.000000',1,0,'Message93','Ruslan'),(94,'2020-04-22 14:29:31.000000',1,0,'Message94','Ruslan'),(95,'2020-04-22 14:29:31.000000',1,0,'Message95','Ruslan'),(96,'2020-04-22 14:29:31.000000',1,0,'Message96','Ruslan'),(97,'2020-04-22 14:29:31.000000',1,0,'Message97','Ruslan'),(98,'2020-04-22 14:29:31.000000',1,0,'Message98','Ruslan'),(99,'2020-04-22 14:29:31.000000',1,0,'Message99','Ruslan'),(100,'2020-04-22 14:29:31.000000',1,0,'Message100','Ruslan'),(101,'2020-04-22 14:29:31.000000',1,0,'Message101','Ruslan'),(102,'2020-04-22 14:29:31.000000',1,0,'Message102','Ruslan'),(103,'2020-04-22 14:29:31.000000',1,0,'Message103','Ruslan'),(104,'2020-04-22 14:29:31.000000',1,0,'Message104','Ruslan'),(105,'2020-04-22 14:29:31.000000',1,0,'Message105','Ruslan'),(106,'2020-04-22 14:29:31.000000',1,0,'Message106','Ruslan'),(107,'2020-04-22 14:29:31.000000',1,0,'Message107','Ruslan'),(108,'2020-04-22 14:29:31.000000',1,0,'Message108','Ruslan'),(109,'2020-04-22 14:29:31.000000',1,0,'Message109','Ruslan'),(110,'2020-04-22 14:29:31.000000',1,0,'Message110','Ruslan'),(111,'2020-04-22 14:29:31.000000',1,0,'Message111','Ruslan'),(112,'2020-04-22 14:29:31.000000',1,0,'Message112','Ruslan'),(113,'2020-04-22 14:29:31.000000',1,0,'Message113','Ruslan'),(114,'2020-04-22 14:29:31.000000',1,0,'Message114','Ruslan'),(115,'2020-04-22 14:29:31.000000',1,0,'Message115','Ruslan'),(116,'2020-04-22 14:29:31.000000',1,0,'Message116','Ruslan'),(117,'2020-04-22 14:29:31.000000',1,0,'Message117','Ruslan'),(118,'2020-04-22 14:29:31.000000',1,0,'Message118','Ruslan'),(119,'2020-04-22 14:29:31.000000',1,0,'Message119','Ruslan'),(120,'2020-04-22 14:29:31.000000',1,0,'Message120','Ruslan'),(121,'2020-04-22 14:29:31.000000',1,0,'Message121','Ruslan'),(122,'2020-04-22 14:29:31.000000',1,0,'Message122','Ruslan'),(123,'2020-04-22 14:29:31.000000',1,0,'Message123','Ruslan'),(124,'2020-04-22 14:29:31.000000',1,0,'Message124','Ruslan'),(125,'2020-04-22 14:29:31.000000',1,0,'Message125','Ruslan'),(126,'2020-04-22 14:29:31.000000',1,0,'Message126','Ruslan'),(127,'2020-04-22 14:29:31.000000',1,0,'Message127','Ruslan'),(128,'2020-04-22 14:29:31.000000',1,0,'Message128','Ruslan'),(129,'2020-04-22 14:29:31.000000',1,0,'Message129','Ruslan'),(130,'2020-04-22 11:33:21.047000',2,0,'write post','Ilya');
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

-- Dump completed on 2020-04-22 14:35:09
