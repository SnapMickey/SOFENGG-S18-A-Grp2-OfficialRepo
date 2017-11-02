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
  `slotsRemaining` int(11) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (10912312,'Chemistry',3),(11012344,'History',3),(11123451,'Software Technology',3),(11251234,'Marketing',3),(11322213,'Chem. Engineering',3),(11542311,'Mech. Engineering',3);
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
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'Henry Sy - 6th Floor','Henry Sy',0),(2,'Henry Sy - 7th Floor','Henry Sy',0),(3,'Henry Sy - 8th Floor','Henry Sy',0),(4,'Gokongwei Lab - G301A','Gokongwei',0),(5,'Gokongwei Lab - G302B','Gokongwei',1),(6,'Gokongwei Lab - G301B','Gokongwei',0),(7,'Gokongwei Lab - G302A','Gokongwei',1);
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
INSERT INTO `pc_info` VALUES (1,1,0),(2,1,1),(3,1,1),(4,1,0),(5,1,1),(6,2,1),(7,2,0),(8,2,1),(9,2,0),(10,2,1),(11,2,1),(12,2,1),(13,2,1);
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
  `eConfirmTime` datetime NOT NULL,
  `emailConfirmed` tinyint(4) NOT NULL,
  `adminConfirmed` tinyint(4) NOT NULL,
  PRIMARY KEY (`borrowID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_reservations`
--

LOCK TABLES `pc_reservations` WRITE;
/*!40000 ALTER TABLE `pc_reservations` DISABLE KEYS */;
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
  `blacklistDays` int(11) NOT NULL,
  `slotsRemaining` int(11) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (11265423,'AB-HIS/CLA','Graduate',0,3),(11324215,'BSCS/CCS','Graduate',0,3),(11412345,'BSED-BIO/COE','Undergraduate',2,3),(11425232,'BSIT/CCS','Undergraduate',0,3),(11534567,'AB-LIT/CLA','Undergraduate',0,3),(11634521,'BSA/COB','Undergraduate',4,3);
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
  `email` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (10823512,'Tess Poñei','Iloveicecream_9','tess_poñei@dlsu.edu.ph','admin'),(10912312,'Heverly Bien','Bienhev11*','heverly_bien@dlsu.edu.ph','faculty'),(10923123,'Heinrich Lein','Heinlein_109','heinrich_lein@dlsu.edu.ph','admin'),(11012344,'Anna Bell','Bellanna_1','anna_bell@dlsu.edu.ph','faculty'),(11023124,'Larry Port','Larryport110*','larry_port@dlsu.edu.ph','admin'),(11123451,'Nadroj Ajed','Nadrojedi12!','nadroj_ajed@dlsu.edu.ph','faculty'),(11145342,'Perry Mon','dlsuemailpass123.','perry_mon@dlsu.edu.ph','admin'),(11251234,'Gary Go','Gary_dlsu123','gary_go@dlsu.edu.ph','faculty'),(11265423,'Ellie Gomez','Gomellie_12','ellie_gomez@dlsu.edu.ph','student'),(11322213,'Han Cort','Hancortdlsu11!','han_cort@dlsu.edu.ph','faculty'),(11324215,'Adam Tom','adam1_12_123!','adam_tom@dlsu.edu.ph','student'),(11412345,'John Ferry','123456abc!','john_ferry@dlsu.edu.ph','student'),(11425232,'Hagrid Henry','H@grid12','hagrid_henry@dlsu.edu.ph','student'),(11534567,'Mark Martinez','Hello123!','mark_martinez@dlsu.edu.ph','student'),(11542311,'Lambert Alai','Lambertdlsu152!','lambert_alai@dlsu.edu.ph','faculty'),(11634521,'Monique Lauff','Monlauff116*','monique_lauff@dlsu.edu.ph','student');
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

-- Dump completed on 2017-11-02 15:37:36
