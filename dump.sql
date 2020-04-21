-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: znetwork
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
  `userid` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `validate` tinyint DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  `friend` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'asdf@asdfa.com',NULL,'$2a$10$p5EaSXuHt5a/odmV8RGMJub.kVdjSS1TlS6vKClWpo28C/5RdjHWK',NULL,'asdf',NULL,'AppUser',NULL);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chats` (
  `chat_id` bigint NOT NULL AUTO_INCREMENT,
  `id_1` int NOT NULL,
  `id_2` int NOT NULL,
  `last_used` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (1,1,6,'2020-04-20 14:44:06'),(2,1,4,'2020-04-20 11:39:13'),(3,1,7,'2020-04-20 12:07:29'),(4,1,5,'2020-04-20 11:39:43'),(5,1,8,'2020-04-20 11:39:52'),(6,6,4,'2020-04-20 11:42:59'),(7,6,5,'2020-04-20 11:43:12'),(8,6,8,'2020-04-20 11:43:25'),(9,6,7,'2020-04-20 11:43:33'),(10,4,8,'2020-04-20 11:46:19'),(11,4,5,'2020-04-20 11:46:34'),(12,8,5,'2020-04-20 11:47:08'),(13,8,7,'2020-04-20 11:47:15'),(14,7,5,'2020-04-20 11:49:01'),(15,1,1,'2020-04-20 12:07:21');
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `note_id` bigint NOT NULL AUTO_INCREMENT,
  `usr_id` int NOT NULL,
  `friend_id` int NOT NULL,
  `add_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (20,8,1,'2020-04-04 18:39:38'),(21,8,4,'2020-04-04 18:39:47'),(30,8,7,'2020-04-07 00:09:18'),(45,6,4,'2020-04-12 00:11:37'),(47,6,1,'2020-04-12 00:12:00'),(108,6,7,'2020-04-15 17:57:28'),(112,1,6,'2020-04-16 18:04:57'),(119,1,5,'2020-04-16 20:30:56'),(120,1,8,'2020-04-19 12:20:45'),(129,4,1,'2020-04-19 15:57:28'),(130,4,6,'2020-04-19 15:57:49'),(131,4,5,'2020-04-19 16:01:26'),(138,1,4,'2020-04-19 17:35:16');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (2),(2);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `receiver` int NOT NULL,
  `text` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (2,2,0,'Hello!','Ruslan','2020-03-31 23:46:44'),(3,2,0,'asdf','Ruslan','2020-03-31 23:49:26'),(4,2,0,'It works!!!!','Ruslan','2020-03-31 23:49:42'),(5,2,0,'','Ruslan','2020-03-31 23:49:44'),(6,2,0,'this is znetwork!','Ruslan','2020-04-01 01:02:46'),(7,2,0,'qwerty','Ruslan','2020-04-01 01:02:56'),(8,1,0,'12345','Ruslan','2020-04-01 20:20:07'),(9,1,0,'Привет!','Ruslan','2020-04-01 20:29:15'),(10,4,0,'asdf','example','2020-04-01 20:47:47'),(11,4,0,'I\'m example!','example','2020-04-01 20:48:19'),(12,4,0,'Yeah it works!','example','2020-04-01 20:48:37'),(13,1,0,'Hello, example!','Ruslan','2020-04-01 20:48:59'),(14,1,0,'How are you?','Ruslan','2020-04-01 20:49:19'),(15,4,0,'Hello, Ruslan! I\'m fine!','example','2020-04-01 20:50:31'),(16,4,0,'How are you?','example','2020-04-01 20:51:07'),(17,1,0,'Very good)','Ruslan','2020-04-01 20:51:36'),(18,1,0,'Привет!','Ruslan','2020-04-02 14:42:49'),(19,1,0,'asdf','Ruslan','2020-04-02 18:41:02'),(20,1,0,'12345','Ruslan','2020-04-02 19:20:05'),(21,1,0,'hello','Ruslan','2020-04-02 22:01:19'),(22,1,0,'1111','Ruslan','2020-04-02 22:01:30'),(23,1,0,'This is my posts!','Ruslan','2020-04-02 22:09:41'),(24,4,0,'Hello, Ruslan!','example','2020-04-02 22:21:40'),(25,4,0,'How are you?','example','2020-04-02 22:21:50'),(26,4,0,'Hello, example! I\'m very good! How r u?','example','2020-04-02 22:22:47'),(28,4,0,'Great!','example','2020-04-02 22:25:27'),(29,5,0,'Hello, guys!','X-MAN','2020-04-02 23:06:43'),(30,1,0,'Hello, X-MAN','Ruslan','2020-04-02 23:07:13'),(31,1,0,'Hello, X-MAN!','Ruslan','2020-04-02 23:07:48'),(32,5,0,'Hello, Ruslan!','X-MAN','2020-04-02 23:08:08'),(33,5,0,'It works!!!!','X-MAN','2020-04-02 23:08:16'),(34,1,0,'Yeah!!!','Ruslan','2020-04-02 23:08:30'),(35,4,0,'That is really cool!!!','example','2020-04-02 23:09:41'),(36,5,0,'Asdf','X-MAN','2020-04-02 23:13:07'),(37,1,4,'asdf','Ruslan','2020-04-03 15:16:28'),(38,4,1,'hello','example','2020-04-03 15:16:48'),(39,1,0,'123','Ruslan','2020-04-03 15:28:40'),(40,1,4,'asdf','Ruslan','2020-04-03 15:36:10'),(41,1,4,'Cool!','Ruslan','2020-04-03 15:45:20'),(42,1,0,'12345','Ruslan','2020-04-03 15:45:36'),(43,1,0,'It really works!','Ruslan','2020-04-03 15:45:52'),(44,1,5,'Hello, X-MAN!','Ruslan','2020-04-03 15:47:17'),(45,5,1,'Hello, Ruslan!','X-MAN','2020-04-03 15:49:33'),(46,1,5,'qwerty','Ruslan','2020-04-03 15:59:01'),(47,1,4,'','Ruslan','2020-04-03 16:27:03'),(48,1,1,'123','Ruslan','2020-04-03 20:11:42'),(49,1,5,'Hello, X-MAN!','Ruslan','2020-04-03 20:15:49'),(50,5,4,'hello!','X-MAN','2020-04-03 20:18:18'),(51,4,5,'Hello, X-MAN!','example','2020-04-03 20:19:26'),(52,1,4,'Привет!','Ruslan','2020-04-03 20:36:12'),(53,1,4,'Привет!','Ruslan','2020-04-03 20:36:51'),(54,1,4,'','Ruslan','2020-04-03 20:43:10'),(55,1,4,'12345','Ruslan','2020-04-03 20:43:26'),(56,1,4,'asdf','Ruslan','2020-04-03 20:44:08'),(57,1,5,'qwerty','Ruslan','2020-04-03 21:17:52'),(58,1,4,'12345','Ruslan','2020-04-03 21:32:01'),(59,4,4,'123','example','2020-04-04 16:37:44'),(60,4,5,'asdf','example','2020-04-04 16:37:56'),(61,4,5,'1','example','2020-04-04 16:38:17'),(62,1,6,'Hello, Ilya!','Ruslan','2020-04-04 17:03:24'),(63,1,5,'123','Ruslan','2020-04-04 17:03:47'),(64,6,0,'This is my post!','Ilya','2020-04-04 17:09:26'),(65,6,0,'asdf','Ilya','2020-04-04 17:15:23'),(66,6,0,'12345','Ilya','2020-04-04 17:17:37'),(67,6,0,'qwerty','Ilya','2020-04-04 17:21:43'),(68,6,0,'Привет!','Ilya','2020-04-04 17:21:57'),(69,6,1,'asdf','Ilya','2020-04-04 17:22:17'),(70,6,6,'qwerty','Ilya','2020-04-04 17:22:27'),(71,6,1,'qwerty','Ilya','2020-04-04 17:22:53'),(72,6,7,'\'asdf\'','Ilya','2020-04-04 18:07:48'),(73,6,5,'Hello, X-MAN!','Ilya','2020-04-04 18:13:41'),(74,6,4,'qwerty','Ilya','2020-04-04 18:14:13'),(75,8,0,'qwerty','Cool User','2020-04-04 18:39:17'),(76,1,8,'Hello, Cool User!','Ruslan','2020-04-04 19:31:55'),(77,4,0,'asdf','example','2020-04-05 12:32:13'),(78,6,0,'asdf','Ilya','2020-04-05 12:51:27'),(79,6,0,'Hello!','Ilya','2020-04-05 12:59:23'),(80,8,1,'qwerty','Cool User','2020-04-05 13:54:38'),(81,1,6,'Hello!','Ruslan','2020-04-05 14:24:15'),(82,6,0,'12345','Ilya','2020-04-05 19:59:09'),(83,6,8,'asdf','Ilya','2020-04-05 19:59:39'),(84,6,8,'qwerty','Ilya','2020-04-05 20:01:49'),(85,6,1,'12345','Ilya','2020-04-05 20:02:16'),(86,6,8,'12345','Ilya','2020-04-05 20:25:37'),(87,6,8,'Hello!','Ilya','2020-04-07 00:05:38'),(88,8,6,'Hello, Ilya!','Cool User','2020-04-07 00:06:30'),(89,8,6,'Hello, Ilya!','Cool User','2020-04-07 00:06:31'),(90,8,6,'','Cool User','2020-04-07 00:06:34'),(91,8,6,'12345','Cool User','2020-04-07 00:06:43'),(92,8,1,'hfyuklftld','Cool User','2020-04-07 00:12:34'),(93,1,8,'Inteface becomes better! A bit','Ruslan','2020-04-10 21:05:59'),(94,1,6,'More messages!','Ruslan','2020-04-11 13:20:03'),(95,1,6,'I want more messages!','Ruslan','2020-04-11 13:20:21'),(96,4,8,'How are you?','example','2020-04-12 00:56:17'),(97,4,8,'Hello!','example','2020-04-12 00:56:32'),(98,4,6,'Hello, Ilya!','example','2020-04-12 15:37:15'),(99,4,6,'12345','example','2020-04-12 15:39:09'),(100,4,6,'Привет!','example','2020-04-12 15:39:12'),(101,4,6,'qwerty','example','2020-04-12 15:39:17'),(102,4,0,'12345','example','2020-04-12 16:31:13'),(103,4,0,'','example','2020-04-12 16:35:39'),(104,4,0,'asdf','example','2020-04-12 16:36:07'),(105,4,0,'asdf','example','2020-04-12 17:40:03'),(106,4,0,'','example','2020-04-12 17:42:40'),(107,4,0,'asdf','example','2020-04-12 17:47:06'),(108,4,0,'This is first post sended by JS!','example','2020-04-12 17:47:43'),(109,4,0,'Cool!','example','2020-04-12 17:48:13'),(110,4,0,'It works!','example','2020-04-12 17:48:33'),(111,6,0,'Yeeeeah!','Ilya','2020-04-12 17:50:31'),(112,6,5,'123','Ilya','2020-04-12 19:40:01'),(113,6,5,'Hello, X-MAN!','Ilya','2020-04-12 19:40:06'),(114,6,4,'Hello!','Ilya','2020-04-12 19:43:40'),(115,6,4,'How are you?','Ilya','2020-04-12 19:43:49'),(116,4,0,'qwerty','example','2020-04-12 20:01:54'),(117,1,0,'Check!','Ruslan','2020-04-12 20:31:03'),(118,1,0,'Check!','Ruslan','2020-04-12 20:31:06'),(119,1,0,'Check!','Ruslan','2020-04-12 20:31:07'),(120,1,0,'Works!','Ruslan','2020-04-12 20:31:19'),(121,1,0,'asdf','Ruslan','2020-04-12 20:50:38'),(122,4,0,'asdf','example','2020-04-12 21:08:37'),(123,4,0,'asdf','example','2020-04-12 21:08:46'),(124,4,0,'asdf','example','2020-04-12 21:10:34'),(125,4,0,'qwerty','example','2020-04-12 21:10:46'),(126,4,0,'Checking sending messages...','example','2020-04-13 12:33:10'),(127,1,0,'Checking sending messages...','Ruslan','2020-04-13 12:41:29'),(128,1,0,'Checking sending messages...','Ruslan','2020-04-13 12:41:29'),(129,1,0,'Checking sending messages...','Ruslan','2020-04-13 12:42:18'),(130,1,0,'Checking sending messages...','Ruslan','2020-04-13 12:42:18'),(131,1,0,'Check!','Ruslan','2020-04-13 12:44:00'),(132,1,0,'Check!','Ruslan','2020-04-13 12:44:07'),(133,6,0,'Checking sending messages...','Ilya','2020-04-13 12:46:15'),(134,6,0,'Check!','Ilya','2020-04-13 12:46:24'),(135,6,1,'Checking sending messages...','Ilya','2020-04-13 12:46:40'),(136,1,6,'Check!','Ruslan','2020-04-13 12:49:15'),(137,1,6,'','Ruslan','2020-04-13 12:49:30'),(138,1,6,'Привет!','Ruslan','2020-04-13 12:50:35'),(139,1,6,'123','Ruslan','2020-04-13 12:50:43'),(140,1,6,'Check!','Ruslan','2020-04-13 12:57:32'),(141,1,6,'Привет!','Ruslan','2020-04-13 12:57:44'),(142,1,6,'Привет!','Ruslan','2020-04-13 13:06:37'),(143,1,6,'Привет!','Ruslan','2020-04-13 13:06:43'),(144,1,6,'12345','Ruslan','2020-04-13 13:16:51'),(145,1,6,'Привет!','Ruslan','2020-04-13 13:18:38'),(146,1,6,'12345','Ruslan','2020-04-13 13:18:44'),(147,1,6,'qwerty','Ruslan','2020-04-13 13:21:41'),(148,1,6,'Checking sending messages...','Ruslan','2020-04-13 13:21:46'),(149,1,6,'Works!','Ruslan','2020-04-13 13:21:56'),(150,1,0,'asdf','Ruslan','2020-04-13 19:30:05'),(151,1,0,'asdf','Ruslan','2020-04-13 19:31:54'),(152,1,0,'qwerty','Ruslan','2020-04-13 19:33:21'),(153,1,6,'Checking sending messages...','Ruslan','2020-04-13 19:39:22'),(154,1,6,'','Ruslan','2020-04-13 19:39:52'),(155,1,6,'qwerty','Ruslan','2020-04-13 19:39:58'),(156,1,6,'Привет!','Ruslan','2020-04-13 19:40:27'),(157,1,0,'Seems that it works!','Ruslan','2020-04-13 19:44:32'),(158,1,6,'Checking...','Ruslan','2020-04-13 19:45:13'),(159,1,6,'Works!','Ruslan','2020-04-13 19:45:23'),(160,1,7,'Hello!','Ruslan','2020-04-13 19:52:44'),(161,1,7,'Time to send message!','Ruslan','2020-04-13 19:52:59'),(162,1,0,'fdsgrdg','Ruslan','2020-04-13 22:42:58'),(163,1,0,'fghdtshtjkjykugkihl','Ruslan','2020-04-13 22:46:54'),(164,1,0,'Check','Ruslan','2020-04-13 22:49:03'),(165,1,0,'Lalala','Ruslan','2020-04-13 22:49:43'),(166,6,0,'dzvxfbf','Ilya','2020-04-13 22:53:11'),(167,6,1,'Привет!','Ilya','2020-04-14 00:44:28'),(168,1,6,'qwerty','Ruslan','2020-04-14 00:49:36'),(169,1,6,'Привет!','Ruslan','2020-04-14 00:50:26'),(170,6,1,'Check','Ilya','2020-04-14 00:52:14'),(171,1,6,'Hello!','Ruslan','2020-04-14 00:56:08'),(172,6,1,'Hello, Ruslan!','Ilya','2020-04-14 01:02:29'),(173,1,6,'Check!','Ruslan','2020-04-14 01:02:48'),(174,6,1,'Hello, Ruslan!','Ilya','2020-04-14 01:03:07'),(175,6,1,'It works!!!!','Ilya','2020-04-14 01:03:14'),(176,6,1,'Aaaa','Ilya','2020-04-14 01:03:28'),(177,1,6,'Coo','Ruslan','2020-04-14 01:03:36'),(178,1,6,'Cool!','Ruslan','2020-04-14 01:03:40'),(179,1,6,'Yeeeeah!','Ruslan','2020-04-14 01:03:51'),(180,1,6,'123','Ruslan','2020-04-14 01:04:31'),(181,1,6,'Привет!','Ruslan','2020-04-14 01:04:38'),(182,1,6,'asdf','Ruslan','2020-04-14 01:26:52'),(183,1,6,'Привет!','Ruslan','2020-04-14 01:27:20'),(184,6,1,'Hello, Ruslan!','Ilya','2020-04-14 01:27:37'),(185,6,1,'Hello!','Ilya','2020-04-14 01:28:50'),(186,5,1,'Hello!','X-MAN','2020-04-14 01:33:48'),(187,5,1,'Hello, Ruslan!','X-MAN','2020-04-14 01:34:04'),(188,1,5,'Hello, X-MAN!','Ruslan','2020-04-14 01:34:25'),(189,1,5,'123\\','Ruslan','2020-04-14 01:35:17'),(190,1,5,'\"123\"','Ruslan','2020-04-14 01:35:32'),(191,5,1,'Is cool!','X-MAN','2020-04-14 01:36:42'),(192,5,1,'It\'s cool!!!','X-MAN','2020-04-14 01:36:58'),(193,6,1,'Check','Ilya','2020-04-14 01:40:00'),(194,1,6,'Works!','Ruslan','2020-04-14 01:40:13'),(195,6,1,'Hello!','Ilya','2020-04-14 01:41:13'),(196,6,1,'Lalala','Ilya','2020-04-14 01:41:26'),(197,6,1,'Check!','Ilya','2020-04-14 01:42:33'),(198,1,6,'Works!','Ruslan','2020-04-14 01:42:49'),(199,1,6,'Check!','Ruslan','2020-04-14 01:43:02'),(200,6,1,'Cool!','Ilya','2020-04-14 01:43:09'),(203,1,6,'some message','Ruslan','2020-04-14 11:37:44'),(204,6,1,'Hello, Ruslan!','Ilya','2020-04-14 11:39:34'),(205,6,1,'Check','Ilya','2020-04-14 11:44:39'),(206,1,6,'Checking sending messages...','Ruslan','2020-04-14 11:48:34'),(207,1,6,'some message','Ruslan','2020-04-14 11:48:46'),(208,1,6,'Hello, Ilya!','Ruslan','2020-04-14 11:49:02'),(209,1,6,'qwerty','Ruslan','2020-04-14 11:49:20'),(210,1,6,'lalalala','Ruslan','2020-04-14 11:49:25'),(211,6,1,'Hello!','Ilya','2020-04-14 11:50:42'),(212,6,1,'Check','Ilya','2020-04-14 11:51:05'),(213,6,1,'Check1','Ilya','2020-04-14 11:51:26'),(214,6,1,'Check2','Ilya','2020-04-14 11:51:31'),(215,6,1,'Check3','Ilya','2020-04-14 11:51:36'),(216,6,1,'Check4','Ilya','2020-04-14 11:51:41'),(217,6,1,'Check5','Ilya','2020-04-14 11:51:48'),(218,6,1,'Check6','Ilya','2020-04-14 11:51:52'),(219,6,1,'Check7','Ilya','2020-04-14 11:51:57'),(220,6,1,'Check8','Ilya','2020-04-14 11:52:03'),(221,6,1,'Check11','Ilya','2020-04-14 11:52:34'),(222,6,1,'Check12','Ilya','2020-04-14 11:52:40'),(223,6,1,'Check13','Ilya','2020-04-14 11:52:44'),(224,6,1,'Check14','Ilya','2020-04-14 11:52:54'),(225,6,1,'Check','Ilya','2020-04-14 11:53:13'),(226,6,1,'Hello!','Ilya','2020-04-14 11:53:16'),(227,6,1,'Check!','Ilya','2020-04-14 11:53:19'),(228,6,1,'Checking...','Ilya','2020-04-14 11:53:24'),(229,6,1,'Checking sending messages...','Ilya','2020-04-14 11:53:26'),(230,6,1,'Hello, Ilya!','Ilya','2020-04-14 11:53:37'),(231,6,1,'Check!','Ilya','2020-04-14 11:53:41'),(232,6,1,'Checking...','Ilya','2020-04-14 11:53:43'),(233,6,1,'Checking sending messages...','Ilya','2020-04-14 11:53:57'),(234,6,1,'Checking...','Ilya','2020-04-14 11:54:00'),(235,6,1,'Hello, Ilya!','Ilya','2020-04-14 11:54:04'),(236,6,1,'Hello!','Ilya','2020-04-14 11:54:07'),(237,6,1,'Hello, Ruslan!','Ilya','2020-04-14 11:54:11'),(238,6,1,'Hello!','Ilya','2020-04-14 11:54:15'),(239,6,1,'Check!','Ilya','2020-04-14 11:54:18'),(240,6,1,'Checking...','Ilya','2020-04-14 11:54:21'),(241,6,1,'Hello, X-MAN!','Ilya','2020-04-14 11:54:23'),(242,6,1,'Asdf','Ilya','2020-04-14 11:54:27'),(243,6,1,'qwerty','Ilya','2020-04-14 11:54:29'),(244,6,1,'Check','Ilya','2020-04-14 11:54:33'),(245,6,1,'Checking sending messages...','Ilya','2020-04-14 11:54:35'),(246,6,1,'Check!','Ilya','2020-04-14 11:54:38'),(247,1,0,'Check','Ruslan','2020-04-14 12:35:58'),(248,1,0,'asdf','Ruslan','2020-04-14 12:36:06'),(249,1,0,'Check!','Ruslan','2020-04-14 12:47:11'),(250,1,6,'Hello, Ilya!','Ruslan','2020-04-14 19:23:13'),(251,1,6,'Hello!','Ruslan','2020-04-14 19:23:21'),(252,1,6,'How are you?','Ruslan','2020-04-14 19:23:26'),(253,1,6,'It works!','Ruslan','2020-04-14 19:23:47'),(254,1,0,'hjgvjkg','Ruslan','2020-04-14 20:04:58'),(255,1,0,'Check!','Ruslan','2020-04-14 21:35:18'),(256,1,0,'Works!','Ruslan','2020-04-14 21:35:24'),(257,1,0,'asdf','Ruslan','2020-04-15 13:38:49'),(258,1,0,'More messages!','Ruslan','2020-04-15 13:41:06'),(259,1,0,'More messages!','Ruslan','2020-04-15 13:42:17'),(260,1,0,'qwerty','Ruslan','2020-04-15 13:43:19'),(261,1,0,'Hello!','Ruslan','2020-04-15 13:47:35'),(262,1,0,'Hello, everyone!!!!','Ruslan','2020-04-15 13:48:03'),(263,1,0,'I\'m here!!!','Ruslan','2020-04-15 13:48:15'),(264,1,0,'This is my messages!!!','Ruslan','2020-04-15 13:48:28'),(265,1,0,'More','Ruslan','2020-04-15 13:48:32'),(266,1,0,'More!','Ruslan','2020-04-15 13:48:37'),(267,1,0,'More messages!!!!','Ruslan','2020-04-15 13:48:43'),(268,1,0,'Yes!','Ruslan','2020-04-15 13:49:00'),(269,1,0,'I\'ts enough!','Ruslan','2020-04-15 13:49:19'),(270,6,1,'Check','Ilya','2020-04-15 14:49:00'),(271,6,1,'Works!','Ilya','2020-04-15 14:49:06'),(272,1,6,'Hello!','Ruslan','2020-04-15 18:03:20'),(273,1,6,'Check!','Ruslan','2020-04-15 19:43:06'),(274,6,1,'Hi!','Ilya','2020-04-15 22:44:43'),(275,1,6,'Hello, Ilya!','Ruslan','2020-04-15 22:46:35'),(276,1,6,'Check!','Ruslan','2020-04-15 22:46:56'),(277,1,6,'Checking sending messages...','Ruslan','2020-04-15 22:47:08'),(278,1,6,'Asdf','Ruslan','2020-04-15 22:47:17'),(279,1,6,'qwerty','Ruslan','2020-04-15 22:47:22'),(280,1,6,'Qwerty','Ruslan','2020-04-15 22:47:26'),(281,1,6,'Hello, Ilya!','Ruslan','2020-04-16 00:20:56'),(282,1,0,'Hello!','Ruslan','2020-04-16 18:03:57'),(283,1,0,'Works!','Ruslan','2020-04-16 18:04:06'),(284,1,0,'Hello!','Ruslan','2020-04-17 00:00:26'),(285,7,8,'123','ex\'123','2020-04-19 14:28:34'),(286,7,5,'123','ex\'123','2020-04-19 14:30:22'),(287,5,8,'Hello, Cool User!','X-MAN','2020-04-19 15:10:16'),(288,1,5,'Hello, X-MAN!','Ruslan','2020-04-19 19:08:48'),(289,1,7,'Привет!','Ruslan','2020-04-19 19:09:14'),(290,1,4,'Hello!','Ruslan','2020-04-19 19:09:26'),(291,1,6,'1','Ruslan','2020-04-19 21:06:41'),(292,1,6,'123','Ruslan','2020-04-19 21:06:48'),(293,1,6,'Check','Ruslan','2020-04-20 00:13:40'),(294,1,6,'Hello!','Ruslan','2020-04-20 11:37:14'),(295,1,6,'Hello, Ilya!','Ruslan','2020-04-20 11:37:47'),(296,1,6,'123','Ruslan','2020-04-20 11:38:14'),(297,1,4,'init','Ruslan','2020-04-20 11:39:13'),(298,1,7,'aaaa','Ruslan','2020-04-20 11:39:30'),(299,1,5,'111','Ruslan','2020-04-20 11:39:43'),(300,1,8,'1','Ruslan','2020-04-20 11:39:52'),(301,6,1,'222','Ilya','2020-04-20 11:41:06'),(302,6,4,'1','Ilya','2020-04-20 11:43:00'),(303,6,5,'1','Ilya','2020-04-20 11:43:12'),(304,6,8,'1','Ilya','2020-04-20 11:43:25'),(305,6,7,'1','Ilya','2020-04-20 11:43:33'),(306,4,8,'1','example','2020-04-20 11:46:19'),(307,4,5,'123','example','2020-04-20 11:46:34'),(308,8,5,'1','Cool User','2020-04-20 11:47:08'),(309,8,7,'1','Cool User','2020-04-20 11:47:15'),(310,7,5,'1','ex\'123','2020-04-20 11:49:01'),(311,1,1,'1234','Ruslan','2020-04-20 12:07:21'),(312,1,7,'1','Ruslan','2020-04-20 12:07:29'),(313,1,6,'1','Ruslan','2020-04-20 12:10:34'),(314,1,6,'fefsgr','Ruslan','2020-04-20 14:36:16'),(315,1,6,'dghudorg','Ruslan','2020-04-20 14:37:39'),(316,1,6,'helllo!','Ruslan','2020-04-20 14:37:44'),(317,1,6,'111','Ruslan','2020-04-20 14:42:56'),(318,1,6,'qwerty','Ruslan','2020-04-20 14:44:06');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `validate` tinyint NOT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ruslanzinewich@gmail.com','2020-04-01 19:21:27','Ruslan',1,'$2a$10$dHbYbb.nNHUqg5zEZuxYseTyQ.Uoy54a3O9Nqhb38zWlih6gKN0ni',NULL),(4,'example@example.com','2020-04-01 19:53:50','example',1,'$2a$10$gNiEPc8chdO0e7CTOFQSluP6wYHn/KxiaepnVekMEk87f/UxKurY6',NULL),(5,'xman@xxx.com','2020-04-02 23:05:55','X-MAN',1,'$2a$10$KF7wAlzXE8YUibcHGrTcP.rWIJD0eIYRy3Z7qAAiAkjQevbz0uaY6',NULL),(6,'spad20@yandex.ru','2020-04-03 23:06:04','Ilya',1,'$2a$10$veNR30nfCgpC4/bjL/dccO8Sfhprj.XZPuKmPT9R3Fq/CsCsiR0Xy',NULL),(7,'ex@ex.com','2020-04-04 18:07:01','ex\'123',1,'$2a$10$qNdcZeiPnyB6Vcg2qNqyd.mLFJ6HRnCO6G2bqSECDpE8gruSZXZKy',NULL),(8,'cool@cool.com','2020-04-04 18:36:48','Cool User',1,'$2a$10$NxVnXE.u7cLEJhOfHLUeju0S//WThUGNHI8u8MRIn1YZ.PG3rJQ7e',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-20 17:55:20
