-- MySQL Script generated by MySQL Workbench
-- 09/17/15 17:18:00
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema AGEABD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AGEABD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AGEABD` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `AGEABD` ;

-- -----------------------------------------------------
-- Table `AGEABD`.`TBLGESTORES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLGESTORES` (
  `gesId` BIGINT NOT NULL AUTO_INCREMENT,
  `gesNombre` VARCHAR(50) NULL,
  `gesCorreo` VARCHAR(50) NULL,
  `gesTipo` VARCHAR(50) NULL,
  `gesDescripcion` LONGTEXT NULL,
  `gesActid` BIGINT NULL,
  PRIMARY KEY (`gesId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGEABD`.`TBLUSUARIOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLUSUARIOS` (
  `regId` BIGINT NOT NULL AUTO_INCREMENT,
  `regContra` VARCHAR(20) NOT NULL,
  `regNombre` VARCHAR(50) NOT NULL,
  `regCorreo` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`regId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGEABD`.`TBLACTIVIDADES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLACTIVIDADES` (
  `actId` BIGINT NOT NULL AUTO_INCREMENT,
  `actNombre` VARCHAR(50) NULL,
  `actFHinicio` DATETIME NULL,
  `actFFinal` DATETIME NULL,
  `actDuracion` INT NULL,
  `actLugar` VARCHAR(50) NULL,
  `actMaximo` INT NULL,
  `actRecursos` VARCHAR(100) NULL,
  `actStatus` BIT NULL,
  PRIMARY KEY (`actId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGEABD`.`TBLPARTICIPANTES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLPARTICIPANTES` (
  `parId` BIGINT NOT NULL AUTO_INCREMENT,
  `parNombre` VARCHAR(50) NULL,
  `parCorreo` VARCHAR(50) NULL,
  `parCarrera` VARCHAR(50) NULL,
  `parEdad` DATE NULL,
  `parActid` BIGINT NULL,
  PRIMARY KEY (`parId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGEABD`.`TBLPAGOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLPAGOS` (
  `pagId` BIGINT NOT NULL AUTO_INCREMENT,
  `pagEvento` BIGINT NULL,
  `pagCantidad` INT NULL,
  `pagReferencia` INT(8) NULL,
  `pagNotas` VARCHAR(100) NULL,
  `pagPagado` BIT NULL,
  PRIMARY KEY (`pagId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGEABD`.`TBLPERFIL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGEABD`.`TBLPERFIL` (
  `perId` BIGINT NOT NULL AUTO_INCREMENT,
  `perNombre` VARCHAR(50) NOT NULL,
  `perPermiso` VARCHAR(50) NOT NULL,
  `perExiste` BIT NOT NULL,
  PRIMARY KEY (`perId`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
