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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
  `Telefono` varchar(11) NOT NULL,
  `ReferidoId` int(11) NOT NULL,
  `DireccionId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ReferidoID` (`ReferidoId`),
  KEY `DireccionID` (`DireccionId`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`ReferidoId`) REFERENCES `referidos` (`Id`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`DireccionId`) REFERENCES `direcciones` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES
(2,'Juan Elias Cordoba','5551234',1,1),
(4,'alioo','a',2,5),
(5,'Juan Pérez','555-1234',3,25),
(6,'Juan Jose Talador','555-1234',4,27),
(7,'Uziel Isaac Larez','669',5,29),
(8,'Benjamin Flores Reyes','555-1234',6,31),
(9,'Jorge Armando Avila Carril','6692653037',7,33),
(10,'sadsa','asdasd',8,35);
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
  `ProductoId` int(11) NOT NULL,
  `Enganche` float DEFAULT NULL,
  `MicaId` int(11) NOT NULL,
  `MetodoDePago` enum('Credito','Contado') NOT NULL,
  `Tinte` varchar(20) DEFAULT NULL,
  `VentaId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ProductoID` (`ProductoId`),
  KEY `detallesventa_ibfk` (`MicaId`),
  KEY `VentaId` (`VentaId`),
  CONSTRAINT `detallesventa_ibfk` FOREIGN KEY (`MicaId`) REFERENCES `micas` (`Id`),
  CONSTRAINT `detallesventa_ibfk_2` FOREIGN KEY (`ProductoId`) REFERENCES `productos` (`Id`),
  CONSTRAINT `detallesventa_ibfk_3` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallesventa`
--

LOCK TABLES `detallesventa` WRITE;
/*!40000 ALTER TABLE `detallesventa` DISABLE KEYS */;
INSERT INTO `detallesventa` VALUES
(3,1,200,5,'Credito','Gris',7);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
INSERT INTO `diagnostico` VALUES
(2,1,'CR-39','AR','MONOFOCAL',1,1),
(3,2,'CR-39','AR','MONOFOCAL',2,2),
(4,3,'CR-39','AR','MONOFOCAL',3,3),
(5,4,'A INDEX','PHOTOCROMATICO','BIFOCAL',4,4),
(6,5,'A INDEX','PHOTOCROMATICO','BIFOCAL',5,5),
(7,6,'CR-39','PHOTOCROMATICO','BIFOCAL',6,6),
(8,7,'POLICARBONATO','AR','BIFOCAL',7,7);
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
  `Calle` varchar(100) NOT NULL,
  `CodigoPostal` varchar(11) NOT NULL,
  `EntreCalles` varchar(200) DEFAULT NULL,
  `Colonia` varchar(50) NOT NULL,
  `Referencia` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES
(1,'Calle Falsa 123','01234','Entre Av. A y Av. B','Colonia Centro','Frente al parque'),
(2,'Calle Verdadera 456','56789','Entre Av. X y Av. Y','Colonia Sur','Cerca de la iglesia'),
(5,'a','a','a','a','a'),
(6,'a','a','a','a','a'),
(7,'a','a','a','a','a'),
(8,'a','a','a','a','a'),
(25,'Calle Falsa 123','12345','Entre Calle A y B','Colonia Centro','Cerca del parque'),
(26,'Avenida Siempre Viva 456','54321','Entre Avenida C y D','Colonia Nueva','Cerca de la escuela'),
(27,'Calle Falsa 123','1777','Entre Calle A y B','Colonia Centro','Cerca del parque'),
(28,'Avenida Siempre Viva 456','54321','Entre Avenida C y D','Colonia Nueva','Cerca de la escuela'),
(29,'limon','82456','saman y almendro','hacienda el semi','casa verde de 8 pisos'),
(30,'','','','',''),
(31,'Calle Falsa 123','12345','Entre Calle A y B','Colonia Centro','Cerca del parque'),
(32,'Avenida Siempre Viva 456','54321','Entre Avenida C y D','Colonia Nueva','Cerca de la escuela'),
(33,'Juan Perez 20226','8754','Elias Alvarez','Fransico Villa','Juantio Alcachofas'),
(34,'Hola pApuaaa','','','kasjd',''),
(35,'asdasd','asdasd','asdasdas','asdas','asdasd'),
(36,'aasdasd','','','','');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
(5,'Mica #5',2700,3200),
(6,'Mica #6',800,800);
/*!40000 ALTER TABLE `micas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `obtener_clientes_detalle`
--

DROP TABLE IF EXISTS `obtener_clientes_detalle`;
/*!50001 DROP VIEW IF EXISTS `obtener_clientes_detalle`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `obtener_clientes_detalle` AS SELECT
 1 AS `clienteId`,
  1 AS `clienteNombre`,
  1 AS `clienteTelefono`,
  1 AS `referidoNombre`,
  1 AS `referidoTelefono`,
  1 AS `referidoRelacion`,
  1 AS `direccionClienteCalleNumero`,
  1 AS `direccionClienteCodigoPostal`,
  1 AS `direccionClienteEntreCalles`,
  1 AS `direccionClienteColonia`,
  1 AS `direccionClienteReferencia`,
  1 AS `direccionReferidoCalleNumero`,
  1 AS `direccionReferidoCodigoPostal`,
  1 AS `direccionReferidoEntreCalles`,
  1 AS `direccionReferidoColonia`,
  1 AS `direccionReferidoReferencia` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `obtener_micas`
--

DROP TABLE IF EXISTS `obtener_micas`;
/*!50001 DROP VIEW IF EXISTS `obtener_micas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `obtener_micas` AS SELECT
 1 AS `Id`,
  1 AS `Tipo`,
  1 AS `Contado`,
  1 AS `Credito` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `obtener_productos`
--

DROP TABLE IF EXISTS `obtener_productos`;
/*!50001 DROP VIEW IF EXISTS `obtener_productos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `obtener_productos` AS SELECT
 1 AS `Id`,
  1 AS `Nombre`,
  1 AS `Stock`,
  1 AS `PrecioContado`,
  1 AS `PrecioCredito`,
  1 AS `Descripcion` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `obtener_venta_detalles`
--

DROP TABLE IF EXISTS `obtener_venta_detalles`;
/*!50001 DROP VIEW IF EXISTS `obtener_venta_detalles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `obtener_venta_detalles` AS SELECT
 1 AS `Id`,
  1 AS `NombreCliente`,
  1 AS `NombrePaciente`,
  1 AS `NombreProducto`,
  1 AS `NombreMica`,
  1 AS `Tinte`,
  1 AS `MetodoPago`,
  1 AS `PeriodoAbonos`,
  1 AS `SaldoActual`,
  1 AS `CostoTotal`,
  1 AS `Enganche`,
  1 AS `FechaVenta` */;
SET character_set_client = @saved_cs_client;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ojoderecho`
--

LOCK TABLES `ojoderecho` WRITE;
/*!40000 ALTER TABLE `ojoderecho` DISABLE KEYS */;
INSERT INTO `ojoderecho` VALUES
(1,1.5,0.5,0.25,1.5,85),
(2,1.5,0.5,0.25,1.5,85),
(3,2.5,-1,2,60,180),
(4,47,7,14,12,4),
(5,47,7,14,12,4),
(6,122,47,45,5,14),
(7,20,0,2,15,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ojoizquierdo`
--

LOCK TABLES `ojoizquierdo` WRITE;
/*!40000 ALTER TABLE `ojoizquierdo` DISABLE KEYS */;
INSERT INTO `ojoizquierdo` VALUES
(1,1.5,0.5,0.25,1.5,85),
(2,1.5,0.5,0.25,1.5,85),
(3,2.75,-1.25,2.5,62,170),
(4,-5,16,14,12,10),
(5,-5,16,14,12,10),
(6,122,47,45,5,14),
(7,20,0,2,15,0);
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
  `Nombre` varchar(50) NOT NULL,
  `ClienteId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ClienteID` (`ClienteId`),
  CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`ClienteId`) REFERENCES `clientes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES
(1,'Pedro Gómezzz',4),
(2,'Jorge Avila',2),
(3,'Carlos Pérez',5),
(4,'Pedrito Alcachofas',9),
(5,'Pedrito Alcachofas',9),
(6,'Tilin',9),
(7,'Juniuilador',2);
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `pacientes_detalles`
--

DROP TABLE IF EXISTS `pacientes_detalles`;
/*!50001 DROP VIEW IF EXISTS `pacientes_detalles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `pacientes_detalles` AS SELECT
 1 AS `DiagnosticoId`,
  1 AS `PacienteId`,
  1 AS `NombrePaciente`,
  1 AS `NombreCliente`,
  1 AS `Material`,
  1 AS `Tratamiento`,
  1 AS `Lente`,
  1 AS `EsferaDerecho`,
  1 AS `CilindroDerecho`,
  1 AS `AdicionDerecho`,
  1 AS `DIDerecho`,
  1 AS `EjeDerecho`,
  1 AS `EsferaIzquierdo`,
  1 AS `CilindroIzquierdo`,
  1 AS `AdicionIzquierdo`,
  1 AS `DIIzquierdo`,
  1 AS `EjeIzquierdo` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Stock` int(11) NOT NULL,
  `PrecioContado` float NOT NULL,
  `PrecioCredito` float NOT NULL,
  `Descripcion` text DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES
(1,'Lente #1',90,1200,1800,''),
(2,'Lente #2',0,1600,2250,NULL),
(3,'Lente #3',0,1950,2750,NULL),
(4,'Lente #4',12,2400,3100,'null'),
(5,'Lente #5',0,3350,4200,NULL),
(6,'Lente #6',2,800,1600,'Lentes rayo mcqueen'),
(7,'Lente #7',0,500,3100,'Lentes perrornes de barbie'),
(8,'Lente 6',8,500,500,'Lente 7');
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
  `Telefono` varchar(11) DEFAULT NULL,
  `Relacion` enum('Esposo(a)','Hijo(a)','Madre','Padre','Tia(o)','Otro') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `DireccionID` (`DireccionId`),
  CONSTRAINT `referidos_ibfk_1` FOREIGN KEY (`DireccionId`) REFERENCES `direcciones` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referidos`
--

LOCK TABLES `referidos` WRITE;
/*!40000 ALTER TABLE `referidos` DISABLE KEYS */;
INSERT INTO `referidos` VALUES
(1,2,'Carlos López','5555678','Otro'),
(2,2,'a','a','Esposo(a)'),
(3,26,'Maria Gómez','555-5678','Madre'),
(4,28,'Maria Gómez','555-5678','Madre'),
(5,30,'Mama','669','Madre'),
(6,32,'Maria Gómez','555-5678','Padre'),
(7,34,'Benjamin Flores Reyes','6692658741','Madre'),
(8,36,'asdasd','asd','Madre');
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
  `FechaVenta` date NOT NULL,
  `TotalVenta` float NOT NULL,
  `SaldoActual` float NOT NULL,
  `PlanDePagos` enum('Semanal','Mensual','Quincenal') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ClienteID` (`PacienteId`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`PacienteId`) REFERENCES `pacientes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES
(7,2,'2024-08-11',4800,4800,'Mensual');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `simo`.`actualizar_stock` AFTER INSERT ON ventas
FOR EACH ROW
BEGIN
    DECLARE producto_id INT;

    -- Obtén el ProductoId del detalle de la venta asociada
    SELECT ProductoId INTO producto_id
    FROM detallesventa
    WHERE Id = NEW.Id;

    -- Reduce el stock del producto
    UPDATE productos
    SET Stock = Stock - 1
    WHERE Id = producto_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `obtener_clientes_detalle`
--

/*!50001 DROP VIEW IF EXISTS `obtener_clientes_detalle`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `obtener_clientes_detalle` AS (select `c`.`Id` AS `clienteId`,`c`.`Nombre` AS `clienteNombre`,`c`.`Telefono` AS `clienteTelefono`,`r`.`Nombre` AS `referidoNombre`,`r`.`Telefono` AS `referidoTelefono`,`r`.`Relacion` AS `referidoRelacion`,`d_cliente`.`Calle` AS `direccionClienteCalleNumero`,`d_cliente`.`CodigoPostal` AS `direccionClienteCodigoPostal`,`d_cliente`.`EntreCalles` AS `direccionClienteEntreCalles`,`d_cliente`.`Colonia` AS `direccionClienteColonia`,`d_cliente`.`Referencia` AS `direccionClienteReferencia`,`d_referido`.`Calle` AS `direccionReferidoCalleNumero`,`d_referido`.`CodigoPostal` AS `direccionReferidoCodigoPostal`,`d_referido`.`EntreCalles` AS `direccionReferidoEntreCalles`,`d_referido`.`Colonia` AS `direccionReferidoColonia`,`d_referido`.`Referencia` AS `direccionReferidoReferencia` from (((`clientes` `c` left join `referidos` `r` on(`c`.`ReferidoId` = `r`.`Id`)) left join `direcciones` `d_cliente` on(`c`.`DireccionId` = `d_cliente`.`Id`)) left join `direcciones` `d_referido` on(`r`.`DireccionId` = `d_referido`.`Id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `obtener_micas`
--

/*!50001 DROP VIEW IF EXISTS `obtener_micas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`simo`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `obtener_micas` AS (select `micas`.`Id` AS `Id`,`micas`.`Tipo` AS `Tipo`,`micas`.`Contado` AS `Contado`,`micas`.`Credito` AS `Credito` from `micas`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `obtener_productos`
--

/*!50001 DROP VIEW IF EXISTS `obtener_productos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`simo`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `obtener_productos` AS (select `productos`.`Id` AS `Id`,`productos`.`Nombre` AS `Nombre`,`productos`.`Stock` AS `Stock`,`productos`.`PrecioContado` AS `PrecioContado`,`productos`.`PrecioCredito` AS `PrecioCredito`,`productos`.`Descripcion` AS `Descripcion` from `productos`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `obtener_venta_detalles`
--

/*!50001 DROP VIEW IF EXISTS `obtener_venta_detalles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `obtener_venta_detalles` AS (select `ventas`.`Id` AS `Id`,`clientes`.`Nombre` AS `NombreCliente`,`pacientes`.`Nombre` AS `NombrePaciente`,`productos`.`Nombre` AS `NombreProducto`,`micas`.`Tipo` AS `NombreMica`,`detallesventa`.`Tinte` AS `Tinte`,`detallesventa`.`MetodoDePago` AS `MetodoPago`,`ventas`.`PlanDePagos` AS `PeriodoAbonos`,`ventas`.`SaldoActual` AS `SaldoActual`,`ventas`.`TotalVenta` AS `CostoTotal`,`detallesventa`.`Enganche` AS `Enganche`,`ventas`.`FechaVenta` AS `FechaVenta` from (((((`ventas` join `pacientes` on(`ventas`.`PacienteId` = `pacientes`.`Id`)) join `clientes` on(`pacientes`.`ClienteId` = `clientes`.`Id`)) join `detallesventa` on(`detallesventa`.`VentaId` = `ventas`.`Id`)) join `productos` on(`detallesventa`.`ProductoId` = `productos`.`Id`)) left join `micas` on(`detallesventa`.`MicaId` = `micas`.`Id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pacientes_detalles`
--

/*!50001 DROP VIEW IF EXISTS `pacientes_detalles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pacientes_detalles` AS (select `d`.`Id` AS `DiagnosticoId`,`p`.`Id` AS `PacienteId`,`p`.`Nombre` AS `NombrePaciente`,`c`.`Nombre` AS `NombreCliente`,`d`.`Material` AS `Material`,`d`.`Tratamiento` AS `Tratamiento`,`d`.`Lente` AS `Lente`,`od`.`Esfera` AS `EsferaDerecho`,`od`.`Cilindro` AS `CilindroDerecho`,`od`.`Adicion` AS `AdicionDerecho`,`od`.`DI` AS `DIDerecho`,`od`.`Eje` AS `EjeDerecho`,`oi`.`Esfera` AS `EsferaIzquierdo`,`oi`.`Cilindro` AS `CilindroIzquierdo`,`oi`.`Adicion` AS `AdicionIzquierdo`,`oi`.`DI` AS `DIIzquierdo`,`oi`.`Eje` AS `EjeIzquierdo` from ((((`diagnostico` `d` join `pacientes` `p` on(`d`.`PacienteId` = `p`.`Id`)) join `clientes` `c` on(`p`.`ClienteId` = `c`.`Id`)) left join `ojoderecho` `od` on(`d`.`OjoDerechoId` = `od`.`Id`)) left join `ojoizquierdo` `oi` on(`d`.`OjoIzquierdoId` = `oi`.`Id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-11 20:44:13
