CREATE DATABASE IF NOT EXISTS znetwork;
USE znetwork;
CREATE TABLE IF NOT EXISTS `messages` (
	`messade_id` bigint NOT NULL AUTO_INCREMENT,
	`parent_id` int NOT NULL,
	`receiver` int NOT NULL,
	`text` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
	`username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	`date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`messade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int NOT NULL AUTO_INCREMENT,
	`email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	`reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	`token` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	`validate` tinyint NOT NULL,
	`password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
	`last_login` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `friends` (
                                       `note_id` bigint NOT NULL AUTO_INCREMENT,
                                       `usr_id` int NOT NULL,
                                       `friend_id` int NOT NULL,
                                       `add_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;