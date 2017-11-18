-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pc_reservation
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
  `locationID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `building` varchar(45) NOT NULL,
  `isAvailable` tinyint(4) NOT NULL,
  `computers` tinyblob,
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'Henry Sy - 6th Floor','Henry Sy',0,NULL),(2,'Henry Sy - 7th Floor','Henry Sy',0,NULL),(3,'Henry Sy - 8th Floor','Henry Sy',1,NULL),(4,'Gokongwei Lab - G301A','Gokongwei',0,NULL),(5,'Gokongwei Lab - G302B','Gokongwei',1,NULL),(6,'Gokongwei Lab - G301B','Gokongwei',0,NULL),(7,'Gokongwei Lab - G302A','Gokongwei',1,NULL),(8,'Henry Sy - 9th Floor','Henry Sy',1,NULL),(9,'Henry Sy - 10th Floor','Henry Sy',1,NULL),(10,'Velasco Lab - V103','Velasco',1,NULL),(11,'Velasco Lab - V208A','Velasco',1,NULL),(12,'Velasco Lab - V208B','Velasco',1,NULL),(13,'Velasco Lab - V301','Velasco',1,NULL);
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc_info`
--

DROP TABLE IF EXISTS `pc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_info` (
  `pcID` int(11) NOT NULL,
  `locationID` int(11) NOT NULL,
  `isAvailable` tinyint(4) NOT NULL,
  PRIMARY KEY (`pcID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
-- Table structure for table `pc_reservations`
--

DROP TABLE IF EXISTS `pc_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_reservations` (
  `borrowID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `pcID` int(11) NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  `dateTimeEnd` datetime NOT NULL,
  `checkInTime` datetime NOT NULL,
  `reserveTime` datetime NOT NULL,
  `adminConfirmed` tinyint(4) NOT NULL,
  `eventName` varchar(45) NOT NULL,
  PRIMARY KEY (`borrowID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_reservations`
--

LOCK TABLES `pc_reservations` WRITE;
/*!40000 ALTER TABLE `pc_reservations` DISABLE KEYS */;
INSERT INTO `pc_reservations` VALUES (1,10912312,2,'2017-11-15 08:00:00','2017-11-15 09:00:00','9999-09-09 09:09:09','2017-11-15 06:30:00',0,'None'),(2,11634521,3,'2017-11-15 08:00:00','2017-11-15 09:00:00','9999-09-09 09:09:09','2017-11-15 06:30:00',0,'None'),(3,11534567,2,'2017-11-15 08:00:00','2017-11-15 09:00:00','9999-09-09 09:09:09','2017-11-15 07:30:00',0,'None'),(4,11265423,5,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-15 07:00:00',0,'None'),(5,11412345,2,'2017-11-15 10:00:00','2017-11-15 11:00:00','9999-09-09 09:09:09','2017-11-15 07:00:00',0,'None'),(6,11324215,13,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-16 07:00:00',0,'None'),(7,11425232,11,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-16 06:00:00',0,'None'),(8,11012344,30,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-16 06:00:00',0,'GitHub Seminar'),(9,11123451,30,'2017-11-16 09:00:00','2017-11-16 10:00:00','9999-09-09 09:09:09','2017-11-16 07:00:00',0,'SOFENGG Seminar'),(10,11251234,30,'2017-11-16 12:00:00','2017-11-16 13:00:00','9999-09-09 09:09:09','2017-11-16 10:00:00',0,'Marketing Seminar'),(11,11710821,36,'2017-11-16 08:00:00','2017-11-16 09:00:00','9999-09-09 09:09:09','2017-11-16 07:00:00',0,'None'),(12,11719191,48,'2017-11-16 12:00:00','2017-11-16 13:00:00','9999-09-09 09:09:09','2017-11-16 06:00:00',0,'None'),(13,11726351,50,'2017-11-16 12:00:00','2017-11-16 13:00:00','9999-09-09 09:09:09','2017-11-16 07:00:00',0,'None');
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
  `course/college` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (11265423,'AB-HIS/CLA','Graduate'),(11324215,'BSCS/CCS','Graduate'),(11412345,'BSED-BIO/COE','Undergraduate'),(11425232,'BSIT/CCS','Undergraduate'),(11534567,'AB-LIT/CLA','Undergraduate'),(11634521,'BSA/COB','Undergraduate');
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
INSERT INTO `user_info` VALUES (10823512,'Tess Poñei','Iloveicecream_9','admin',NULL),(10912312,'Heverly Bien','Bienhev11*','faculty',NULL),(10923123,'Heinrich Lein','Heinlein_109','admin',NULL),(11012344,'Anna Bell','Bellanna_1','faculty',NULL),(11023124,'Larry Port','Larryport110*','admin',NULL),(11123451,'Nadroj Ajed','Nadrojedi12!','faculty',NULL),(11145342,'Perry Mon','dlsuemailpass123.','admin',NULL),(11251234,'Gary Go','Gary_dlsu123','faculty',NULL),(11265423,'Ellie Gomez','Gomellie_12','student',NULL),(11322213,'Han Cort','Hancortdlsu11!','faculty',NULL),(11324215,'Adam Tom','adam1_12_123!','student',NULL),(11412345,'John Ferry','123456abc!','student',NULL),(11425232,'Hagrid Henry','H@grid12','student',NULL),(11490901,'Gaynelle Grady','Gradnelle007!','student',NULL),(11509152,'Cassie Troy','TroyCass09!','student',NULL),(11534567,'Mark Martinez','Hello123!','student',NULL),(11542311,'Lambert Alai','Lambertdlsu152!','faculty',NULL),(11611223,'Mitzi Frasier','FraMi116!','student',NULL),(11624523,'Jacklyn Pulido','Pulijack00%','student',NULL),(11634521,'Monique Lauff','Monlauff116*','student',NULL),(11710821,'Mallory Spivey','Spiderfan@11','student',NULL),(11712345,'Savanna Sisson','Sisson_123','student',NULL),(11719191,'Vena Burchfeld','Vburch117!','student',NULL),(11726351,'Claudette Morrow','Claudow23!','student',NULL);
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

-- Dump completed on 2017-11-19  0:05:34
