/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 11.3.2-MariaDB : Database - simo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`simo` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `simo`;

/*Table structure for table `abonos` */

DROP TABLE IF EXISTS `abonos`;

CREATE TABLE `abonos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VentaId` int(11) NOT NULL,
  `FechaAbono` date NOT NULL,
  `Monto` float NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `VentaID` (`VentaId`),
  CONSTRAINT `abonos_ibfk_1` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

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

/*Table structure for table `detallesventa` */

DROP TABLE IF EXISTS `detallesventa`;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `diagnostico` */

DROP TABLE IF EXISTS `diagnostico`;

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

/*Table structure for table `direcciones` */

DROP TABLE IF EXISTS `direcciones`;

CREATE TABLE `direcciones` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(100) NOT NULL,
  `CodigoPostal` varchar(11) NOT NULL,
  `EntreCalles` varchar(200) DEFAULT NULL,
  `Colonia` varchar(50) NOT NULL,
  `Referencia` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `micas` */

DROP TABLE IF EXISTS `micas`;

CREATE TABLE `micas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(50) NOT NULL,
  `Contado` float NOT NULL,
  `Credito` float NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `ojoderecho` */

DROP TABLE IF EXISTS `ojoderecho`;

CREATE TABLE `ojoderecho` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Esfera` float DEFAULT NULL,
  `Cilindro` float DEFAULT NULL,
  `Adicion` float DEFAULT NULL,
  `DI` float DEFAULT NULL,
  `Eje` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `ojoizquierdo` */

DROP TABLE IF EXISTS `ojoizquierdo`;

CREATE TABLE `ojoizquierdo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Esfera` float DEFAULT NULL,
  `Cilindro` float DEFAULT NULL,
  `Adicion` float DEFAULT NULL,
  `DI` float DEFAULT NULL,
  `Eje` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `pacientes` */

DROP TABLE IF EXISTS `pacientes`;

CREATE TABLE `pacientes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `ClienteId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ClienteID` (`ClienteId`),
  CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`ClienteId`) REFERENCES `clientes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `productos` */

DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Stock` int(11) NOT NULL,
  `PrecioContado` float NOT NULL,
  `PrecioCredito` float NOT NULL,
  `Descripcion` text DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `referidos` */

DROP TABLE IF EXISTS `referidos`;

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

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(50) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `ventas` */

DROP TABLE IF EXISTS `ventas`;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/* Trigger structure for table `ventas` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `actualizar_stock` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `actualizar_stock` AFTER INSERT ON `ventas` FOR EACH ROW 
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
END */$$


DELIMITER ;

/* Function  structure for function  `obtener_id_cliente_por_nombre` */

/*!50003 DROP FUNCTION IF EXISTS `obtener_id_cliente_por_nombre` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `obtener_id_cliente_por_nombre`(nombre_cliente Varchar(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
    DECLARE cliente_id INT;
    SET cliente_id = (
        SELECT Id FROM clientes
        WHERE Nombre = nombre_cliente
        LIMIT 1
    );
    RETURN cliente_id;

    END */$$
DELIMITER ;

/* Function  structure for function  `obtener_nombres_pacientes` */

/*!50003 DROP FUNCTION IF EXISTS `obtener_nombres_pacientes` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `obtener_nombres_pacientes`(patron VARCHAR(255)) RETURNS text CHARSET latin1 COLLATE latin1_swedish_ci
    DETERMINISTIC
BEGIN
    DECLARE nombres TEXT;
    SET nombres = (
        SELECT GROUP_CONCAT(nombre SEPARATOR ', ')
        FROM pacientes
        WHERE nombre LIKE CONCAT(patron, '%')
        ORDER BY nombre
        LIMIT 5
    );
    RETURN nombres;

    END */$$
DELIMITER ;

/* Function  structure for function  `obtener_nombre_clientes` */

/*!50003 DROP FUNCTION IF EXISTS `obtener_nombre_clientes` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `obtener_nombre_clientes`(patron varchar(255)) RETURNS text CHARSET latin1 COLLATE latin1_swedish_ci
    DETERMINISTIC
BEGIN
    DECLARE nombres TEXT;
    SET nombres = (
        SELECT GROUP_CONCAT(nombre SEPARATOR ', ')
        FROM clientes
        WHERE nombre LIKE CONCAT(patron, '%')
        ORDER BY nombre
        LIMIT 5
    );
    RETURN nombres;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `actualizar_venta` */

/*!50003 DROP PROCEDURE IF EXISTS  `actualizar_venta` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_venta`(    IN p_IdVenta INT,
    IN p_ProductoNombre VARCHAR(30),
    IN p_Enganche FLOAT,
    IN p_MicaNombre VARCHAR(50),
    IN p_MetodoDePago ENUM('Credito', 'Contado'),
    IN p_Tinte VARCHAR(20),
    IN p_FechaVenta DATE,
    IN p_TotalVenta FLOAT,
    IN p_SaldoActual FLOAT,
    IN p_PlanDePagos ENUM('Semanal', 'Mensual', 'Quincenal')
)
BEGIN
    DECLARE v_ProductoId INT;
    DECLARE v_MicaId INT;
    DECLARE v_StockActual INT;
    DECLARE v_ProductoPrevioId INT;
    DECLARE v_StockPrevio INT;

    -- Maneja cualquier posible error
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- En caso de error, deshace todos los cambios
        ROLLBACK;
    END;
    
    -- Inicia la transacción
    START TRANSACTION;

    -- Obtiene el ID del producto actual por su nombre
    SELECT Id INTO v_ProductoId
    FROM productos
    WHERE Nombre = p_ProductoNombre;

    -- Obtiene el ID de la mica por su nombre
    SELECT Id INTO v_MicaId
    FROM micas
    WHERE Tipo = p_MicaNombre;

    -- Obtiene el ID del producto previo asociado a la venta
    SELECT ProductoId INTO v_ProductoPrevioId
    FROM detallesventa
    WHERE VentaId = p_IdVenta;

    -- Obtiene el stock actual del producto
    SELECT Stock INTO v_StockActual
    FROM productos
    WHERE Id = v_ProductoId;

    -- Verifica si hay suficiente stock
    IF v_StockActual <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No hay stock suficiente para el producto.';
    END IF;

    -- Actualiza el stock del producto previo
    IF v_ProductoPrevioId IS NOT NULL THEN
        -- Obtiene el stock del producto previo
        SELECT Stock INTO v_StockPrevio
        FROM productos
        WHERE Id = v_ProductoPrevioId;

        -- Actualiza el stock del producto previo (suma 1 unidad)
        UPDATE productos
        SET Stock = v_StockPrevio + 1
        WHERE Id = v_ProductoPrevioId;
    END IF;

    -- Actualiza la tabla detallesventa para todas las entradas con la venta especificada
    UPDATE detallesventa
    SET ProductoId = v_ProductoId,
        Enganche = p_Enganche,
        MicaId = v_MicaId,
        MetodoDePago = p_MetodoDePago,
        Tinte = p_Tinte
    WHERE VentaId = p_IdVenta;

    -- Actualiza la tabla ventas con el ID de la venta especificada
    UPDATE ventas
    SET FechaVenta = p_FechaVenta,
        TotalVenta = p_TotalVenta,
        SaldoActual = p_SaldoActual,
        PlanDePagos = p_PlanDePagos
    WHERE Id = p_IdVenta;

    -- Actualiza el stock del producto actual
    UPDATE productos
    SET Stock = v_StockActual - 1 -- Asumiendo que cada venta disminuye el stock en 1 unidad
    WHERE Id = v_ProductoId;

    -- Confirma los cambios si todo ha ido bien
    COMMIT;
END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminar_abono` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminar_abono` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_abono`(IN p_AbonoID INT -- ID del abono que deseas eliminar
)
BEGIN
    DECLARE v_MontoAbono DECIMAL(18, 2);
    DECLARE v_VentaID INT;
	 DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Si ocurre un error, deshacer la transacción
        ROLLBACK;
        -- Lanzar un mensaje de error
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar el abono';
    END;

    -- Iniciar la transacción
    START TRANSACTION;

    
   
    -- Obtener el monto del abono y el VentaID antes de eliminar el abono
    SELECT Monto, VentaID
    INTO v_MontoAbono, v_VentaID
    FROM Abonos
    WHERE AbonoID = p_AbonoID;

    -- Eliminar el abono
    DELETE FROM Abonos
    WHERE AbonoID = p_AbonoID;

    -- Actualizar el saldo actual de la deuda en la tabla Ventas
    UPDATE Ventas
    SET SaldoActual = SaldoActual + v_MontoAbono
    WHERE VentaID = v_VentaID;

    -- Confirmar la transacción
    COMMIT;


	END */$$
DELIMITER ;

/* Procedure structure for procedure `InsertarClienteConReferido` */

/*!50003 DROP PROCEDURE IF EXISTS  `InsertarClienteConReferido` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarClienteConReferido`(
    IN p_NombreCliente VARCHAR(50),
    IN p_TelefonoCliente VARCHAR(11),
    IN p_CalleNumeroCliente VARCHAR(50),
    IN p_CodigoPostalCliente VARCHAR(11),
    IN p_EntreCallesCliente VARCHAR(50),
    IN p_ColoniaCliente VARCHAR(50),
    IN p_ReferenciaCliente VARCHAR(50),
    IN p_NombreReferido VARCHAR(50),
    IN p_TelefonoReferido VARCHAR(11),
    IN p_RelacionReferido ENUM('Esposo(a)','Hijo(a)','Madre','Padre','Tia(o)','Otro'),
    IN p_CalleNumeroReferido VARCHAR(50),
    IN p_CodigoPostalReferido VARCHAR(11),
    IN p_EntreCallesReferido VARCHAR(50),
    IN p_ColoniaReferido VARCHAR(50),
    IN p_ReferenciaReferido VARCHAR(50))
BEGIN
    DECLARE lastDireccionClienteId INT;
    DECLARE lastDireccionReferidoId INT;
    DECLARE lastReferidoId INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Rollback en caso de error
        ROLLBACK;
        -- Opcional: Registrar el error para depuración
        SELECT 'Error en la transacción' AS ErrorMessage;
    END;
    
    START TRANSACTION;

    -- Insertar la dirección del cliente
    INSERT INTO direcciones (Calle, CodigoPostal, EntreCalles, Colonia, Referencia)
    VALUES (p_CalleNumeroCliente, p_CodigoPostalCliente, p_EntreCallesCliente, p_ColoniaCliente, p_ReferenciaCliente);
    SET lastDireccionClienteId = LAST_INSERT_ID();

    -- Insertar la dirección del referido
    INSERT INTO direcciones (Calle, CodigoPostal, EntreCalles, Colonia, Referencia)
    VALUES (p_CalleNumeroReferido, p_CodigoPostalReferido, p_EntreCallesReferido, p_ColoniaReferido, p_ReferenciaReferido);
    SET lastDireccionReferidoId = LAST_INSERT_ID();

    -- Insertar el referido
    INSERT INTO referidos (DireccionId, Nombre, Telefono, Relacion)
    VALUES (lastDireccionReferidoId, p_NombreReferido, p_TelefonoReferido, p_RelacionReferido);
    SET lastReferidoId = LAST_INSERT_ID();

    -- Insertar el cliente
    INSERT INTO clientes (Nombre, Telefono, ReferidoId, DireccionId)
    VALUES (p_NombreCliente, p_TelefonoCliente, lastReferidoId, lastDireccionClienteId);
    
    COMMIT;
END */$$
DELIMITER ;

/* Procedure structure for procedure `insertar_abono` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertar_abono` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_abono`(IN p_VentaId INT,
    IN p_FechaAbono DATE,
    IN p_MontoAbono FLOAT
)
BEGIN
    DECLARE v_SaldoActual FLOAT;
	   -- Maneja cualquier posible error
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- En caso de error, deshace todos los cambios
        ROLLBACK;
    END;
    -- Inicia la transacción
    START TRANSACTION;

 

    -- Inserta el nuevo abono en la tabla abonos
    INSERT INTO abonos (VentaId, FechaAbono, Monto)
    VALUES (p_VentaId, p_FechaAbono, p_MontoAbono);

    -- Obtiene el saldo actual de la venta antes del abono
    SELECT SaldoActual INTO v_SaldoActual
    FROM ventas
    WHERE Id = p_VentaId;

    -- Actualiza el saldo actual restando el monto del abono
    UPDATE ventas
    SET SaldoActual = v_SaldoActual - p_MontoAbono
    WHERE Id = p_VentaId;

    -- Confirma los cambios si todo ha ido bien
    COMMIT;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `insertar_diagnostico` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertar_diagnostico` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_diagnostico`(
    IN nombre_cliente VARCHAR(50),
    IN nombre_paciente VARCHAR(50),
    IN material ENUM('CR-39','A INDEX','POLICARBONATO','OTRO'),
    IN tratamiento ENUM('AR','PHOTOCROMATICO','SIN TRATAMIENTO'),
    IN lente ENUM('MONOFOCAL','BIFOCAL','INVISIBLE','PROGRESIVO'),
    IN esfera_derecho FLOAT, IN cilindro_derecho FLOAT, IN adicion_derecho FLOAT, IN di_derecho FLOAT, IN eje_derecho FLOAT,
    IN esfera_izquierdo FLOAT, IN cilindro_izquierdo FLOAT, IN adicion_izquierdo FLOAT, IN di_izquierdo FLOAT, IN eje_izquierdo FLOAT
)
BEGIN
    DECLARE cliente_id INT;
    DECLARE paciente_id INT;
    DECLARE ojo_derecho_id INT;
    DECLARE ojo_izquierdo_id INT;
    Start transaction;
    -- Obtener el ClienteId basado en el nombre del cliente
    SET cliente_id = obtener_id_cliente_por_nombre(nombre_cliente);
    
    -- Insertar en la tabla de ojo derecho
    INSERT INTO ojoderecho (Esfera, Cilindro, Adicion, DI, Eje)
    VALUES (esfera_derecho, cilindro_derecho, adicion_derecho, di_derecho, eje_derecho);
    SET ojo_derecho_id = LAST_INSERT_ID();
    
    -- Insertar en la tabla de ojo izquierdo
    INSERT INTO ojoizquierdo (Esfera, Cilindro, Adicion, DI, Eje)
    VALUES (esfera_izquierdo, cilindro_izquierdo, adicion_izquierdo, di_izquierdo, eje_izquierdo);
    SET ojo_izquierdo_id = LAST_INSERT_ID();
    
    -- Insertar en la tabla de pacientes (si el cliente no tiene ya un paciente asociado)
    INSERT INTO pacientes (Nombre, ClienteId)
    VALUES (nombre_paciente, cliente_id)
    ON DUPLICATE KEY UPDATE Id = LAST_INSERT_ID(Id); 
    SET paciente_id = LAST_INSERT_ID();

    -- Insertar en la tabla de diagnostico
    INSERT INTO diagnostico (PacienteId, Material, Tratamiento, Lente, OjoDerechoId, OjoIzquierdoId)
    VALUES (paciente_id, material, tratamiento, lente, ojo_derecho_id, ojo_izquierdo_id);
    Commit;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `insertar_micas` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertar_micas` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`simo`@`%` PROCEDURE `insertar_micas`(
		IN p_Tipo varchar(50),
		in p_Contado float,
		in p_Credito FLOAT)
BEGIN
	
	Insert into micas (Tipo, Contado, Credito)
	Values (p_Tipo, p_Contado, p_Credito);

	END */$$
DELIMITER ;

/* Procedure structure for procedure `insertar_productos` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertar_productos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`simo`@`%` PROCEDURE `insertar_productos`(
	 IN p_Nombre VARCHAR(30),
    IN p_Stock INT,
    IN p_PrecioContado FLOAT,
    IN p_PrecioCredito FLOAT,
    IN p_Descripcion TEXT
)
BEGIN
    INSERT INTO Productos (Nombre, Stock, PrecioContado, PrecioCredito, Descripcion)
    VALUES (p_Nombre, p_Stock, p_PrecioContado, p_PrecioCredito, p_Descripcion);
END */$$
DELIMITER ;

/* Procedure structure for procedure `insertar_venta` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertar_venta` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_venta`(   IN p_nombre_paciente VARCHAR(50),
    IN p_fecha_venta DATE,
    IN p_total_venta FLOAT,
    IN p_saldo_actual FLOAT,
    IN p_plan_de_pagos ENUM('Semanal', 'Mensual', 'Quincenal'),
    -- Detalles de la venta
    IN p_nombre_producto VARCHAR(30),
    IN p_nombre_mica VARCHAR(50),
    IN p_enganche FLOAT,
    IN p_metodo_pago ENUM('Credito', 'Contado'),
    IN p_tinte VARCHAR(20)
)
BEGIN
    DECLARE v_paciente_id INT;
    DECLARE v_producto_id INT;
    DECLARE v_mica_id INT;
    DECLARE v_venta_id INT;
    DECLARE v_stock_actual INT;

    -- Inicia una transacción
    START TRANSACTION;

    -- Obtén el ID del paciente a partir del nombre
    SELECT Id INTO v_paciente_id
    FROM pacientes
    WHERE Nombre = p_nombre_paciente;

    -- Verifica si se encontró el paciente
    IF v_paciente_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Paciente no encontrado';
    END IF;

    -- Obtén el ID del producto a partir del nombre
    SELECT Id INTO v_producto_id
    FROM productos
    WHERE Nombre = p_nombre_producto;

    -- Verifica si se encontró el producto
    IF v_producto_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Producto no encontrado';
    END IF;

    -- Obtén el ID de la mica a partir del nombre
    SELECT Id INTO v_mica_id
    FROM micas
    WHERE Tipo = p_nombre_mica;

    -- Verifica si se encontró la mica
    IF v_mica_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mica no encontrada';
    END IF;

    -- Verifica el stock del producto
    SELECT Stock INTO v_stock_actual
    FROM productos
    WHERE Id = v_producto_id;

    -- Verifica si hay suficiente stock
    IF v_stock_actual <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay stock suficiente para el producto.';
    END IF;

    -- Inserta la nueva venta
    INSERT INTO ventas (PacienteId, FechaVenta, TotalVenta, SaldoActual, PlanDePagos)
    VALUES (v_paciente_id, p_fecha_venta, p_total_venta, p_saldo_actual, p_plan_de_pagos);

    -- Obtén el ID de la nueva venta
    SET v_venta_id = LAST_INSERT_ID();

    -- Inserta el detalle de la venta
    INSERT INTO detallesventa (ProductoId, Enganche, MicaId, MetodoDePago, Tinte, VentaId)
    VALUES (v_producto_id, p_enganche, v_mica_id, p_metodo_pago, p_tinte, v_venta_id);

    -- Confirma la transacción
    COMMIT;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarCliente` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarCliente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCliente`(
    IN p_clienteId INT,
    IN p_clienteNombre VARCHAR(50),
    IN p_clienteTelefono VARCHAR(11),
    IN p_direccionClienteCalle VARCHAR(100),
    IN p_direccionClienteCodigoPostal VARCHAR(11),
    IN p_direccionClienteEntreCalles VARCHAR(200),
    IN p_direccionClienteColonia VARCHAR(50),
    IN p_direccionClienteReferencia VARCHAR(400),
    IN p_referidoNombre VARCHAR(50),
    IN p_referidoTelefono VARCHAR(11),
    IN p_referidoRelacion ENUM('Esposo(a)','Hijo(a)','Madre','Padre','Tia(o)','Otro'),
    IN p_direccionReferidoCalle VARCHAR(100),
    IN p_direccionReferidoCodigoPostal VARCHAR(11),
    IN p_direccionReferidoEntreCalles VARCHAR(200),
    IN p_direccionReferidoColonia VARCHAR(50),
    IN p_direccionReferidoReferencia VARCHAR(400)
)
BEGIN
    DECLARE v_referidoId INT;
    DECLARE v_clienteDireccionId INT;
    DECLARE v_referidoDireccionId INT;

    -- Obtener la Id de la dirección del cliente
    SELECT DireccionId INTO v_clienteDireccionId
    FROM clientes
    WHERE Id = p_clienteId;

    -- Obtener la Id del referido y su dirección
    SELECT ReferidoId, r.DireccionId INTO v_referidoId, v_referidoDireccionId
    FROM clientes c
    JOIN referidos r ON c.ReferidoId = r.Id
    WHERE c.Id = p_clienteId;

    -- Iniciar la transacción
    START TRANSACTION;

    -- Actualizar la información del cliente
    UPDATE clientes
    SET 
        Nombre = p_clienteNombre,
        Telefono = p_clienteTelefono
    WHERE Id = p_clienteId;

    -- Actualizar la dirección del cliente
    UPDATE direcciones
    SET 
        Calle = p_direccionClienteCalle,
        CodigoPostal = p_direccionClienteCodigoPostal,
        EntreCalles = p_direccionClienteEntreCalles,
        Colonia = p_direccionClienteColonia,
        Referencia = p_direccionClienteReferencia
    WHERE Id = v_clienteDireccionId;

    -- Actualizar la información del referido
    UPDATE referidos
    SET 
        Nombre = p_referidoNombre,
        Telefono = p_referidoTelefono,
        Relacion = p_referidoRelacion
    WHERE Id = v_referidoId;

    -- Actualizar la dirección del referido
    UPDATE direcciones
    SET 
        Calle = p_direccionReferidoCalle,
        CodigoPostal = p_direccionReferidoCodigoPostal,
        EntreCalles = p_direccionReferidoEntreCalles,
        Colonia = p_direccionReferidoColonia,
        Referencia = p_direccionReferidoReferencia
    WHERE Id = v_referidoDireccionId;

    -- Finalizar la transacción
    COMMIT;
END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarPaciente` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarPaciente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarPaciente`(
    IN p_PacienteId INT,
    IN p_nombreCliente VARCHAR(50),
    IN p_nombrePaciente VARCHAR(50),
    IN p_material ENUM('CR-39','A INDEX','POLICARBONATO','OTRO'),
    IN p_tratamiento ENUM('AR','PHOTOCROMATICO','SIN TRATAMIENTO'),
    IN p_lente ENUM('MONOFOCAL','BIFOCAL','INVISIBLE','PROGRESIVO'),
    IN p_esferaDerecho FLOAT,
    IN p_cilindroDerecho FLOAT,
    IN p_adicionDerecho FLOAT,
    IN p_diDerecho FLOAT,
    IN p_ejeDerecho FLOAT,
    IN p_esferaIzquierdo FLOAT,
    IN p_cilindroIzquierdo FLOAT,
    IN p_adicionIzquierdo FLOAT,
    IN p_diIzquierdo FLOAT,
    IN p_ejeIzquierdo FLOAT
)
BEGIN
    DECLARE v_OjoDerechoId INT;
    DECLARE v_OjoIzquierdoId INT;
    DECLARE clienteid INT;
    
    START TRANSACTION;

    -- Obtener el ID del cliente por nombre
    SET clienteid = obtener_id_cliente_por_nombre(p_nombreCliente);

    -- Actualizar datos en la tabla `pacientes`
    UPDATE pacientes
    SET Nombre = p_nombrePaciente,
        ClienteId = clienteid
    WHERE Id = p_PacienteId;

    -- Verificar si existe un diagnóstico para el paciente
    IF EXISTS (SELECT 1 FROM diagnostico WHERE PacienteId = p_PacienteId) THEN
        -- Obtener los IDs actuales de ojo derecho e izquierdo del diagnóstico
        SELECT OjoDerechoId, OjoIzquierdoId INTO v_OjoDerechoId, v_OjoIzquierdoId
        FROM diagnostico
        WHERE PacienteId = p_PacienteId;
        
        -- Actualizar o insertar los datos del ojo derecho
        IF v_OjoDerechoId IS NULL THEN
            INSERT INTO ojoderecho (Esfera, Cilindro, Adicion, DI, Eje)
            VALUES (p_esferaDerecho, p_cilindroDerecho, p_adicionDerecho, p_diDerecho, p_ejeDerecho);
            
            SET v_OjoDerechoId = LAST_INSERT_ID();
        ELSE
            UPDATE ojoderecho
            SET
                Esfera = p_esferaDerecho,
                Cilindro = p_cilindroDerecho,
                Adicion = p_adicionDerecho,
                DI = p_diDerecho,
                Eje = p_ejeDerecho
            WHERE Id = v_OjoDerechoId;
        END IF;

        -- Actualizar o insertar los datos del ojo izquierdo
        IF v_OjoIzquierdoId IS NULL THEN
            INSERT INTO ojoizquierdo (Esfera, Cilindro, Adicion, DI, Eje)
            VALUES (p_esferaIzquierdo, p_cilindroIzquierdo, p_adicionIzquierdo, p_diIzquierdo, p_ejeIzquierdo);
            
            SET v_OjoIzquierdoId = LAST_INSERT_ID();
        ELSE
            UPDATE ojoizquierdo
            SET
                Esfera = p_esferaIzquierdo,
                Cilindro = p_cilindroIzquierdo,
                Adicion = p_adicionIzquierdo,
                DI = p_diIzquierdo,
                Eje = p_ejeIzquierdo
            WHERE Id = v_OjoIzquierdoId;
        END IF;
        
        -- Actualizar la tabla `diagnostico` con los nuevos IDs de ojo
        UPDATE diagnostico
        SET
            Material = p_material,
            Tratamiento = p_tratamiento,
            Lente = p_lente,
            OjoDerechoId = v_OjoDerechoId,
            OjoIzquierdoId = v_OjoIzquierdoId
        WHERE PacienteId = p_PacienteId;
    ELSE
        -- Insertar un nuevo diagnóstico si no existe
        INSERT INTO diagnostico (
            PacienteId, Material, Tratamiento, Lente, OjoDerechoId, OjoIzquierdoId
        ) VALUES (
            p_PacienteId, p_material, p_tratamiento, p_lente, v_OjoDerechoId, v_OjoIzquierdoId
        );
    END IF;
    
    COMMIT;
END */$$
DELIMITER ;

/* Procedure structure for procedure `modificar_abono` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificar_abono` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_abono`(  IN p_AbonoId INT,
    IN p_NuevoMonto FLOAT,
    IN p_NuevaFecha DATE
)
BEGIN
    DECLARE v_VentaId INT;
    DECLARE v_MontoOriginal FLOAT;
    DECLARE v_SaldoActual FLOAT;
    DECLARE v_DiferenciaMonto FLOAT;
		 DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- En caso de error, deshace todos los cambios
        ROLLBACK;
    END;
    -- Inicia la transacción
    START TRANSACTION;

    -- Maneja cualquier posible error
   

    -- Obtiene el ID de la venta y el monto original del abono
    SELECT VentaId, Monto INTO v_VentaId, v_MontoOriginal
    FROM abonos
    WHERE Id = p_AbonoId;

    -- Calcula la diferencia entre el monto original y el nuevo monto
    SET v_DiferenciaMonto = p_NuevoMonto - v_MontoOriginal;

    -- Actualiza el monto y la fecha del abono en la tabla abonos
    UPDATE abonos
    SET Monto = p_NuevoMonto,
        FechaAbono = p_NuevaFecha
    WHERE Id = p_AbonoId;

    -- Obtiene el saldo actual de la venta
    SELECT SaldoActual INTO v_SaldoActual
    FROM ventas
    WHERE Id = v_VentaId;

    -- Actualiza el saldo actual de la venta
    UPDATE ventas
    SET SaldoActual = v_SaldoActual - v_DiferenciaMonto
    WHERE Id = v_VentaId;

    -- Confirma los cambios si todo ha ido bien
    COMMIT;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `modificar_mica` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificar_mica` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`simo`@`%` PROCEDURE `modificar_mica`(
		in p_id int,
		in p_tipo varchar(50),
		in p_contado float,
		in p_credito float
		)
BEGIN
		Update micas 
		Set
			Tipo = p_tipo,
			Contado = p_contado,
			Credito = p_credito
		where Id = p_id;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `modificar_productos` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificar_productos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`simo`@`%` PROCEDURE `modificar_productos`(
		IN p_id INT,
		IN p_Nombre varchar(50),
		in p_Stock int,
		in p_Contado float,
		in p_Credito float,
		in p_Des TEXT
)
BEGIN
		Update productos 
		Set Nombre = p_Nombre, Stock = p_Stock, PrecioContado = p_Contado, PrecioCredito = p_Credito, Descripcion = p_Des
		where Id = p_id;
		
	END */$$
DELIMITER ;

/* Procedure structure for procedure `seleccionar_mica_productos` */

/*!50003 DROP PROCEDURE IF EXISTS  `seleccionar_mica_productos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `seleccionar_mica_productos`(In p_NombreProducto varchar(50), IN p_NombreMica varchar(50))
BEGIN
		(SELECT
    p.Id,
    p.Nombre AS Nombre,
    p.PrecioContado AS PrecioContado,
    p.PrecioCredito AS PrecioCredito
FROM
    productos p
WHERE
    p.Nombre = p_NombreProducto)

UNION

(SELECT
    m.Id,
    m.Tipo AS Nombre,
    m.Contado AS PrecioContado,
    m.Credito AS PrecioCredito
FROM
    micas m
WHERE
    m.Tipo = p_NombreMica);
	END */$$
DELIMITER ;

/*Table structure for table `obtener_clientes_detalle` */

DROP TABLE IF EXISTS `obtener_clientes_detalle`;

/*!50001 DROP VIEW IF EXISTS `obtener_clientes_detalle` */;
/*!50001 DROP TABLE IF EXISTS `obtener_clientes_detalle` */;

/*!50001 CREATE TABLE  `obtener_clientes_detalle`(
 `clienteId` int(11) ,
 `clienteNombre` varchar(50) ,
 `clienteTelefono` varchar(11) ,
 `referidoNombre` varchar(50) ,
 `referidoTelefono` varchar(11) ,
 `referidoRelacion` enum('Esposo(a)','Hijo(a)','Madre','Padre','Tia(o)','Otro') ,
 `direccionClienteCalleNumero` varchar(100) ,
 `direccionClienteCodigoPostal` varchar(11) ,
 `direccionClienteEntreCalles` varchar(200) ,
 `direccionClienteColonia` varchar(50) ,
 `direccionClienteReferencia` varchar(400) ,
 `direccionReferidoCalleNumero` varchar(100) ,
 `direccionReferidoCodigoPostal` varchar(11) ,
 `direccionReferidoEntreCalles` varchar(200) ,
 `direccionReferidoColonia` varchar(50) ,
 `direccionReferidoReferencia` varchar(400) 
)*/;

/*Table structure for table `obtener_micas` */

DROP TABLE IF EXISTS `obtener_micas`;

/*!50001 DROP VIEW IF EXISTS `obtener_micas` */;
/*!50001 DROP TABLE IF EXISTS `obtener_micas` */;

/*!50001 CREATE TABLE  `obtener_micas`(
 `Id` int(11) ,
 `Tipo` varchar(50) ,
 `Contado` float ,
 `Credito` float 
)*/;

/*Table structure for table `obtener_productos` */

DROP TABLE IF EXISTS `obtener_productos`;

/*!50001 DROP VIEW IF EXISTS `obtener_productos` */;
/*!50001 DROP TABLE IF EXISTS `obtener_productos` */;

/*!50001 CREATE TABLE  `obtener_productos`(
 `Id` int(11) ,
 `Nombre` varchar(30) ,
 `Stock` int(11) ,
 `PrecioContado` float ,
 `PrecioCredito` float ,
 `Descripcion` text 
)*/;

/*Table structure for table `obtener_venta_detalles` */

DROP TABLE IF EXISTS `obtener_venta_detalles`;

/*!50001 DROP VIEW IF EXISTS `obtener_venta_detalles` */;
/*!50001 DROP TABLE IF EXISTS `obtener_venta_detalles` */;

/*!50001 CREATE TABLE  `obtener_venta_detalles`(
 `Id` int(11) ,
 `NombreCliente` varchar(50) ,
 `NombrePaciente` varchar(50) ,
 `NombreProducto` varchar(30) ,
 `NombreMica` varchar(50) ,
 `Tinte` varchar(20) ,
 `MetodoPago` enum('Credito','Contado') ,
 `PeriodoAbonos` enum('Semanal','Mensual','Quincenal') ,
 `SaldoActual` float ,
 `CostoTotal` float ,
 `Enganche` float ,
 `FechaVenta` date 
)*/;

/*Table structure for table `pacientes_detalles` */

DROP TABLE IF EXISTS `pacientes_detalles`;

/*!50001 DROP VIEW IF EXISTS `pacientes_detalles` */;
/*!50001 DROP TABLE IF EXISTS `pacientes_detalles` */;

/*!50001 CREATE TABLE  `pacientes_detalles`(
 `DiagnosticoId` int(11) ,
 `PacienteId` int(11) ,
 `NombrePaciente` varchar(50) ,
 `NombreCliente` varchar(50) ,
 `Material` enum('CR-39','A INDEX','POLICARBONATO','OTRO') ,
 `Tratamiento` enum('AR','PHOTOCROMATICO','SIN TRATAMIENTO') ,
 `Lente` enum('MONOFOCAL','BIFOCAL','INVISIBLE','PROGRESIVO') ,
 `EsferaDerecho` float ,
 `CilindroDerecho` float ,
 `AdicionDerecho` float ,
 `DIDerecho` float ,
 `EjeDerecho` float ,
 `EsferaIzquierdo` float ,
 `CilindroIzquierdo` float ,
 `AdicionIzquierdo` float ,
 `DIIzquierdo` float ,
 `EjeIzquierdo` float 
)*/;

/*View structure for view obtener_clientes_detalle */

/*!50001 DROP TABLE IF EXISTS `obtener_clientes_detalle` */;
/*!50001 DROP VIEW IF EXISTS `obtener_clientes_detalle` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `obtener_clientes_detalle` AS (select `c`.`Id` AS `clienteId`,`c`.`Nombre` AS `clienteNombre`,`c`.`Telefono` AS `clienteTelefono`,`r`.`Nombre` AS `referidoNombre`,`r`.`Telefono` AS `referidoTelefono`,`r`.`Relacion` AS `referidoRelacion`,`d_cliente`.`Calle` AS `direccionClienteCalleNumero`,`d_cliente`.`CodigoPostal` AS `direccionClienteCodigoPostal`,`d_cliente`.`EntreCalles` AS `direccionClienteEntreCalles`,`d_cliente`.`Colonia` AS `direccionClienteColonia`,`d_cliente`.`Referencia` AS `direccionClienteReferencia`,`d_referido`.`Calle` AS `direccionReferidoCalleNumero`,`d_referido`.`CodigoPostal` AS `direccionReferidoCodigoPostal`,`d_referido`.`EntreCalles` AS `direccionReferidoEntreCalles`,`d_referido`.`Colonia` AS `direccionReferidoColonia`,`d_referido`.`Referencia` AS `direccionReferidoReferencia` from (((`clientes` `c` left join `referidos` `r` on(`c`.`ReferidoId` = `r`.`Id`)) left join `direcciones` `d_cliente` on(`c`.`DireccionId` = `d_cliente`.`Id`)) left join `direcciones` `d_referido` on(`r`.`DireccionId` = `d_referido`.`Id`))) */;

/*View structure for view obtener_micas */

/*!50001 DROP TABLE IF EXISTS `obtener_micas` */;
/*!50001 DROP VIEW IF EXISTS `obtener_micas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`simo`@`%` SQL SECURITY DEFINER VIEW `obtener_micas` AS (select `micas`.`Id` AS `Id`,`micas`.`Tipo` AS `Tipo`,`micas`.`Contado` AS `Contado`,`micas`.`Credito` AS `Credito` from `micas`) */;

/*View structure for view obtener_productos */

/*!50001 DROP TABLE IF EXISTS `obtener_productos` */;
/*!50001 DROP VIEW IF EXISTS `obtener_productos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`simo`@`%` SQL SECURITY DEFINER VIEW `obtener_productos` AS (select `productos`.`Id` AS `Id`,`productos`.`Nombre` AS `Nombre`,`productos`.`Stock` AS `Stock`,`productos`.`PrecioContado` AS `PrecioContado`,`productos`.`PrecioCredito` AS `PrecioCredito`,`productos`.`Descripcion` AS `Descripcion` from `productos`) */;

/*View structure for view obtener_venta_detalles */

/*!50001 DROP TABLE IF EXISTS `obtener_venta_detalles` */;
/*!50001 DROP VIEW IF EXISTS `obtener_venta_detalles` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `obtener_venta_detalles` AS (select `ventas`.`Id` AS `Id`,`clientes`.`Nombre` AS `NombreCliente`,`pacientes`.`Nombre` AS `NombrePaciente`,`productos`.`Nombre` AS `NombreProducto`,`micas`.`Tipo` AS `NombreMica`,`detallesventa`.`Tinte` AS `Tinte`,`detallesventa`.`MetodoDePago` AS `MetodoPago`,`ventas`.`PlanDePagos` AS `PeriodoAbonos`,`ventas`.`SaldoActual` AS `SaldoActual`,`ventas`.`TotalVenta` AS `CostoTotal`,`detallesventa`.`Enganche` AS `Enganche`,`ventas`.`FechaVenta` AS `FechaVenta` from (((((`ventas` join `pacientes` on(`ventas`.`PacienteId` = `pacientes`.`Id`)) join `clientes` on(`pacientes`.`ClienteId` = `clientes`.`Id`)) join `detallesventa` on(`detallesventa`.`VentaId` = `ventas`.`Id`)) join `productos` on(`detallesventa`.`ProductoId` = `productos`.`Id`)) left join `micas` on(`detallesventa`.`MicaId` = `micas`.`Id`))) */;

/*View structure for view pacientes_detalles */

/*!50001 DROP TABLE IF EXISTS `pacientes_detalles` */;
/*!50001 DROP VIEW IF EXISTS `pacientes_detalles` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pacientes_detalles` AS (select `d`.`Id` AS `DiagnosticoId`,`p`.`Id` AS `PacienteId`,`p`.`Nombre` AS `NombrePaciente`,`c`.`Nombre` AS `NombreCliente`,`d`.`Material` AS `Material`,`d`.`Tratamiento` AS `Tratamiento`,`d`.`Lente` AS `Lente`,`od`.`Esfera` AS `EsferaDerecho`,`od`.`Cilindro` AS `CilindroDerecho`,`od`.`Adicion` AS `AdicionDerecho`,`od`.`DI` AS `DIDerecho`,`od`.`Eje` AS `EjeDerecho`,`oi`.`Esfera` AS `EsferaIzquierdo`,`oi`.`Cilindro` AS `CilindroIzquierdo`,`oi`.`Adicion` AS `AdicionIzquierdo`,`oi`.`DI` AS `DIIzquierdo`,`oi`.`Eje` AS `EjeIzquierdo` from ((((`diagnostico` `d` join `pacientes` `p` on(`d`.`PacienteId` = `p`.`Id`)) join `clientes` `c` on(`p`.`ClienteId` = `c`.`Id`)) left join `ojoderecho` `od` on(`d`.`OjoDerechoId` = `od`.`Id`)) left join `ojoizquierdo` `oi` on(`d`.`OjoIzquierdoId` = `oi`.`Id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
