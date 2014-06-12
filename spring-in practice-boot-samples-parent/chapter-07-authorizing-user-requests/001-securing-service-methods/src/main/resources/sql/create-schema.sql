SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0//
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0//

DROP TABLE IF EXISTS `account`//

CREATE TABLE `account` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(80) NULL DEFAULT NULL,
	`marketing_ok` TINYINT(1) NOT NULL,
	`accept_terms` TINYINT(1) NOT NULL,
	`enabled` TINYINT(1) NOT NULL,
	`date_created` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	`date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `username` (`username`),
	UNIQUE INDEX `account_idx_1` (`username`),
	UNIQUE INDEX `account_idx_2` (`email`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `role`//

CREATE TABLE `role` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `permission`//

CREATE TABLE `permission` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)	
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `account_role`//

CREATE TABLE `account_role` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`account_id` BIGINT(20) UNSIGNED NOT NULL,
	`role_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `account_role_idx_1` (`account_id`, `role_id`),
	INDEX `role_id` (`role_id`),
	CONSTRAINT `account_role_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
	CONSTRAINT `account_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `role_permission`//

CREATE TABLE `role_permission` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`role_id` BIGINT(20) UNSIGNED NOT NULL,
	`permission_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `role_permission_idx_1` (`role_id`, `permission_id`),
	CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
	CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

-- ---------------------------
-- Helper Stored Procedures --
-- ---------------------------

DROP PROCEDURE IF EXISTS `createAccount`//
CREATE PROCEDURE createAccount($name varchar(50), $first_name varchar(50), $last_name varchar(50), $email varchar(50), out $id BIGINT)
BEGIN
	INSERT INTO `account` 
  		(`username`, `first_name`, `last_name`, `email`, `password`, `marketing_ok`, `accept_terms`, `enabled`, `date_created`) 
	VALUES
  		($name, $first_name, $last_name, $email, 'p@ssword', 1, 1, 1, CURRENT_TIMESTAMP);
  	SET $id := last_insert_id();
END//

DROP PROCEDURE IF EXISTS `createRole`//
CREATE PROCEDURE createRole($name varchar(50), out $id BIGINT) 
BEGIN
	INSERT INTO `role` (`name`) VALUES ($name);
	SET $id := last_insert_id();
END//

DROP PROCEDURE IF EXISTS `createPermission`//
CREATE PROCEDURE createPermission($name varchar(50)) 
BEGIN
	INSERT INTO `permission` (`name`) VALUES ($name);	
END//

DROP PROCEDURE IF EXISTS `assignPermissionToRole`//
CREATE PROCEDURE `assignPermissionToRole`($permission_name varchar(50), $role_id BIGINT)
BEGIN
	DECLARE PERMISSION_ID BIGINT;
	SELECT `id` INTO PERMISSION_ID FROM `permission` WHERE `name` = $permission_name;
	INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES ($role_id, PERMISSION_ID);
END//

DROP PROCEDURE IF EXISTS `assignRoleToUser`//
CREATE PROCEDURE `assignRoleToUser`($role_id BIGINT, $account_id BIGINT)
BEGIN
	INSERT INTO `account_role` (`role_id`, `account_id`) VALUES ($role_id, $account_id);
END//

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS//
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS//