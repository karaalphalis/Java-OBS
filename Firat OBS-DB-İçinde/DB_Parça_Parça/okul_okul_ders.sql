-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: okul
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `okul_ders`
--

DROP TABLE IF EXISTS `okul_ders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `okul_ders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `öğrenci_id` int DEFAULT NULL,
  `worker_id` int DEFAULT NULL,
  `kesinleştirme` enum('Kesinleştirildi','Kesinleştirilmedi') NOT NULL DEFAULT 'Kesinleştirilmedi',
  `onay` enum('onaylandı','onaylanmadı') NOT NULL DEFAULT 'onaylanmadı',
  `harf_notu` varchar(15) DEFAULT NULL,
  `durumu` enum('belirsiz','geçti','kaldı') NOT NULL DEFAULT 'belirsiz',
  `gönderilme_durum` enum('gönderildi','gönderilmedi') DEFAULT 'gönderilmedi',
  `vize_notu` int DEFAULT NULL,
  `final_notu` int DEFAULT NULL,
  `ortalama` int DEFAULT NULL,
  `öğrenci_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `okul_ders`
--

LOCK TABLES `okul_ders` WRITE;
/*!40000 ALTER TABLE `okul_ders` DISABLE KEYS */;
INSERT INTO `okul_ders` (`id`, `öğrenci_id`, `worker_id`, `kesinleştirme`, `onay`, `harf_notu`, `durumu`, `gönderilme_durum`, `vize_notu`, `final_notu`, `ortalama`, `öğrenci_name`) VALUES (3,30,101,'Kesinleştirildi','onaylandı','A','geçti','gönderildi',100,100,100,'halis');
/*!40000 ALTER TABLE `okul_ders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-29 22:38:39
