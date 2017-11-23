-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: pc_reserve
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `userID` int(11) NOT NULL,
  `faculty` varchar(45) NOT NULL,
  `college` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (10912312,'Chemistry','COS'),(11012344,'History','CLA'),(11123451,'Software Technology','CCS'),(11251234,'Marketing','COB'),(11322213,'Chem. Engineering','COS'),(11542311,'Mech. Engineering','GCOE');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `locationID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `building` varchar(45) NOT NULL,
  `isAvailable` tinyint(4) NOT NULL,
  `computers` tinyblob,
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'Henry Sy - 6th Floor','Henry Sy',0,NULL),(2,'Henry Sy - 7th Floor','Henry Sy',0,NULL),(3,'Henry Sy - 8th Floor','Henry Sy',1,NULL),(4,'Gokongwei Lab - G301','Gokongwei',0,NULL),(5,'Gokongwei Lab - G302B','Gokongwei',1,NULL),(6,'Gokongwei Lab - G302A','Gokongwei',1,NULL),(7,'Henry Sy - 9th Floor','Henry Sy',1,NULL),(8,'Henry Sy - 10th Floor','Henry Sy',1,NULL),(9,'Velasco Lab - V103','Velasco',1,NULL),(10,'Velasco Lab - V208A','Velasco',1,NULL),(11,'Velasco Lab - V208B','Velasco',1,NULL),(12,'Velasco Lab - V301','Velasco',1,NULL),(13,'Miguel Lab - M303','Miguel',1,NULL),(14,'Gokongwei - G304A','Gokongwei',1,NULL),(15,'Gokongwei - G304B','Gokongwei',0,NULL),(16,'Gokongwei - G404','Gokongwei',0,NULL);
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_reservations`
--

DROP TABLE IF EXISTS `lab_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_reservations` (
  `labReservationID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `locationID` int(11) NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  `dateTimeEnd` datetime NOT NULL,
  `reservetime` datetime NOT NULL,
  `checkintime` datetime NOT NULL DEFAULT '9999-09-09 09:09:09',
  `adminconfirmed` tinyint(4) NOT NULL DEFAULT '0',
  `eventName` varchar(45) NOT NULL,
  PRIMARY KEY (`labReservationID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_reservations`
--

LOCK TABLES `lab_reservations` WRITE;
/*!40000 ALTER TABLE `lab_reservations` DISABLE KEYS */;
INSERT INTO `lab_reservations` VALUES (1,11012344,3,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-16 06:00:00',0,'GitHub Seminar'),(2,11123451,3,'2017-11-16 09:00:00','2017-11-16 10:00:00','9999-09-09 09:09:09','2017-11-16 07:00:00',0,'SOFENGG Seminar'),(3,11251234,3,'2017-11-16 12:00:00','2017-11-16 13:00:00','9999-09-09 09:09:09','2017-11-16 10:00:00',0,'Marketing Seminar');
/*!40000 ALTER TABLE `lab_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc_info`
--

DROP TABLE IF EXISTS `pc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_info` (
  `pcID` int(11) NOT NULL AUTO_INCREMENT,
  `locationID` int(11) NOT NULL,
  `isAvailable` tinyint(4) NOT NULL,
  PRIMARY KEY (`pcID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_info`
--

LOCK TABLES `pc_info` WRITE;
/*!40000 ALTER TABLE `pc_info` DISABLE KEYS */;
INSERT INTO `pc_info` VALUES (1,1,0),(2,1,1),(3,1,1),(4,1,0),(5,1,1),(6,2,1),(7,2,0),(8,2,1),(9,2,0),(10,2,1),(11,2,1),(12,2,1),(13,2,1),(14,5,1),(15,5,1),(16,5,1),(17,5,1),(18,7,1),(19,7,1),(20,7,0),(21,7,1),(22,8,1),(23,8,1),(24,8,0),(25,8,0),(26,9,1),(27,9,1),(28,9,1),(29,9,1),(30,3,1),(31,3,1),(32,3,1),(33,3,1),(34,10,1),(35,10,1),(36,10,1),(37,10,1),(38,11,1),(39,11,1),(40,11,1),(41,11,1),(42,11,1),(43,12,1),(44,12,1),(45,12,1),(46,12,1),(47,13,1),(48,13,1),(49,13,1),(50,13,1),(51,13,1);
/*!40000 ALTER TABLE `pc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc_reservation`
--

DROP TABLE IF EXISTS `pc_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_reservation` (
  `borrowId` int(11) NOT NULL,
  `adminConfirmed` bit(1) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `dateTimeEnd` datetime DEFAULT NULL,
  `dateTimeStart` datetime DEFAULT NULL,
  `eConfirmedTime` datetime DEFAULT NULL,
  `emailConfirmed` bit(1) DEFAULT NULL,
  `pcID` int(11) DEFAULT NULL,
  `reserveTime` datetime DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`borrowId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_reservation`
--

LOCK TABLES `pc_reservation` WRITE;
/*!40000 ALTER TABLE `pc_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `pc_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc_reservations`
--

DROP TABLE IF EXISTS `pc_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_reservations` (
  `pcReservationsID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `pcID` int(11) NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  `dateTimeEnd` datetime NOT NULL,
  `checkInTime` datetime NOT NULL DEFAULT '9999-09-09 09:09:09',
  `reserveTime` datetime NOT NULL,
  `adminConfirmed` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pcReservationsID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_reservations`
--

LOCK TABLES `pc_reservations` WRITE;
/*!40000 ALTER TABLE `pc_reservations` DISABLE KEYS */;
INSERT INTO `pc_reservations` VALUES (1,11324215,17,'2017-11-23 08:00:00','2017-11-23 09:00:00','9999-09-09 09:09:09','2017-11-23 09:58:24',0);
/*!40000 ALTER TABLE `pc_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics` (
  `pcID` int(11) NOT NULL,
  `location` varchar(45) NOT NULL,
  `college` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `dateTimeReserved` datetime NOT NULL,
  `dateTimeUsed` datetime NOT NULL,
  `isUsed` tinyint(4) NOT NULL,
  PRIMARY KEY (`pcID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `userID` int(11) NOT NULL,
  `college` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (11265423,'CLA','Graduate'),(11324215,'CCS','Graduate'),(11412345,'COE','Undergraduate'),(11425232,'CCS','Undergraduate'),(11490901,'COS','Graduate'),(11509152,'COS','Undergraduate'),(11534567,'CLA','Undergraduate'),(11611223,'GCOE','Undergraduate'),(11624523,'CCS','Undergraduate'),(11634521,'COB','Undergraduate'),(11710821,'GCOE','Undergraduate'),(11712345,'GCOE','Undergraduate'),(11719191,'COB','Undergraduate'),(11726351,'COB','Undergraduate');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `userID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (10823512,'Tess Poñei','41ba5c4c4504371e0a705dec14104a3bc7f9950a','admin',NULL),(10912312,'Heverly Bien','af45e213b424099accdb1053fc7814e9d0124fcb','faculty',NULL),(10923123,'Heinrich Lein','5524697760ab25bb46c49f8b766ccd192605c4b7','admin',NULL),(11012344,'Anna Bell','08ec4ef982206ea87a6edfccdc2965fe7d836b1e','faculty',NULL),(11023124,'Larry Port','83152888382cef461668e01446e28b855bf2c2f5','admin',NULL),(11123451,'Nadroj Ajed','80147532ec238bfa646fe78132076e9c51da8ebc','faculty',NULL),(11145342,'Perry Mon','a8900d17be323797f23943535ea292f5b588c153','admin',NULL),(11251234,'Gary Go','b2312f5196cce37ef273bbe82f867d51f1ed4b98','faculty',NULL),(11265423,'Ellie Gomez','4bcec676e009f9fa5a7148cb66b1db4e75bc5912','student',NULL),(11322213,'Han Cort','f3f4b09c783ad281be8f5a984f3bf3d058ceed83','faculty',NULL),(11324215,'Adam Tom','5585350e695466a3f990b48bc38a748fc8b34b87','student',NULL),(11412345,'John Ferry','991dd29818ae767f8b5cd4378ce613225c3bd34c','student',NULL),(11425232,'Hagrid Henry','9367998c487aa9bce3a4db7d274c357c8d0cee84','student',NULL),(11490901,'Gaynelle Grady','4b599999691da3264f6a80ab326c444be676f644','student',NULL),(11509152,'Cassie Troy','0e50ff6d23bd7269d711f85068ec833555dc0ebb','student',NULL),(11534567,'Mark Martinez','b66a5337cc0d5f1a5466ed96fd125396c0dd24e6','student',NULL),(11542311,'Lambert Alai','9df0bce4356320076dce0cc8acddaa619110c4d3','faculty',NULL),(11611223,'Mitzi Frasier','4d8ebeadd66787a535a01334f0e2b6180eb2ff3e','student',NULL),(11624523,'Jacklyn Pulido','315d070a4d2add96284c74cb07480ae29c6bab4d','student',NULL),(11634521,'Monique Lauff','4decd9e0a59443eac3f8e0198b1f25bcac8b38f6','student',NULL),(11710821,'Mallory Spivey','38345ad3415e66fda5860920b45c69cfc15dc682','student',NULL),(11712345,'Savanna Sisson','10fa250fde2127256843abb9826fcf6409104c17','student',NULL),(11719191,'Vena Burchfeld','25f743c776cd42fd4bc4cbe3e1e3ec9515b6c2d7','student',NULL),(11726351,'Claudette Morrow','dcbe2bc5eb76535090ff3417514ec6c24f8d00d2','student',NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-23 10:05:57
