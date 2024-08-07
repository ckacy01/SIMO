-- MariaDB dump 10.19-11.3.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: simo
-- ------------------------------------------------------
-- Server version	11.3.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonos`
--

DROP TABLE IF EXISTS `abonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abonos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VentaId` int(11) NOT NULL,
  `FechaAbono` date NOT NULL,
  `Monto` float NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `VentaID` (`VentaId`),
  CONSTRAINT `abonos_ibfk_1` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonos`
--

LOCK TABLES `abonos` WRITE;
/*!40000 ALTER TABLE `abonos` DISABLE KEYS */;
/*!40000 ALTER TABLE `abonos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `Telefono` int(11) NOT NULL,
  `ReferidoId` int(11) DEFAULT NULL,
  `DireccionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ReferidoID` (`ReferidoId`),
  KEY `DireccionID` (`DireccionId`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`ReferidoId`) REFERENCES `referidos` (`Id`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`DireccionId`) REFERENCES `direcciones` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallesventa`
--

DROP TABLE IF EXISTS `detallesventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallesventa` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VentaId` int(11) NOT NULL,
  `ProductoId` int(11) NOT NULL,
  `PlanId` int(11) NOT NULL,
  `ClienteId` int(11) NOT NULL,
  `Enganche` float DEFAULT NULL,
  `MicaId` int(11) NOT NULL,
  `MetodoDePago` enum('Credito','Contado') NOT NULL,
  `Tinte` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `VentaID` (`VentaId`),
  KEY `ProductoID` (`ProductoId`),
  KEY `PlanID` (`PlanId`),
  KEY `ClienteID` (`ClienteId`),
  KEY `detallesventa_ibfk` (`MicaId`),
  CONSTRAINT `detallesventa_ibfk` FOREIGN KEY (`MicaId`) REFERENCES `micas` (`Id`),
  CONSTRAINT `detallesventa_ibfk_1` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`),
  CONSTRAINT `detallesventa_ibfk_2` FOREIGN KEY (`ProductoId`) REFERENCES `productos` (`Id`),
  CONSTRAINT `detallesventa_ibfk_3` FOREIGN KEY (`PlanId`) REFERENCES `plandepagos` (`Id`),
  CONSTRAINT `detallesventa_ibfk_4` FOREIGN KEY (`ClienteId`) REFERENCES `clientes` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallesventa`
--

LOCK TABLES `detallesventa` WRITE;
/*!40000 ALTER TABLE `detallesventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallesventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico`
--

DROP TABLE IF EXISTS `diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostico` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PacienteId` int(11) NOT NULL,
  `Material` enum('CR-39','A INDEX','POLICARBONATO','OTRO') NOT NULL,
  `Tratamiento` enum('AR','PHOTOCROMATICO','SIN TRATAMIENTO') NOT NULL,
  `Lente` enum('MONOFOCAL','BIFOCAL','INVISIBLE','PROGRESIVO') NOT NULL,
  `OjoDerechoId` int(11) DEFAULT NULL,
  `OjoIzquierdoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `PacienteID` (`PacienteId`),
  KEY `OjoDerechoID` (`OjoDerechoId`),
  KEY `OjoIzquierdoID` (`OjoIzquierdoId`),
  CONSTRAINT `diagnostico_ibfk_1` FOREIGN KEY (`PacienteID`) REFERENCES `pacientes` (`Id`),
  CONSTRAINT `diagnostico_ibfk_2` FOREIGN KEY (`OjoDerechoID`) REFERENCES `ojoderecho` (`Id`),
  CONSTRAINT `diagnostico_ibfk_3` FOREIGN KEY (`OjoIzquierdoID`) REFERENCES `ojoizquierdo` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direcciones` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(50) NOT NULL,
  `CodigoPostal` int(11) NOT NULL,
  `NumeroInterior` int(11) DEFAULT NULL,
  `NumeroExterior` int(11) DEFAULT NULL,
  `EntreCalles` varchar(50) DEFAULT NULL,
  `Colonia` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `micas`
--

DROP TABLE IF EXISTS `micas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `micas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(50) NOT NULL,
  `Contado` float NOT NULL,
  `Credito` float NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `micas`
--

LOCK TABLES `micas` WRITE;
/*!40000 ALTER TABLE `micas` DISABLE KEYS */;
INSERT INTO `micas` VALUES
(1,'Mica #1',900,1500),
(2,'Mica #2',1150,1450),
(3,'Mica #3',1500,1950),
(4,'Mica #4',2100,2600),
(5,'Mica #5',2700,3200);
/*!40000 ALTER TABLE `micas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ojoderecho`
--

DROP TABLE IF EXISTS `ojoderecho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ojoderecho` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Esfera` float DEFAULT NULL,
  `Cilindro` float DEFAULT NULL,
  `Adicion` float DEFAULT NULL,
  `DI` float DEFAULT NULL,
  `Eje` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ojoderecho`
--

LOCK TABLES `ojoderecho` WRITE;
/*!40000 ALTER TABLE `ojoderecho` DISABLE KEYS */;
/*!40000 ALTER TABLE `ojoderecho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ojoizquierdo`
--

DROP TABLE IF EXISTS `ojoizquierdo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ojoizquierdo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Esfera` float DEFAULT NULL,
  `Cilindro` float DEFAULT NULL,
  `Adicion` float DEFAULT NULL,
  `DI` float DEFAULT NULL,
  `Eje` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ojoizquierdo`
--

LOCK TABLES `ojoizquierdo` WRITE;
/*!40000 ALTER TABLE `ojoizquierdo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ojoizquierdo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `ClienteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ClienteID` (`ClienteId`),
  CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`ClienteId`) REFERENCES `clientes` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plandepagos`
--

DROP TABLE IF EXISTS `plandepagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plandepagos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VentaId` int(11) NOT NULL,
  `TipoPlan` enum('Semanal','Quincenal','Mensual') DEFAULT NULL,
  `FechaInicio` date DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `VentaID` (`VentaId`),
  CONSTRAINT `plandepagos_ibfk_1` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plandepagos`
--

LOCK TABLES `plandepagos` WRITE;
/*!40000 ALTER TABLE `plandepagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `plandepagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `PrecioContado` float DEFAULT NULL,
  `PrecioCredito` float DEFAULT NULL,
  `Descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES
(1,'Lente #1',0,1200,1800,''),
(2,'Lente #2',0,1600,2250,NULL),
(3,'Lente #3',0,1950,2750,NULL),
(4,'Lente #4',0,2400,3100,NULL),
(5,'Lente #5',0,3350,4200,NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referidos`
--

DROP TABLE IF EXISTS `referidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referidos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DireccionId` int(11) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `Relacion` enum('Esposo(a)','Hijo(a)','Madre','Padre','Tia(o)','Otro') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `DireccionID` (`DireccionId`),
  CONSTRAINT `referidos_ibfk_1` FOREIGN KEY (`DireccionId`) REFERENCES `direcciones` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referidos`
--

LOCK TABLES `referidos` WRITE;
/*!40000 ALTER TABLE `referidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `referidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(50) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES
(1,'usuario','usuario'),
(2,'simo','simo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PacienteId` int(11) NOT NULL,
  `PlanDePagosId` int(11) NOT NULL,
  `FechaVenta` date NOT NULL,
  `TotalVenta` float NOT NULL,
  `SaldoActual` float NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ClienteID` (`PacienteId`),
  KEY `PlanDePagosID` (`PlanDePagosId`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`PacienteId`) REFERENCES `pacientes` (`Id`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`PlanDePagosId`) REFERENCES `plandepagos` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-07  0:59:49
