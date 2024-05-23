CREATE DATABASE  IF NOT EXISTS `cabinetmedical` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cabinetmedical`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: cabinetmedical
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation` (
  `resultats` varchar(10000) DEFAULT NULL,
  `ordonnance` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `prix` int DEFAULT NULL,
  `Patient ID` int DEFAULT NULL,
  KEY `Patient ID_idx` (`Patient ID`),
  CONSTRAINT `Patient ID` FOREIGN KEY (`Patient ID`) REFERENCES `patient` (`Patient ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation`
--

LOCK TABLES `consultation` WRITE;
/*!40000 ALTER TABLE `consultation` DISABLE KEYS */;
INSERT INTO `consultation` VALUES ('rak birit wlidou','achrob les vitamines','2024-05-05',2000,3),('rak birit wlidou','achrob les vitamines','2024-05-05',2000,3),('raki mrida bzef bzef','achorbi zitzitoun','2024-05-10',20000,10),('rien trouvé','boire les vitamine','2024-05-15',2500,11),('hfdbef','dbdvgy','2024-05-07',5456,3);
/*!40000 ALTER TABLE `consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `createcompte`
--

DROP TABLE IF EXISTS `createcompte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `createcompte` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nomutilisateur` varchar(45) DEFAULT NULL,
  `Motdepasse` int DEFAULT NULL,
  `TypeUtilisateur` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Nomutilisateur_UNIQUE` (`Nomutilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `createcompte`
--

LOCK TABLES `createcompte` WRITE;
/*!40000 ALTER TABLE `createcompte` DISABLE KEYS */;
INSERT INTO `createcompte` VALUES (1,'test',1235,'Medecin'),(2,'test2',1223,'Medecin'),(3,'test3',123,'Medecin'),(4,'test4',123,'Medecin'),(5,'testt4',123,'Medecin'),(6,'tststs',12,'Medecin'),(7,'please',123,'Medecin'),(9,'test5',123,'Medecin'),(10,'test6',123,'Medecin'),(11,'tesrrrr',66,'Medecin'),(12,'num1',123,'Medecin'),(13,'oookk',123,'Medecin'),(14,'PLEASSE',123,'Medecin'),(15,'oom',123,'Medecin'),(16,'marche',123,'Medecin'),(17,'efff',123,'Medecin'),(18,'marchhhhhhhhhhhhe',1234,'Medecin'),(19,'ii',12,'Medecin'),(21,'XS',123,'Medecin'),(23,'good',123,'Medecin'),(26,'izan',12,'Medecin'),(27,'last',1244,'Medecin'),(28,'LYDIA123',1234,'Medecin'),(29,'LIDI',123,'Secraitaire'),(30,'lidi1',1234,'Secraitaire'),(31,'HOPE',1234,'Medecin'),(32,'hp',123,'Secraitaire'),(33,'A',123,'Secraitaire'),(34,'karima',123,'Secraitaire'),(35,'karim',1234,'Secraitaire'),(36,'malek',1234,'Medecin'),(37,'youcef',123,'Secraitaire'),(38,'ENZO',123,'Secraitaire'),(39,'kokooo',1223,'Medecin'),(40,'meredeee',123,'Medecin'),(41,'R',12,'Secraitaire'),(42,'fatigué',123,'Secraitaire'),(43,'lydialili',1234,'Medecin'),(44,'Lydia',12345,'Secraitaire'),(45,'snow',123,'Secraitaire'),(46,'roukayaben',1111,'Secraitaire'),(47,'benhatiteko',4545,'Secraitaire'),(48,'matouprince',1111,'Secraitaire'),(49,'taoussachaba',2122004,'Medecin'),(50,'rekaia',101,'Patient'),(51,'amine',1212,'Secraitaire'),(55,'ika',0,'Medecin'),(56,'taoussa zella',4444,'Patient'),(57,'taoussazella',4444,'Medecin'),(59,'ikaa',444,'Patient'),(60,'k',1,'Patient'),(61,'ikaika',123,'Patient'),(62,'rekaiabenhatite',444,'Medecin'),(63,'lydiakawterhanane',1111,'Secraitaire'),(64,'ben',111,'Patient'),(65,'finaltry',4545,'Secraitaire'),(66,'lil',11,'Secraitaire'),(68,'mouhamed',4545,'Patient'),(69,'rekaiaaa',111,'Patient'),(70,'lydialala',1111,'Medecin'),(71,'kiki',111,'Medecin'),(72,'lii',11,'Medecin'),(73,'mmm',44,'Medecin'),(74,'ara',454,'Medecin'),(75,'lo',44,'Secraitaire'),(76,'lololo',455,'Secraitaire'),(77,'re',44,'Medecin'),(78,'lolo',44,'Secraitaire'),(79,'err',455,'Secraitaire'),(80,'nom',455,'Patient'),(81,'rekiaaa',45566,'Medecin');
/*!40000 ALTER TABLE `createcompte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medecin` (
  `idmedecin` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `numtel` int DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `spécialité` varchar(45) DEFAULT NULL,
  `idutilisateur` int NOT NULL,
  PRIMARY KEY (`idmedecin`),
  KEY `idutilisateur_idx` (`idutilisateur`),
  CONSTRAINT `idutilisateur` FOREIGN KEY (`idutilisateur`) REFERENCES `createcompte` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecin`
--

LOCK TABLES `medecin` WRITE;
/*!40000 ALTER TABLE `medecin` DISABLE KEYS */;
INSERT INTO `medecin` VALUES (5,'LAST','LAST','LAST',1233,'LAST','LAST',27),(6,'LYDIA','CHALA','CHALALLYDIA@GMAIL.com',1234567,'ALGER','INFO',28),(7,'HOPE','HOPE','HOPE@GMAIL.COM',1234,'alger','HPE',31),(8,'Y','Z','M',123,'AL','Z',40),(9,'CHALAL','LYDIA','chalallydia@gmail.com',655432678,'ALGER ','diabétologie',43);
/*!40000 ALTER TABLE `medecin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `Patient ID` int NOT NULL AUTO_INCREMENT,
  `Nom` varchar(45) DEFAULT NULL,
  `Prénom` varchar(45) DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `Numérotel` int DEFAULT NULL,
  `Anticédents` varchar(800) DEFAULT NULL,
  `idcompte` int DEFAULT NULL,
  PRIMARY KEY (`Patient ID`),
  KEY `fk_idcompte` (`idcompte`),
  CONSTRAINT `fk_idcompte` FOREIGN KEY (`idcompte`) REFERENCES `createcompte` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (3,'rafik','chalal',20,123,'DIABETE',NULL),(4,'sali','abd',50,661661,'RIEN',NULL),(5,'YAN','INS',28,56678,'YY',NULL),(8,'DAVID','ALLOUI',27,12345,'RIEN',NULL),(9,'kawteroo','koko',20,1334,'RIEN',NULL),(10,'celia','aa',23,23344,'RIEN',NULL),(11,'rekaia','benhatite',20,775248939,'',NULL),(12,'mouhamed','moh',12,8848,'rien',NULL);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rendezvous` (
  `idPatient` int NOT NULL,
  `Nompatient` varchar(45) DEFAULT NULL,
  `prenompatient` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `heure` varchar(45) DEFAULT NULL,
  `IDmed` int DEFAULT NULL,
  KEY `idpatient_idx` (`idPatient`),
  KEY `IDmed_idx` (`IDmed`),
  CONSTRAINT `IDmed` FOREIGN KEY (`IDmed`) REFERENCES `medecin` (`idmedecin`),
  CONSTRAINT `idpatient` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`Patient ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous` VALUES (3,'chalal','rafik','2024-05-01','8H ',5),(8,'david','alloui','2024-05-10','8H ',6),(9,'kawter','KOO','2024-05-02','8H ',7),(10,'celia','cliaa','2024-05-02','8H ',9),(11,'rekaia','benhatite','2024-05-03','8H ',9),(3,'rafik','chalal','2024-05-24','10H',9);
/*!40000 ALTER TABLE `rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secraitaire`
--

DROP TABLE IF EXISTS `secraitaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secraitaire` (
  `idsecraitaire` int NOT NULL AUTO_INCREMENT,
  `Nom` varchar(45) DEFAULT NULL,
  `Prenom` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `telephone` int DEFAULT NULL,
  `Adresse` varchar(45) DEFAULT NULL,
  `idutilisateursec` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`idsecraitaire`),
  KEY `idutilisateursec_idx` (`idutilisateursec`),
  CONSTRAINT `idutilisateursec` FOREIGN KEY (`idutilisateursec`) REFERENCES `createcompte` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secraitaire`
--

LOCK TABLES `secraitaire` WRITE;
/*!40000 ALTER TABLE `secraitaire` DISABLE KEYS */;
INSERT INTO `secraitaire` VALUES (4,'fatiguéa','a mort','merde',123,'pleasemarche',42,NULL),(5,'chalal','lydia','chalallydia@gmail.com',6771473,'bejaia',44,NULL),(6,'snow','white','snow@gmail.com',123,'alg',45,21),(11,'dved','xjnbcv','dh',6595,'hdfv',79,5);
/*!40000 ALTER TABLE `secraitaire` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23  1:30:15
