CREATE TABLE `hints` (
`id` INT(10) NULL DEFAULT NULL,
`hint` TEXT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `category` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`info` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);
INSERT INTO `category`(`id`, `info`) VALUES(1, 'супы'),(2, 'лапша'),(3, 'суши'),(4, 'десерты'),(5, 'другое');