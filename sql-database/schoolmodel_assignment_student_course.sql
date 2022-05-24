CREATE DATABASE  IF NOT EXISTS `schoolmodel` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `schoolmodel`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: schoolmodel
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `assignment_student_course`
--

DROP TABLE IF EXISTS `assignment_student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_student_course` (
  `course_code` int NOT NULL,
  `assignment_code` int NOT NULL,
  `student_code` int NOT NULL,
  PRIMARY KEY (`course_code`,`assignment_code`,`student_code`),
  KEY `assignment_student_course_fk_assignment` (`assignment_code`),
  KEY `assignment_student_course_fk_student` (`student_code`),
  CONSTRAINT `assignment_student_course_fk_assignment` FOREIGN KEY (`assignment_code`) REFERENCES `assignment` (`assignment_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assignment_student_course_fk_course` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assignment_student_course_fk_student` FOREIGN KEY (`student_code`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_student_course`
--

LOCK TABLES `assignment_student_course` WRITE;
/*!40000 ALTER TABLE `assignment_student_course` DISABLE KEYS */;
INSERT INTO `assignment_student_course` VALUES (1,21,21),(1,21,22),(1,21,23),(1,21,34),(1,21,37),(1,22,21),(1,22,22),(1,22,23),(1,22,34),(1,22,37),(1,23,21),(1,23,22),(1,23,23),(1,23,34),(1,23,37),(1,24,21),(1,24,22),(1,24,23),(1,24,34),(1,24,37),(1,25,21),(1,25,22),(1,25,23),(1,25,34),(1,25,37),(2,26,21),(2,26,22),(2,26,35),(2,26,36),(2,27,21),(2,27,22),(2,27,35),(2,27,36),(2,28,21),(2,28,22),(2,28,35),(2,28,36);
/*!40000 ALTER TABLE `assignment_student_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24  3:50:14
