-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema admissions
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema admissions
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `admissions` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `admissions` ;

-- -----------------------------------------------------
-- Table `admissions`.`faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`faculty` (
                                                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `budget_capacity` INT NOT NULL,
                                                      `description` VARCHAR(2048) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `req_subject1` VARCHAR(255) NOT NULL,
    `req_subject2` VARCHAR(255) NOT NULL,
    `req_subject3` VARCHAR(255) NOT NULL,
    `total_capacity` INT NOT NULL,
    `admission_open` BIT(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKcnlvf8tbojb7ynutd3pep8s1x` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `admissions`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`candidate` (
                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `candidate_status` VARCHAR(255) NULL DEFAULT NULL,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `role` VARCHAR(255) NULL DEFAULT NULL,
    `username` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `candidate_username_uindex` (`username` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 175
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `admissions`.`admission_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`admission_request` (
                                                                `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                `status` INT NOT NULL,
                                                                `creation_date_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                                                                `req_subject1_grade` INT NOT NULL,
                                                                `req_subject2_grade` INT NOT NULL,
                                                                `req_subject3_grade` INT NOT NULL,
                                                                `candidate_id` BIGINT NULL DEFAULT NULL,
                                                                `faculty_id` BIGINT NULL DEFAULT NULL,
                                                                PRIMARY KEY (`id`),
    UNIQUE INDEX `UKdtpiwdcwq749iug4s5q50xkx5` (`candidate_id` ASC, `faculty_id` ASC) VISIBLE,
    INDEX `FK9x4jry9ea2o5av76ebwulm45f` (`faculty_id` ASC) VISIBLE,
    CONSTRAINT `FK9x4jry9ea2o5av76ebwulm45f`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `admissions`.`faculty` (`id`),
    CONSTRAINT `FKsp9ud3hxixws17h1cdfam85vv`
    FOREIGN KEY (`candidate_id`)
    REFERENCES `admissions`.`candidate` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 177
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `admissions`.`candidate_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`candidate_profile` (
                                                                `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                `address` VARCHAR(255) NULL DEFAULT NULL,
    `city` VARCHAR(255) NULL DEFAULT NULL,
    `email` VARCHAR(255) NULL DEFAULT NULL,
    `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    `phone_number` VARCHAR(255) NULL DEFAULT NULL,
    `region` VARCHAR(255) NULL DEFAULT NULL,
    `school` VARCHAR(255) NULL DEFAULT NULL,
    `candidate_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_ktqxtjlhx7f7yrpmdw24ebfgy` (`candidate_id` ASC) VISIBLE,
    CONSTRAINT `FKtkv0rt04w6e20vuow1vt1hfje`
    FOREIGN KEY (`candidate_id`)
    REFERENCES `admissions`.`candidate` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 115
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `admissions`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`hibernate_sequence` (
    `next_val` BIGINT NULL DEFAULT NULL)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `admissions`.`statement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admissions`.`statement` (
                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `author` VARCHAR(255) NULL DEFAULT NULL,
    `creation_date_time` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
