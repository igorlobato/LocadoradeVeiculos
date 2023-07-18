-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banco_de_dados_locadora_de_veiculos
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `aluguel`
--

DROP TABLE IF EXISTS `aluguel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluguel` (
  `idaluguel` int NOT NULL AUTO_INCREMENT,
  `veiculo` varchar(45) NOT NULL,
  `dataSaida` varchar(45) NOT NULL,
  `dataDevolucao` varchar(45) NOT NULL,
  `quantidadeDiarias` int NOT NULL,
  `cliente` varchar(45) NOT NULL,
  `ativo` tinyint NOT NULL,
  `valorPagar` double NOT NULL,
  `multa` double NOT NULL,
  `diasAtrasados` int NOT NULL,
  `quilometrosRodados` double NOT NULL,
  PRIMARY KEY (`idaluguel`),
  KEY `cliente_idx` (`cliente`),
  KEY `veiculo_idx` (`veiculo`),
  CONSTRAINT `cliente` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `veiculo` FOREIGN KEY (`veiculo`) REFERENCES `veiculo` (`placa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluguel`
--

LOCK TABLES `aluguel` WRITE;
/*!40000 ALTER TABLE `aluguel` DISABLE KEYS */;
INSERT INTO `aluguel` VALUES (2,'the','09/07/2023','18/07/2023',9,'Igo',1,2250,0,0,0),(3,'a','20/05/2023','30/05/2023',10,'Igo',1,2500,0,0,0);
/*!40000 ALTER TABLE `aluguel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `valorDiaria` double NOT NULL,
  `para` varchar(45) NOT NULL,
  PRIMARY KEY (`idcategoria`),
  KEY `idx_categoria` (`categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (2,'A','carro',250,'carros');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  `cpf` varchar(25) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `datadenascimento` varchar(45) NOT NULL,
  `sexo` varchar(45) NOT NULL,
  `anocnh` int NOT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `idx_nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (3,'Igo','r1','a','a','a','a',9),(4,'1','igor','a','a','m','m',5),(7,'Testenome','testecpgf','af','sgh','ag','addg',44),(8,'Eu','voce','um','dois','tres','M',8),(9,'Igor','10','5','6','7','8',9),(10,'Xablau','02420834208','jardim','991919116','154254','m',123),(11,'Enoque','123','sdgsfdh','768','121424','M',1999),(12,'Joao','100','fdgsd','1111111','123','m',2),(13,'adf','asf','a','f','saf','sa',5);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `idveiculo` int NOT NULL AUTO_INCREMENT,
  `modelo` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `placa` varchar(45) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `anodefabricacao` int NOT NULL,
  `quilometragem` double NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `alugado` tinyint NOT NULL,
  `veiculo` varchar(45) NOT NULL,
  `numerodeportas` int DEFAULT NULL,
  `tipodecombustivel` varchar(45) DEFAULT NULL,
  `capacidadedoportamalas` int DEFAULT NULL,
  `cilindrada` int DEFAULT NULL,
  `tipodemotor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idveiculo`),
  KEY `categoria_idx` (`categoria`),
  KEY `idx_modelo` (`modelo`),
  KEY `idx_placa` (`placa`),
  KEY `placa` (`placa`),
  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`categoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (2,'This','is','the','last',55,99,'A',0,'moto',NULL,NULL,NULL,990,'Time'),(3,'A','e','a','e',2020,55,'A',1,'carro',50,'99',88,NULL,NULL);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-18 19:40:50
