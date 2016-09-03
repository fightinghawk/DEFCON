-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tipodeusuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipodeusuario` (
  `tipodeusuario` CHAR(30) NOT NULL,
  `tipo_descripcion` TEXT NULL,
  PRIMARY KEY (`tipodeusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `user_id` CHAR(30) NOT NULL,
  `user_nombre` CHAR(30) NULL,
  `user_apellido` CHAR(30) NULL,
  `user_password` CHAR(30) NULL,
  `user_mail` CHAR(30) NULL,
  `user_tipo` CHAR(30) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_usuarios_tipodeusuario_idx` (`user_tipo` ASC),
  CONSTRAINT `fk_usuarios_tipodeusuario`
    FOREIGN KEY (`user_tipo`)
    REFERENCES `mydb`.`tipodeusuario` (`tipo_descripcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`permisos` (
  `per_permisos` CHAR(30) NOT NULL,
  `per_descripciom` TEXT NULL,
  PRIMARY KEY (`per_permisos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipodeusuario_has_permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipodeusuario_has_permisos` (
  `tipodeusuario_tipodeusuario` CHAR(30) NOT NULL,
  `permisos_per_permisos` CHAR(30) NOT NULL,
  PRIMARY KEY (`tipodeusuario_tipodeusuario`, `permisos_per_permisos`),
  INDEX `fk_tipodeusuario_has_permisos_permisos1_idx` (`permisos_per_permisos` ASC),
  INDEX `fk_tipodeusuario_has_permisos_tipodeusuario1_idx` (`tipodeusuario_tipodeusuario` ASC),
  CONSTRAINT `fk_tipodeusuario_has_permisos_tipodeusuario1`
    FOREIGN KEY (`tipodeusuario_tipodeusuario`)
    REFERENCES `mydb`.`tipodeusuario` (`tipodeusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipodeusuario_has_permisos_permisos1`
    FOREIGN KEY (`permisos_per_permisos`)
    REFERENCES `mydb`.`permisos` (`per_permisos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`geoPos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`geoPos` (
  `geopos_id` INT(11) NOT NULL AUTO_INCREMENT,
  `latitud` DOUBLE NULL,
  `longitud` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`geopos_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`direcciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`direcciones` (
  `direcciones_id` INT(11) NOT NULL AUTO_INCREMENT,
  `principal` VARCHAR(45) NOT NULL,
  `izquierda` VARCHAR(45) NULL DEFAULT NULL,
  `derecha` VARCHAR(45) NULL DEFAULT NULL,
  `barrio` VARCHAR(45) NULL DEFAULT NULL,
  `localidad` VARCHAR(45) NULL DEFAULT NULL,
  `altura` INT(11) NULL DEFAULT NULL,
  `piso` INT(11) NULL DEFAULT NULL,
  `dpto` CHAR(1) NULL DEFAULT NULL,
  `unidad` INT(11) NULL DEFAULT NULL,
  `codpostal` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`direcciones_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pois`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pois` (
  `pois_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `strtipo` VARCHAR(30) NOT NULL,
  `geoPos_id` INT(11) NOT NULL,
  `direcciones_id` INT(11) NOT NULL,
  PRIMARY KEY (`pois_id`),
  INDEX `fk_pois_geoPos1_idx` (`geoPos_id` ASC),
  INDEX `fk_pois_direcciones1_idx` (`direcciones_id` ASC),
  CONSTRAINT `fk_pois_geoPos1`
    FOREIGN KEY (`geoPos_id`)
    REFERENCES `mydb`.`geoPos` (`geopos_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pois_direcciones1`
    FOREIGN KEY (`direcciones_id`)
    REFERENCES `mydb`.`direcciones` (`direcciones_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`keyWords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`keyWords` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `clave` VARCHAR(45) NOT NULL,
  `pois_pois_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_keyWords_pois1_idx` (`pois_pois_id` ASC),
  CONSTRAINT `fk_keyWords_pois1`
    FOREIGN KEY (`pois_pois_id`)
    REFERENCES `mydb`.`pois` (`pois_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`diaspoi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`diaspoi` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `horainicio` INT(11) NOT NULL,
  `horafin` INT(11) NOT NULL,
  `minutoinicio` INT(11) NOT NULL,
  `minutofin` INT(11) NOT NULL,
  `dia` INT(11) NOT NULL,
  `pois_pois_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_table1_pois1_idx` (`pois_pois_id` ASC),
  CONSTRAINT `fk_table1_pois1`
    FOREIGN KEY (`pois_pois_id`)
    REFERENCES `mydb`.`pois` (`pois_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`terminales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`terminales` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`busqueda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`busqueda` (
  `id` INT(11) NOT NULL,
  `fecha` DATE NOT NULL,
  `resultados` INT(10) UNSIGNED NOT NULL,
  `time` DOUBLE NOT NULL,
  `terminales_id` INT(10) UNSIGNED NOT NULL,
  `usuarios_user_id` CHAR(30) NOT NULL,
  INDEX `fk_busqueda_terminales1_idx` (`terminales_id` ASC),
  INDEX `fk_busqueda_usuarios1_idx` (`usuarios_user_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_busqueda_terminales1`
    FOREIGN KEY (`terminales_id`)
    REFERENCES `mydb`.`terminales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_busqueda_usuarios1`
    FOREIGN KEY (`usuarios_user_id`)
    REFERENCES `mydb`.`usuarios` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`criteriosdebusqueda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`criteriosdebusqueda` (
  `cri_id` INT NOT NULL AUTO_INCREMENT,
  `cri_tipo` VARCHAR(30) NOT NULL,
  `cri_contenido` VARCHAR(45) NULL,
  `busqueda_id` INT(11) NOT NULL,
  PRIMARY KEY (`cri_id`),
  INDEX `fk_criteriosdebusqueda_busqueda1_idx` (`busqueda_id` ASC),
  CONSTRAINT `fk_criteriosdebusqueda_busqueda1`
    FOREIGN KEY (`busqueda_id`)
    REFERENCES `mydb`.`busqueda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
