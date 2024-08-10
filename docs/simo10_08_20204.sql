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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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

/*Table structure for table `plandepagos` */

DROP TABLE IF EXISTS `plandepagos`;

CREATE TABLE `plandepagos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VentaId` int(11) NOT NULL,
  `TipoPlan` enum('Semanal','Quincenal','Mensual') DEFAULT NULL,
  `FechaInicio` date DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `VentaID` (`VentaId`),
  CONSTRAINT `plandepagos_ibfk_1` FOREIGN KEY (`VentaId`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Table structure for table `productos` */

DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `PrecioContado` float DEFAULT NULL,
  `PrecioCredito` float DEFAULT NULL,
  `Descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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

/*View structure for view pacientes_detalles */

/*!50001 DROP TABLE IF EXISTS `pacientes_detalles` */;
/*!50001 DROP VIEW IF EXISTS `pacientes_detalles` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pacientes_detalles` AS (select `d`.`Id` AS `DiagnosticoId`,`p`.`Id` AS `PacienteId`,`p`.`Nombre` AS `NombrePaciente`,`c`.`Nombre` AS `NombreCliente`,`d`.`Material` AS `Material`,`d`.`Tratamiento` AS `Tratamiento`,`d`.`Lente` AS `Lente`,`od`.`Esfera` AS `EsferaDerecho`,`od`.`Cilindro` AS `CilindroDerecho`,`od`.`Adicion` AS `AdicionDerecho`,`od`.`DI` AS `DIDerecho`,`od`.`Eje` AS `EjeDerecho`,`oi`.`Esfera` AS `EsferaIzquierdo`,`oi`.`Cilindro` AS `CilindroIzquierdo`,`oi`.`Adicion` AS `AdicionIzquierdo`,`oi`.`DI` AS `DIIzquierdo`,`oi`.`Eje` AS `EjeIzquierdo` from ((((`diagnostico` `d` join `pacientes` `p` on(`d`.`PacienteId` = `p`.`Id`)) join `clientes` `c` on(`p`.`ClienteId` = `c`.`Id`)) left join `ojoderecho` `od` on(`d`.`OjoDerechoId` = `od`.`Id`)) left join `ojoizquierdo` `oi` on(`d`.`OjoIzquierdoId` = `oi`.`Id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
