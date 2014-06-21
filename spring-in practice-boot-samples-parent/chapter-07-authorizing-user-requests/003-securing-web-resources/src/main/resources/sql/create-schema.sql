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
	`date_created` TIMESTAMP NOT NULL DEFAULT 0,
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
	CONSTRAINT `account_role_fk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
	CONSTRAINT `account_role_fk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
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
	CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
	CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `forum`//

CREATE TABLE `forum` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(250) NOT NULL,
    `owner_id` BIGINT(20) UNSIGNED NOT NULL,
    `date_created` TIMESTAMP DEFAULT 0,
    `date_modified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `forum_idx_1` (`name`),
    CONSTRAINT `forum_fk_1` FOREIGN KEY (`owner_id`) REFERENCES `account` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `message`//

CREATE TABLE `message` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`forum_id` BIGINT(20) UNSIGNED NOT NULL,
	`subject` varchar(250) NOT NULL,
    `author_id` BIGINT(20) UNSIGNED NOT NULL,
    `text` TEXT(8000) NOT NULL,
    `visible` TINYINT NOT NULL DEFAULT 1,
    `date_created` TIMESTAMP DEFAULT 0,
    `date_modified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `message_idx_1` (`date_created`),
    CONSTRAINT `message_fk_1` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`),
    CONSTRAINT `message_fk_2` FOREIGN KEY (`author_id`) REFERENCES `account` (`id`)
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

DROP PROCEDURE IF EXISTS `createForum`//
CREATE PROCEDURE createForum($name VARCHAR(250), $owner_id BIGINT, out $id BIGINT)
BEGIN
	INSERT INTO `forum` (`name`, `owner_id`) VALUES ($name, $owner_id);
	set $id := last_insert_id();
END//

DROP PROCEDURE IF EXISTS `createMessage`//
CREATE PROCEDURE createMessage($forum_id BIGINT, $author_id BIGINT, $create_date TIMESTAMP, $subject VARCHAR(250))
BEGIN
	INSERT INTO `message`
		(`forum_id`, `subject`, `author_id`, `date_created`, `text`)
	VALUES
		($forum_id, $subject, $author_id, $create_date, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum.</p><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum');
END//

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS//
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS//