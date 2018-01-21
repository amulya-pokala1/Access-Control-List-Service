-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: acl
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(45) NOT NULL,
  `PASSKEY` varchar(512) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `mail_id` varchar(254) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `mail_id_UNIQUE` (`mail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (4,'tarun1',NULL,'an admin','tarun123622@gmail.com'),(5,'tarun1',NULL,'an admin','taru123622@gmail.com'),(13,'adf',NULL,NULL,'malapatisaiteja@gmail.com'),(14,'abi','f1029c1e3b2c1de0e00e4c2515a037f84f3d8b50c133f39ea1dd55043871161884e0f0f19d42631edb61f1f5cf7d4302495aca2913e32c9125d1e8ea48a9ba45',NULL,'tarundreams95@gmail.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_password_uri`
--

DROP TABLE IF EXISTS `admin_password_uri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_password_uri` (
  `ADMIN_ID` int(11) DEFAULT NULL,
  `URI` varchar(512) NOT NULL,
  KEY `f_admin_idx` (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_password_uri`
--

LOCK TABLES `admin_password_uri` WRITE;
/*!40000 ALTER TABLE `admin_password_uri` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_password_uri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) NOT NULL,
  `group_description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name_UNIQUE` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=348 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (4,'READ2341','CAN READ234'),(5,'READ23412','CAN READ234'),(6,'READ234123','CAN READ234');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_permission`
--

DROP TABLE IF EXISTS `group_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_permission` (
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`permission_id`),
  KEY `F_PERMISSION_ID_idx` (`permission_id`),
  CONSTRAINT `F_GROUP_ID` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `F_PERMISSION_ID` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`PERMISSION_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_permission`
--

LOCK TABLES `group_permission` WRITE;
/*!40000 ALTER TABLE `group_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION_NAME` varchar(30) NOT NULL,
  `PERMISSION_DESCRIPTION` varchar(100) DEFAULT NULL,
  `IS_MANDATORY` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`PERMISSION_ID`),
  UNIQUE KEY `PERMISSION_NAME_UNIQUE` (`PERMISSION_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'READ','CAN READ',0),(2,'READ1','CAN READ1',0),(3,'READ2','CAN READ2',0),(4,'WRITE','CAN WRITE',1),(7,'READ234','CAN READ234',0);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `PASSKEY` varchar(512) DEFAULT NULL,
  `mail_id` varchar(254) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mail_id_UNIQUE` (`mail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'tarun1',NULL,'tarun123@gmail.com'),(10,'tarun1',NULL,'tarun1236@gmail.com'),(11,'tarun1',NULL,'tarun12362@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `F_GROUP_ID1_idx` (`group_id`),
  CONSTRAINT `F_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_password_uri`
--

DROP TABLE IF EXISTS `user_password_uri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_password_uri` (
  `USER_ID` int(11) DEFAULT NULL,
  `URI` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_password_uri`
--

LOCK TABLES `user_password_uri` WRITE;
/*!40000 ALTER TABLE `user_password_uri` DISABLE KEYS */;
INSERT INTO `user_password_uri` VALUES (312,'97d7df8e4d06c2f578f785ff0286059d0f4771506a72802e789e561361d0c4bf2c68d6c33d96850bbef603a71bb5f5a99798fe9c77f7896f0533c98d2d6c69e6'),(313,'8c61be3b11e75e4e0adb08a8ecd7f7273917c35c02dd0665cf4be348e19c7020d3c4d907f70f456f4f2a3439b3d3df2bfd2fa510fb36d7b393d649e2b127931f'),(314,'ec3cecece2f0de5cfe2b08f9cb37ab8e376a06fe05064b3f237fcc2695c771ba44ea6dd51d0f681f2cafe7f865b8582cfb4965d67e15f05d147e79ccb2d69123'),(315,'7234c9bae4819d9594bfb6f2b0c173409fac55e29c2df5f48d00a3babb90cf529b296a444f571de4e879d658b9c284f748dd0ca08af897333cef192f2be3fdb4'),(316,'d9e59fa9fb6d0b22b5a886f72c54ad3c4d3ff28d092a72c749b3a2fed2ea25967b6c91b4770bcfb6b7bb0a8265145dd150c90475b6530012921ed4a759644ade'),(317,'72095d13250aff3fdd4a953206c3a67578e1fab19dd64758cce55f14fef2c1bcec6e7456c1009996e7546dfb184f8be46b74c65aa0915fa00afad99ef4ea5129'),(318,'644cd96bc37e5b92899ec848bbc96faa1a271a04db7e771bf1848aeeb69ec98f5d236c25a5101bc3bc0cb7f3a717afe1c79c58805b8272207225a6f33e768880'),(327,'2ac40a68c1325bdb6653f6a3446e6e2e5175b8473d2e270632b28d7084cc26c4556c9814c3bb020341494f925a80e1bcaae11c2fbf68b7e5b8e3364b426cd87e'),(335,'5d399ecaba91ec4ad499dcb7241cb144969247d215631349c15ee48039561bbeeb7c9ad080c1863bd23d4b09a5d5511e3df4bdbe821e461364b2bcc8c52d294b');
/*!40000 ALTER TABLE `user_password_uri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`permission_id`),
  KEY `F_PERMISSION_ID1_idx` (`permission_id`),
  CONSTRAINT `F_PERMISSION_ID1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`PERMISSION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `F_USER_ID1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'acl'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-22  0:18:37
