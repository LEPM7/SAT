-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sat
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sat` DEFAULT CHARACTER SET utf8 ;
USE `sat` ;

-- -----------------------------------------------------
-- Table `sat`.`REMESAS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sat`.`REMESAS` ;

CREATE TABLE IF NOT EXISTS `sat`.`REMESAS` (
  `ID_REMESAS` INT NOT NULL AUTO_INCREMENT,
  `FECHA_RECEPCION` DATETIME NULL,
  `FECHA_PAGO_IMPUESTO` DATETIME NULL,
  `MONTO` DOUBLE NULL,
  `usuario` VARCHAR(10) NULL,
  `contrasena` VARCHAR(10) NULL,
  `correlativoRemesa` INT NULL,
  `montoPagado` DOUBLE NULL,
  `montoTasaCambio` DOUBLE NULL,
  PRIMARY KEY (`ID_REMESAS`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
