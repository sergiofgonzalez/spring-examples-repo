SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`enabled`  boolean     NOT NULL,
	PRIMARY KEY (`username`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
	`username`  varchar(50) NOT NULL,
	`authority` varchar(50) NOT NULL,
	PRIMARY KEY (`username`, `authority`),
	INDEX `authorities_idx0` (`authority`),
	CONSTRAINT `FK_authorities_username_users_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;