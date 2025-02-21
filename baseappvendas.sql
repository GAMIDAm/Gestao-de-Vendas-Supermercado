-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: baseappvendas
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(50) NOT NULL,
  `cpfCliente` varchar(20) NOT NULL,
  `dataNascimentoCliente` int NOT NULL,
  `sexoCliente` varchar(50) NOT NULL,
  `estadoCivilCliente` varchar(50) NOT NULL,
  `cepCliente` int NOT NULL,
  `logradouroCliente` varchar(100) NOT NULL,
  `numCasaCliente` int NOT NULL,
  `telefoneCliente` varchar(20) NOT NULL,
  `emailCliente` varchar(100) NOT NULL,
  PRIMARY KEY (`idCliente`,`sexoCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Joao Silva Santos','777.777.777-77',30102003,'Masculino','Solteiro',665544,'Av Cupece',554,'11956647887','joao@senac.com'),(2,'Maria','555.555.555-55',9111992,'Feminino','Casado',4488795,'Av. Yervant',259,'1199887755','Maria@senac.com'),(5,'Marcos Antonio','125.468.957-75',11111989,'Masculino','Casado',4487965,'Rua das Perobas',257,'11964289568','Marcos@senac.com'),(6,'Ricardo Santos','985.632.157-88',13111987,'Masculino','Solteiro',4488574,'Av. Interlagos',564,'1155649872','Ricardo@senac.com'),(7,'Elza Carmen','111.222.333-44',1121989,'Feminino','Casado',5566789,'Avenida Interlagos',856,'11987563212','Elza@senac.com.br');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `idProduto` int NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(50) NOT NULL,
  `valorProduto` double NOT NULL,
  `estoqueProduto` int NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Leite',8.99,3),(2,'Arroz',22.99,0),(3,'Feijão 1kg',5.99,6),(4,'Café 500g',21.99,7),(7,'Óleo de Soja',4.69,7),(8,'Coca Cola 2l',12.99,9),(9,'Sabão em pó 500g',19.9,14);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nomeUsuario` varchar(50) DEFAULT NULL,
  `loginUsuario` varchar(50) DEFAULT NULL,
  `senhaUsuario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Administrador','admin','1234'),(2,'Miguel Santos','miguel','2428'),(3,'Teste','teste','teste1234');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `idVenda` int NOT NULL AUTO_INCREMENT,
  `clienteId` int DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `dataVenda` datetime DEFAULT NULL,
  PRIMARY KEY (`idVenda`),
  KEY `clienteId` (`clienteId`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`clienteId`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (9,1,35.96,'2023-11-07 16:39:26'),(10,1,151.33,'2023-11-07 16:42:14'),(11,1,120.96,'2023-11-07 16:59:14'),(12,1,85.92,'2023-11-07 17:01:26'),(13,1,214.29,'2023-11-07 17:15:51'),(14,2,88.96,'2023-11-09 14:49:37'),(15,2,5.8,'2023-11-11 20:06:08'),(16,1,43.98,'2023-11-11 20:06:58'),(17,1,68.97,'2023-11-11 20:08:31'),(18,2,17.98,'2023-11-11 20:17:07'),(19,1,29,'2023-11-11 20:22:20'),(20,5,119.54,'2023-11-11 20:31:18'),(21,5,103.77,'2023-11-24 10:31:24'),(22,6,40.39,'2023-11-24 10:36:26'),(23,6,218.16,'2023-11-27 10:05:34'),(24,2,17.98,'2023-11-27 10:06:53'),(25,1,22.99,'2023-11-30 13:11:57'),(26,7,170.27,'2023-12-01 15:24:51'),(27,2,75.07,'2023-12-01 15:43:29'),(28,1,45.98,'2025-02-21 11:01:39'),(29,7,171.61,'2025-02-21 11:59:25');
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendadetalhes`
--

DROP TABLE IF EXISTS `vendadetalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendadetalhes` (
  `idVendaDetalhes` int NOT NULL AUTO_INCREMENT,
  `vendaId` int DEFAULT NULL,
  `produtoId` int DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  PRIMARY KEY (`idVendaDetalhes`),
  KEY `vendaId` (`vendaId`),
  KEY `produtoId` (`produtoId`),
  CONSTRAINT `vendadetalhes_ibfk_1` FOREIGN KEY (`vendaId`) REFERENCES `venda` (`idVenda`),
  CONSTRAINT `vendadetalhes_ibfk_2` FOREIGN KEY (`produtoId`) REFERENCES `produto` (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendadetalhes`
--

LOCK TABLES `vendadetalhes` WRITE;
/*!40000 ALTER TABLE `vendadetalhes` DISABLE KEYS */;
INSERT INTO `vendadetalhes` VALUES (1,13,1,4),(2,13,3,3),(3,13,2,7),(4,14,4,3),(5,14,2,1),(6,15,3,1),(7,16,4,2),(8,17,2,3),(9,18,1,2),(10,19,3,5),(11,20,1,2),(12,20,2,2),(13,20,3,2),(14,20,4,2),(15,21,2,3),(16,21,3,6),(17,22,3,3),(18,22,2,1),(19,23,7,3),(20,23,2,4),(21,23,1,5),(22,23,4,2),(23,23,3,4),(24,24,1,2),(25,25,2,1),(26,26,8,3),(27,26,7,2),(28,26,4,3),(29,26,3,1),(30,26,2,1),(31,26,1,3),(32,27,9,3),(33,27,7,2),(34,27,3,1),(35,28,2,2),(36,29,7,3),(37,29,9,4),(38,29,8,6);
/*!40000 ALTER TABLE `vendadetalhes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'baseappvendas'
--

--
-- Dumping routines for database 'baseappvendas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 14:05:58
