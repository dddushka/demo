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
-- Dumping data for table `flyway_schema_history`
--

/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','create db','SQL','V1__create_db.sql',-1828559230,'root','2025-06-03 23:45:10',383,1),(2,'2','insert data','SQL','V2__insert_data.sql',940721513,'root','2025-06-03 23:45:10',17,0);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;

--
-- Dumping data for table `grade`
--

/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;

--
-- Dumping data for table `lesson`
--

/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (102,'2025-06-02','тест','тест',25),(103,'2025-06-09',NULL,NULL,25),(104,'2025-06-16',NULL,NULL,25),(105,'2025-06-23',NULL,NULL,25),(106,'2025-06-30',NULL,NULL,25),(107,'2025-05-01',NULL,NULL,35),(108,'2025-05-01',NULL,NULL,39),(109,'2025-05-01',NULL,NULL,40),(110,'2025-05-02',NULL,NULL,32),(111,'2025-05-02',NULL,NULL,36),(112,'2025-05-02',NULL,NULL,37),(113,'2025-05-05',NULL,NULL,25),(114,'2025-05-05',NULL,NULL,26),(115,'2025-05-05',NULL,NULL,27),(116,'2025-05-05',NULL,NULL,28),(117,'2025-05-05',NULL,NULL,29),(118,'2025-05-06',NULL,NULL,30),(119,'2025-05-06',NULL,NULL,31),(120,'2025-05-06',NULL,NULL,33),(121,'2025-05-07',NULL,NULL,34),(122,'2025-05-07',NULL,NULL,38),(123,'2025-05-08',NULL,NULL,35),(124,'2025-05-08',NULL,NULL,39),(125,'2025-05-08',NULL,NULL,40),(126,'2025-05-09',NULL,NULL,32),(127,'2025-05-09',NULL,NULL,36),(128,'2025-05-09',NULL,NULL,37),(129,'2025-05-12',NULL,NULL,25),(130,'2025-05-12',NULL,NULL,26),(131,'2025-05-12',NULL,NULL,27),(132,'2025-05-12',NULL,NULL,28),(133,'2025-05-12',NULL,NULL,29),(134,'2025-05-13',NULL,NULL,30),(135,'2025-05-13',NULL,NULL,31),(136,'2025-05-13',NULL,NULL,33),(137,'2025-05-14',NULL,NULL,34),(138,'2025-05-14',NULL,NULL,38),(139,'2025-05-15',NULL,NULL,35),(140,'2025-05-15',NULL,NULL,39),(141,'2025-05-15',NULL,NULL,40),(142,'2025-05-16',NULL,NULL,32),(143,'2025-05-16',NULL,NULL,36),(144,'2025-05-16',NULL,NULL,37),(145,'2025-05-19',NULL,NULL,25),(146,'2025-05-19',NULL,NULL,26),(147,'2025-05-19',NULL,NULL,27),(148,'2025-05-19',NULL,NULL,28),(149,'2025-05-19',NULL,NULL,29),(150,'2025-05-20',NULL,NULL,30),(151,'2025-05-20',NULL,NULL,31),(152,'2025-05-20',NULL,NULL,33),(153,'2025-05-21',NULL,NULL,34),(154,'2025-05-21',NULL,NULL,38),(155,'2025-05-22',NULL,NULL,35),(156,'2025-05-22',NULL,NULL,39),(157,'2025-05-22',NULL,NULL,40),(158,'2025-05-23',NULL,NULL,32),(159,'2025-05-23',NULL,NULL,36),(160,'2025-05-23',NULL,NULL,37),(161,'2025-05-26',NULL,NULL,25),(162,'2025-05-26',NULL,NULL,26),(163,'2025-05-26',NULL,NULL,27),(164,'2025-05-26',NULL,NULL,28),(165,'2025-05-26',NULL,NULL,29),(166,'2025-05-27',NULL,NULL,30),(167,'2025-05-27',NULL,NULL,31),(168,'2025-05-27',NULL,NULL,33),(169,'2025-05-28',NULL,NULL,34),(170,'2025-05-28',NULL,NULL,38),(171,'2025-05-29',NULL,NULL,35),(172,'2025-05-29',NULL,NULL,39),(173,'2025-05-29',NULL,NULL,40),(174,'2025-05-30',NULL,NULL,32),(175,'2025-05-30',NULL,NULL,36),(176,'2025-05-30',NULL,NULL,37);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;

--
-- Dumping data for table `schedule`
--

/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (25,'MONDAY',1,112,18,36,1),(26,'MONDAY',2,214,18,31,8),(27,'MONDAY',3,213,18,41,11),(28,'MONDAY',4,309,18,39,7),(29,'MONDAY',5,319,18,37,4),(30,'TUESDAY',1,218,18,43,10),(31,'TUESDAY',2,112,18,40,1),(32,'FRIDAY',1,112,18,40,1),(33,'TUESDAY',3,319,18,37,4),(34,'WEDNESDAY',1,319,18,37,4),(35,'THURSDAY',1,319,18,37,4),(36,'FRIDAY',2,319,18,37,4),(37,'FRIDAY',3,101,18,42,6),(38,'WEDNESDAY',2,101,18,42,6),(39,'THURSDAY',2,113,18,29,9),(40,'THURSDAY',3,113,18,35,9);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

--
-- Dumping data for table `school`
--

/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'СШ 1','Советская 1','5488933','sch1@edu.by','Васильева Т. Н.'),(2,'СШ 2','Победителей 1','5423387','sch2@edu.by','Рябова Е. В.');
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
INSERT INTO `schoolclass` VALUES (1,6,'А',1,1),(2,6,'Б',1,1),(3,8,'В',1,1),(4,1,'А',2,1),(5,1,'Б',2,1),(7,1,'А',1,1),(8,1,'Б',1,1),(9,2,'В',1,1),(11,2,'А',1,1),(17,2,'Б',1,1),(18,11,'А',1,1);
/*!40000 ALTER TABLE `schoolclass` ENABLE KEYS */;

--
-- Dumping data for table `subject`
--

/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (28,'Астрономия'),(29,'Белорусский язык'),(30,'Биология'),(31,'География'),(32,'Иностранный язык'),(33,'Информатика'),(34,'История Беларуси'),(35,'Белорусская литература'),(36,'Русская литература'),(37,'Математика'),(38,'ОБЖ'),(39,'Обществоведение'),(40,'Русский язык'),(41,'Физика'),(42,'Физическая культура'),(43,'Химия');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

--
-- Dumping data for table `teacher`
--

/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Сергеева Марина Александровна',2,1),(3,'Федорович Татьяна Михайловна',9,1),(4,'Козлова Ирина Викторовна',10,1),(5,'Сидоренко Алексей Михайлович',11,1),(6,'Климович Наталья Станиславовна',12,1),(7,'Лавренова Татьяна Петровна',20,1),(8,'Кравчук Андрей Игоревич',21,1),(9,'Антонова Елена Сергеевна',22,1),(10,'Мельникова Валерия Павловна',23,1),(11,'Черненко Дмитрий Валерьевич',24,1),(12,'Бондарь Анна Владимировна',25,1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

--
-- Dumping data for table `teacher_subject`
--

/*!40000 ALTER TABLE `teacher_subject` DISABLE KEYS */;
INSERT INTO `teacher_subject` VALUES (1,36),(1,40);
/*!40000 ALTER TABLE `teacher_subject` ENABLE KEYS */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$12$8fcN/MhoOpmesKBnz5CHlumOs2VnEXFoGD1huZnZvp8XkuGKfNU92',1,1),(2,'teacher','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(3,'schoolchild','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(4,'schoolchild1','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(5,'schoolchild2','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(6,'schoolchild3','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(7,'schoolchild4','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(8,'schoolchild5','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1),(9,'teacher1','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(10,'teacher2','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(11,'teacher3','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(12,'teacher4','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(19,'teacher5','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(20,'teacher6','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(21,'teacher7','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(22,'teacher8','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(23,'teacher9','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(24,'teacher10','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(25,'teacher11','$2a$12$00nsSVllDOw/hXhw6zSX3OEiUNe4SGcj1VZgEBNiVZehnkznYQWs.',1,1),(26,'schoolchild6','$2a$12$jli5YZM61dK5R3MLJun7Nu4i4BU0lS/.BMJVG/ERDpvYSHkhBfduq',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping data for table `user_roles`
--

/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_TEACHER'),(3,'ROLE_SCHOOLCHILD'),(4,'ROLE_SCHOOLCHILD'),(5,'ROLE_SCHOOLCHILD'),(6,'ROLE_SCHOOLCHILD'),(7,'ROLE_SCHOOLCHILD'),(8,'ROLE_SCHOOLCHILD'),(9,'ROLE_TEACHER'),(10,'ROLE_TEACHER'),(11,'ROLE_TEACHER'),(12,'ROLE_TEACHER'),(19,'ROLE_TEACHER'),(20,'ROLE_TEACHER'),(21,'ROLE_TEACHER'),(22,'ROLE_TEACHER'),(23,'ROLE_TEACHER'),(24,'ROLE_TEACHER'),(25,'ROLE_TEACHER'),(26,'ROLE_SCHOOLCHILD');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-05 18:13:14
