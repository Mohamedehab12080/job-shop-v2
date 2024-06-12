CREATE DATABASE  IF NOT EXISTS `jobshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jobshop`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: jobshop
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `job_seeker_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtbcejcatqctectw6hhwwbsv97` (`post_id`),
  KEY `FKg7bshxgetwt3lb5whvku5sjay` (`job_seeker_id`),
  CONSTRAINT `FKg7bshxgetwt3lb5whvku5sjay` FOREIGN KEY (`job_seeker_id`) REFERENCES `job_seeker` (`id`),
  CONSTRAINT `FKtbcejcatqctectw6hhwwbsv97` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_qualification`
--

DROP TABLE IF EXISTS `application_qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_qualification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` bigint DEFAULT NULL,
  `job_seeker_qualification_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ygw0hefdctrbwohrpd5lsnls` (`application_id`),
  KEY `FKi4w5eq76sm4n5p1y98bnntuah` (`job_seeker_qualification_id`),
  CONSTRAINT `FK1ygw0hefdctrbwohrpd5lsnls` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FKi4w5eq76sm4n5p1y98bnntuah` FOREIGN KEY (`job_seeker_qualification_id`) REFERENCES `job_seeker_qualification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_qualification`
--

LOCK TABLES `application_qualification` WRITE;
/*!40000 ALTER TABLE `application_qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_skill`
--

DROP TABLE IF EXISTS `application_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` bigint DEFAULT NULL,
  `job_seeker_skill_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3eijpi60amysjuowey6u3g2ag` (`application_id`),
  KEY `FKmq00412f42s0scr6l1v5ljyoj` (`job_seeker_skill_id`),
  CONSTRAINT `FK3eijpi60amysjuowey6u3g2ag` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FKmq00412f42s0scr6l1v5ljyoj` FOREIGN KEY (`job_seeker_skill_id`) REFERENCES `job_seeker_skill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_skill`
--

LOCK TABLES `application_skill` WRITE;
/*!40000 ALTER TABLE `application_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_administrator`
--

DROP TABLE IF EXISTS `company_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_administrator` (
  `company_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ma2nig30fawkioequvsv9wm1` (`company_name`),
  CONSTRAINT `FKqiv804j7e79oytuycvsgw8ass` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_administrator`
--

LOCK TABLES `company_administrator` WRITE;
/*!40000 ALTER TABLE `company_administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_field`
--

DROP TABLE IF EXISTS `company_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_field` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `company_administrator_id` bigint NOT NULL,
  `field_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ebos3nsjl3e1fl5f3iw1lkfs5` (`field_id`),
  KEY `FK2fg6pr2l3a9l2p6rb1xi1uj77` (`company_administrator_id`),
  CONSTRAINT `FK2fg6pr2l3a9l2p6rb1xi1uj77` FOREIGN KEY (`company_administrator_id`) REFERENCES `company_administrator` (`id`),
  CONSTRAINT `FKhal86qmbhvoh2h8vuytemo4g2` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_field`
--

LOCK TABLES `company_field` WRITE;
/*!40000 ALTER TABLE `company_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_field_qualification`
--

DROP TABLE IF EXISTS `company_field_qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_field_qualification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `company_field_id` bigint DEFAULT NULL,
  `qualification_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb8qh91dyhkwkyfbminj6rsrp` (`company_field_id`),
  KEY `FKjtmq1456enjgs02f2qrn035iv` (`qualification_id`),
  CONSTRAINT `FKb8qh91dyhkwkyfbminj6rsrp` FOREIGN KEY (`company_field_id`) REFERENCES `company_field` (`id`),
  CONSTRAINT `FKjtmq1456enjgs02f2qrn035iv` FOREIGN KEY (`qualification_id`) REFERENCES `qualification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_field_qualification`
--

LOCK TABLES `company_field_qualification` WRITE;
/*!40000 ALTER TABLE `company_field_qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_field_qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_field_skill`
--

DROP TABLE IF EXISTS `company_field_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_field_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `company_field_id` bigint DEFAULT NULL,
  `skill_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdl9uh59hflrd4t0vtdous8iog` (`company_field_id`),
  KEY `FK28b492ioswom0mvjd7rwas9yh` (`skill_id`),
  CONSTRAINT `FK28b492ioswom0mvjd7rwas9yh` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`),
  CONSTRAINT `FKdl9uh59hflrd4t0vtdous8iog` FOREIGN KEY (`company_field_id`) REFERENCES `company_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_field_skill`
--

LOCK TABLES `company_field_skill` WRITE;
/*!40000 ALTER TABLE `company_field_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_field_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_profile`
--

DROP TABLE IF EXISTS `company_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_administrator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2jeot6w6kxx6ayc22hjyr075x` (`company_administrator_id`),
  CONSTRAINT `FKt93w0xygg2i80ko4t1682pi6` FOREIGN KEY (`company_administrator_id`) REFERENCES `company_administrator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_profile`
--

LOCK TABLES `company_profile` WRITE;
/*!40000 ALTER TABLE `company_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` bigint NOT NULL,
  `company_administrator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7wc20hp85r7agffb3wqbkdjpw` (`company_administrator_id`),
  CONSTRAINT `FK7wc20hp85r7agffb3wqbkdjpw` FOREIGN KEY (`company_administrator_id`) REFERENCES `company_administrator` (`id`),
  CONSTRAINT `FKmdje7uepsvocbhnmslxdxw8b` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer_field`
--

DROP TABLE IF EXISTS `employer_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer_field` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `company_field_id` bigint DEFAULT NULL,
  `employer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xnb7m0kg1s8ekicmp74y3x0n` (`company_field_id`),
  KEY `FK8fl60svflp74xtvcxuyb5okxc` (`employer_id`),
  CONSTRAINT `FK7xnb7m0kg1s8ekicmp74y3x0n` FOREIGN KEY (`company_field_id`) REFERENCES `company_field` (`id`),
  CONSTRAINT `FK8fl60svflp74xtvcxuyb5okxc` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer_field`
--

LOCK TABLES `employer_field` WRITE;
/*!40000 ALTER TABLE `employer_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `employer_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer_profile`
--

DROP TABLE IF EXISTS `employer_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1h2aifusqqlhtm8thjgn5yywa` (`employer_id`),
  CONSTRAINT `FKra5wmp1rd99ga3whwm3tax5aw` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer_profile`
--

LOCK TABLES `employer_profile` WRITE;
/*!40000 ALTER TABLE `employer_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `employer_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `field` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `field_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f8mr6kl8yhhwvv1j7ywr8hout` (`field_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `follower_id` bigint DEFAULT NULL,
  `following_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmow2qk674plvwyb4wqln37svv` (`follower_id`),
  KEY `FKqme6uru2g9wx9iysttk542esm` (`following_id`),
  CONSTRAINT `FKmow2qk674plvwyb4wqln37svv` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqme6uru2g9wx9iysttk542esm` FOREIGN KEY (`following_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker`
--

DROP TABLE IF EXISTS `job_seeker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker` (
  `birth_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `employment_state` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK21q1cv1wujv1suy7cocb5cw2s` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker`
--

LOCK TABLES `job_seeker` WRITE;
/*!40000 ALTER TABLE `job_seeker` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_seeker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker_profile`
--

DROP TABLE IF EXISTS `job_seeker_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_seeker_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o5mnyke4p1sv06oo82rfdebyg` (`job_seeker_id`),
  CONSTRAINT `FK5m0bhk80y4mc7n9dxp39yrjei` FOREIGN KEY (`job_seeker_id`) REFERENCES `job_seeker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_profile`
--

LOCK TABLES `job_seeker_profile` WRITE;
/*!40000 ALTER TABLE `job_seeker_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_seeker_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker_qualification`
--

DROP TABLE IF EXISTS `job_seeker_qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker_qualification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `qualification_degree` varchar(255) DEFAULT NULL,
  `job_seeker_id` bigint DEFAULT NULL,
  `qualification_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6e3ml55pi2rrauy9qg5m9i6vs` (`job_seeker_id`),
  KEY `FKf03wptgx6clutqfjjocmcfspt` (`qualification_id`),
  CONSTRAINT `FK6e3ml55pi2rrauy9qg5m9i6vs` FOREIGN KEY (`job_seeker_id`) REFERENCES `job_seeker` (`id`),
  CONSTRAINT `FKf03wptgx6clutqfjjocmcfspt` FOREIGN KEY (`qualification_id`) REFERENCES `qualification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_qualification`
--

LOCK TABLES `job_seeker_qualification` WRITE;
/*!40000 ALTER TABLE `job_seeker_qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_seeker_qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker_skill`
--

DROP TABLE IF EXISTS `job_seeker_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `skill_degree` varchar(255) DEFAULT NULL,
  `job_seeker_id` bigint DEFAULT NULL,
  `skill_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcooldyol16rgoi6gusx7nccs7` (`job_seeker_id`),
  KEY `FKhngwkotdhgi4w0i2ig3x9k9jb` (`skill_id`),
  CONSTRAINT `FKcooldyol16rgoi6gusx7nccs7` FOREIGN KEY (`job_seeker_id`) REFERENCES `job_seeker` (`id`),
  CONSTRAINT `FKhngwkotdhgi4w0i2ig3x9k9jb` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_skill`
--

LOCK TABLES `job_seeker_skill` WRITE;
/*!40000 ALTER TABLE `job_seeker_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_seeker_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `location_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f90ivichjaokvmovxpnlm5nin` (`user_id`),
  CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `job_requirments` varchar(1000) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `company_profile_id` bigint DEFAULT NULL,
  `employer_id` bigint DEFAULT NULL,
  `post_field_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5e21dfp4gu19hpav1rhyep7us` (`post_field_id`),
  KEY `FK9mylxcq3rnyltbf9ioltyq28q` (`company_profile_id`),
  KEY `FKto1lyptcxhil3m793ixsksluq` (`employer_id`),
  CONSTRAINT `FK5yicebnbpobgfpmglt2loxca9` FOREIGN KEY (`post_field_id`) REFERENCES `post_field` (`id`),
  CONSTRAINT `FK9mylxcq3rnyltbf9ioltyq28q` FOREIGN KEY (`company_profile_id`) REFERENCES `company_profile` (`id`),
  CONSTRAINT `FKto1lyptcxhil3m793ixsksluq` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_field`
--

DROP TABLE IF EXISTS `post_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_field` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `qualifications` varbinary(255) DEFAULT NULL,
  `skills` varbinary(255) DEFAULT NULL,
  `field_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcifm3n2gtqtsiqnryyemuwq2q` (`field_id`),
  CONSTRAINT `FKcifm3n2gtqtsiqnryyemuwq2q` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_field`
--

LOCK TABLES `post_field` WRITE;
/*!40000 ALTER TABLE `post_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `qualification_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lvx0afsbm0rbq3sud1xdpslr1` (`qualification_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1ledx6hfgc5c7ht0js8bmdqs0` (`skill_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `contacts` varbinary(255) DEFAULT NULL,
  `cover_image` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_login_with_google` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `req_user` bit(1) NOT NULL,
  `role` tinyint DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `ends_at` datetime(6) DEFAULT NULL,
  `plan_type` varchar(255) DEFAULT NULL,
  `started_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  CONSTRAINT `user_chk_1` CHECK ((`role` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_followers`
--

DROP TABLE IF EXISTS `user_followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_followers` (
  `user_id` bigint NOT NULL,
  `followers_id` bigint NOT NULL,
  KEY `FKpvkdr9tjpc96kdwe7591oixnj` (`followers_id`),
  KEY `FKokc5w6fibhnthvwnxjxyrlfc1` (`user_id`),
  CONSTRAINT `FKokc5w6fibhnthvwnxjxyrlfc1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpvkdr9tjpc96kdwe7591oixnj` FOREIGN KEY (`followers_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_followers`
--

LOCK TABLES `user_followers` WRITE;
/*!40000 ALTER TABLE `user_followers` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_followers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_followings`
--

DROP TABLE IF EXISTS `user_followings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_followings` (
  `user_id` bigint NOT NULL,
  `followings_id` bigint NOT NULL,
  KEY `FK1f1kxtjhmrvlvrhqmuwf9r7ls` (`followings_id`),
  KEY `FKj2a8435v8kbuogf5d8aaudfrp` (`user_id`),
  CONSTRAINT `FK1f1kxtjhmrvlvrhqmuwf9r7ls` FOREIGN KEY (`followings_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKj2a8435v8kbuogf5d8aaudfrp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_followings`
--

LOCK TABLES `user_followings` WRITE;
/*!40000 ALTER TABLE `user_followings` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_followings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q6jibbenp7o9v6tq178xg88hg` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-12 22:57:16
