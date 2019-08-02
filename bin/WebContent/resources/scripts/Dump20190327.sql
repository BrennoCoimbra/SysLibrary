CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (1,'Augusto Cury'),(2,'Coelho Neto'),(3,'Dan Brown');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Administracao'),(2,'Banco de Dados'),(4,'Calculo'),(3,'Contabilidade'),(5,'Economia e Financas'),(6,'Empreendedorismo'),(7,'Engenharia de Software'),(8,'Gestao de Projetos'),(11,'Hardware'),(9,'Ingles'),(10,'Inteligencia Artificial'),(12,'Programacao'),(13,'Seguranca da Informacao'),(14,'Sistemas de Informacao'),(15,'Sistemas Operacionais');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `editoras`
--

LOCK TABLES `editoras` WRITE;
/*!40000 ALTER TABLE `editoras` DISABLE KEYS */;
INSERT INTO `editoras` VALUES (1,'abril'),(2,'veja');
/*!40000 ALTER TABLE `editoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `livro_autor`
--

LOCK TABLES `livro_autor` WRITE;
/*!40000 ALTER TABLE `livro_autor` DISABLE KEYS */;
INSERT INTO `livro_autor` VALUES (3,11,1),(4,11,2),(5,11,3),(13,18,3),(14,19,3),(15,20,2),(16,21,1),(17,22,1),(18,23,1),(19,23,3),(21,25,2),(22,26,2);
/*!40000 ALTER TABLE `livro_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `livro_categoria`
--

LOCK TABLES `livro_categoria` WRITE;
/*!40000 ALTER TABLE `livro_categoria` DISABLE KEYS */;
INSERT INTO `livro_categoria` VALUES (1,11,2),(2,11,12),(10,18,2),(11,19,2),(12,20,4),(13,21,1),(14,22,1),(15,23,2),(16,23,13),(19,25,4),(20,26,2);
/*!40000 ALTER TABLE `livro_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (1,'Aprenda','2015','1','9788582603369',704,'teste',25,17,5,6,'9788582603369',1,NULL,'2019-03-11 22:26:26',NULL,NULL),(2,'Java Para Iniciantes','2015','1','9788582603369',704,'teste',25,17,1,3,'9788582603369',1,NULL,'2019-03-11 22:39:33',NULL,NULL),(3,'Java','2015','1','9788582603369',123,'teste',25,15,1,4,'9788582603369',1,NULL,'2019-03-11 22:46:06',NULL,NULL),(4,'Java','2015','1','9788582603369',123,'tests',10,21,1,1,'9788582603369',0,NULL,'2019-03-11 22:51:13',NULL,NULL),(5,'laal','2016','123','123',321,'we',1,1,1,1,'123',1,2,'2019-03-13 22:05:57',NULL,NULL),(6,'lala','2015','1','123',123,'teste',1,1,1,1,'321',1,2,'2019-03-14 19:53:14',NULL,NULL),(7,'la','2015','123','123',321,'fa',1,1,1,1,'123',1,2,'2019-03-14 20:13:07',NULL,NULL),(8,'lala','2015','1','123',123,'testes',1,1,1,1,'123',1,2,'2019-03-14 21:02:07',NULL,NULL),(11,'MicrosoftÂ® SQL ServerÂ® - 2016 Express Edition Interativo','2017','1','9788536524504',224,'Com o objetivo de apresentar ao leitor iniciante na prÃ¡tica de administraÃ§Ã£o de bancos de dados o programa gerenciador de banco de dados Microsoft SQL Server 2016 Express',1,1,1,1,'9788536524504',1,1,'2019-03-16 22:17:28',NULL,NULL),(18,'fafa','2017','1','333',123,'res',1,1,1,1,'333',1,1,'2019-03-17 12:52:03',NULL,NULL),(19,'s','2017','1','222',222,'eee',2,2,2,2,'222',1,1,'2019-03-17 13:25:28',NULL,NULL),(20,'rrr','2017','3','111',123,'r',1,1,1,1,'111',1,1,'2019-03-17 13:29:08',NULL,NULL),(21,'eee','2018','1','222',111,'teste',1,1,1,1,'111',1,1,'2019-03-17 13:43:47',NULL,NULL),(22,'eee','2018','1','222',111,'teste',1,1,1,1,'111',1,1,'2019-03-17 13:44:29',NULL,NULL),(23,'gaga','2018','1','444',222,'teste',2,2,2,2,'444',1,1,'2019-03-18 19:30:55',NULL,NULL),(25,'te','2017','1','1234',2,'tese',1,1,1,1,'1234',1,1,'2019-03-20 23:38:46',NULL,NULL),(26,'fafa','2018','1','123321',123,'teste',1,1,1,1,'123321',1,1,'2019-03-21 01:48:49',NULL,NULL);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-27 19:41:19
