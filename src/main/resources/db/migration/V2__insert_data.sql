-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: demo
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Dumping data for table `grade`
--

/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (5,2,37,1),(6,10,41,3),(7,8,37,2);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;

--
-- Dumping data for table `lesson`
--

/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (34,'2025-05-01',NULL,NULL,12),(35,'2025-05-01',NULL,NULL,13),(36,'2025-05-05',NULL,NULL,10),(37,'2025-05-06','п1','п1',11),(38,'2025-05-08',NULL,NULL,12),(39,'2025-05-08',NULL,NULL,13),(40,'2025-05-12',NULL,NULL,10),(41,'2025-05-13','Война и мир','пересказ',11),(42,'2025-05-15',NULL,NULL,12),(43,'2025-05-15',NULL,NULL,13),(44,'2025-05-19',NULL,NULL,10),(45,'2025-05-20',NULL,NULL,11),(46,'2025-05-22',NULL,NULL,12),(47,'2025-05-22',NULL,NULL,13),(48,'2025-05-26',NULL,NULL,10),(49,'2025-05-27',NULL,NULL,11),(50,'2025-05-29',NULL,NULL,12),(51,'2025-05-29',NULL,NULL,13);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;

--
-- Dumping data for table `schedule`
--

/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (10,'MONDAY',1,101,1,1,1),(11,'FRIDAY',1,101,1,2,1),(12,'THURSDAY',1,101,1,1,1),(13,'THURSDAY',2,101,1,2,1),(14,'WEDNESDAY',1,113,1,1,1);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

--
-- Dumping data for table `school`
--

/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'СШ 1','Советская 1','5488935','sch1@edu.by','Васильева Т. Н.'),(2,'СШ 2','Победителей 1','5423387','sch2@edu.by','Рябова Е. В.');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;

--
-- Dumping data for table `schoolchild`
--

/*!40000 ALTER TABLE `schoolchild` DISABLE KEYS */;
INSERT INTO `schoolchild` VALUES (1,'Иванов Алексей Павлович',3,1),(2,'Петров Александр Сергеевич',4,1),(3,'Петрова Ксения Руслановна',5,1),(9,'Тестовый Тест Тестович1',6,1),(10,'Сидоров Дмитрий Иванович',NULL,1);
/*!40000 ALTER TABLE `schoolchild` ENABLE KEYS */;

--
-- Dumping data for table `schoolchild_schoolclass`
--

/*!40000 ALTER TABLE `schoolchild_schoolclass` DISABLE KEYS */;
INSERT INTO `schoolchild_schoolclass` VALUES (1,1),(2,1),(3,1),(10,1),(9,2);
/*!40000 ALTER TABLE `schoolchild_schoolclass` ENABLE KEYS */;

--
-- Dumping data for table `schoolclass`
--

/*!40000 ALTER TABLE `schoolclass` DISABLE KEYS */;
INSERT INTO `schoolclass` VALUES (1,6,'А',1,1),(2,6,'Б',1,1),(3,8,'В',1,1),(4,1,'А',2,1),(5,1,'Б',2,1),(7,1,'А',1,1),(8,1,'Б',1,1),(9,2,'В',1,1);
/*!40000 ALTER TABLE `schoolclass` ENABLE KEYS */;

--
-- Dumping data for table `subject`
--

/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Русский язык'),(2,'Русская литература'),(3,'Математика'),(4,'Белорусский язык'),(5,'Белорусская литература'),(6,'ОБЖ'),(7,'История'),(8,'Обществоведение'),(9,'Химия'),(10,'Биология'),(11,'Физика');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

--
-- Dumping data for table `teacher`
--

/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Сергеева Марина Александровна',2,1),(3,'Федорович Татьяна Дмитриевна',9,1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

--
-- Dumping data for table `teacher_subject`
--

/*!40000 ALTER TABLE `teacher_subject` DISABLE KEYS */;
INSERT INTO `teacher_subject` VALUES (1,2),(3,9);
/*!40000 ALTER TABLE `teacher_subject` ENABLE KEYS */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$12$8fcN/MhoOpmesKBnz5CHlumOs2VnEXFoGD1huZnZvp8XkuGKfNU92',1,1),(2,'teacher','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(3,'schoolchild','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(4,'schoolchild1','$2a$12$DhlWljVKmEOO1h4bPKo0GeYFsKd9LuTXAYT40r1d6F6TH9L2PznDy',1,1),(5,'schoolchild2','$2a$12$MI.2IPnTuJbT03ITO5UWs.xG9uqdzXUBE6DpYbd1DvuDiMre/04hq',1,1),(6,'schoolchild3','$2a$12$TYVWd/bvlTyeZ/imAhl39uDnE3vUS/SxTvyuF8mExbAhQF7S7Te6e',1,1),(7,'schoolchild4','$2a$12$McqIPCqnG4UT97wVQGFpl.HWF6b2G4xJ8KeEbD7uuZYf9Fmxmw/z6',1,1),(8,'schoolchild5','$2a$12$dR0UZ0Jhs/Bg.CwuZk5Sb./hE9uyyNVJt1cLlLr42Fzk/jggJX/9a',1,1),(9,'teacher1','$2a$12$xdSJYWzEPYqEpwL/My./cuuY5Q7hRa069yvx0Rkyy8UBPiaCeX3mO',1,1),(10,'teacher2','$2a$12$Vtn7IDO93sf7dNR4JWpUieh0ce/4sL.NylhcA4LUUuLDPtrIk0642',1,1),(11,'teacher3','$2a$12$KvnVuI2B/w5df0VPdjZt..oEooMwblsbuGh2N.Mwdwui/rtq5gqsO',1,1),(12,'teacher4','$2a$12$Hw7CVr/9sjKzpwm9A6yRbOD8Yxl2.4EC4Aq0oa.R979n31syIBX3e',1,1),(14,'test','$2a$10$wFb2hlGKkOeh0KXZFBGzPOllyHXscg/jfwHcK/bWvgspVCrZQiK9q',NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping data for table `user_roles`
--

/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_TEACHER'),(3,'ROLE_SCHOOLCHILD'),(4,'ROLE_SCHOOLCHILD'),(5,'ROLE_SCHOOLCHILD'),(6,'ROLE_SCHOOLCHILD'),(7,'ROLE_SCHOOLCHILD'),(8,'ROLE_SCHOOLCHILD'),(9,'ROLE_TEACHER'),(10,'ROLE_TEACHER'),(11,'ROLE_TEACHER'),(12,'ROLE_TEACHER'),(14,'ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

