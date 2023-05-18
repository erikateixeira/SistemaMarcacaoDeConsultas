-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SistemaMarcacaoConsultas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SistemaMarcacaoConsultas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SistemaMarcacaoConsultas` DEFAULT CHARACTER SET utf8mb4;
SHOW WARNINGS;
USE `SistemaMarcacaoConsultas` ;

-- -----------------------------------------------------
-- Table `SistemaMarcacaoConsultas`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaMarcacaoConsultas`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(80) NOT NULL,
  `cpfFuncionario` VARCHAR(14) NOT NULL,
  `rgFuncionario` VARCHAR(20) NOT NULL,
  `dataNascimentoFuncionario` DATE NOT NULL,
  `enderecoFuncionario` VARCHAR(255) NOT NULL,
  `cepFuncionario` VARCHAR(15) NOT NULL,
  `bairroFuncionario` VARCHAR(80) NOT NULL,
  `cidadeFuncionario` VARCHAR(80) NOT NULL,
  `estadoFuncionario` VARCHAR(2) NOT NULL,
  `telefoneFuncionario` VARCHAR(16) NOT NULL,
  `emailFuncionario` VARCHAR(80) NOT NULL,
  `funcao` VARCHAR(40) NOT NULL,
  `loginFuncionario` VARCHAR(45) NULL,
  `senhaFuncionario` VARCHAR(15) NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `SistemaMarcacaoConsultas`.`Medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaMarcacaoConsultas`.`Medico` (
  `idMedico` INT NOT NULL AUTO_INCREMENT,
  `nomeMedico` VARCHAR(80) NOT NULL,
  `cnpj` VARCHAR(30) NOT NULL,
  `crmEstado` VARCHAR(2) NOT NULL,
  `crmNum` INT NOT NULL,
  `especialidade` VARCHAR(30) NOT NULL,
  `sala` INT NOT NULL,
  `loginMedico` VARCHAR(20) NOT NULL,
  `senhaMedico` VARCHAR(45) NOT NULL,
  `dataDisponivel` JSON NOT NULL,
  `horaDisponivelInicial` TIME NOT NULL,
  `horaDisponivelFinal` TIME NOT NULL,
  `valorConsulta` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`idMedico`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `SistemaMarcacaoConsultas`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaMarcacaoConsultas`.`Paciente` (
  `idPaciente` INT NOT NULL AUTO_INCREMENT,
  `nomeIdentidade` VARCHAR(80) NOT NULL,
  `nomePreferencia` VARCHAR(80) NOT NULL,
  `cpfPaciente` VARCHAR(14) NULL,
  `passaportePaciente` VARCHAR(20) NULL,
  `dataNascimentoPaciente` DATE NOT NULL,
  `nomeResponsavel` VARCHAR(80) NULL,
  `cpfResponsavel` VARCHAR(14) NULL,
  `genero` VARCHAR(30) NULL,
  `enderecoPaciente` VARCHAR(255) NOT NULL,
  `cepPaciente` VARCHAR(15) NOT NULL,
  `bairroPaciente` VARCHAR(45) NOT NULL,
  `cidadePaciente` VARCHAR(80) NOT NULL,
  `estadoPaciente` VARCHAR(2) NOT NULL,
  `telefonePaciente` VARCHAR(16) NOT NULL,
  `emailPaciente` VARCHAR(80) NOT NULL,
  `planoSaude` VARCHAR(40) NOT NULL,
  `numPlano` INT NULL,
  `validadePlano` DATE NULL,
  PRIMARY KEY (`idPaciente`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `SistemaMarcacaoConsultas`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaMarcacaoConsultas`.`Consulta` (
  `idConsulta` INT NOT NULL AUTO_INCREMENT,
  `idMedico` INT NOT NULL,
  `idPaciente` INT NOT NULL,
  `idFuncionario` INT NOT NULL,
  `dataConsulta` DATE NOT NULL,
  `horarioConsulta` TIME NOT NULL,
  `retornoConsulta` TINYINT NOT NULL,
  PRIMARY KEY (`idConsulta`),
  INDEX `fk_Consulta_Medico1_idx` (`idMedico` ASC) VISIBLE,
  INDEX `fk_Consulta_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  INDEX `fk_Consulta_Usuario1_idx` (`idFuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_Consulta_Medico1`
    FOREIGN KEY (`idMedico`)
    REFERENCES `SistemaMarcacaoConsultas`.`Medico` (`idMedico`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Consulta_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SistemaMarcacaoConsultas`.`Paciente` (`idPaciente`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Consulta_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `SistemaMarcacaoConsultas`.`Funcionario` (`idFuncionario`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
