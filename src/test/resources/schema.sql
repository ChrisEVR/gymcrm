/*Table structure for table `user` */

--DROP TABLE IF EXISTS `user`;

CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255),
  `is_active` BOOLEAN,
  PRIMARY KEY (`id`)
);


/*Table structure for table `trainee` */

--DROP TABLE IF EXISTS `trainee`;

--CREATE TABLE `trainee` (
--  `id` int NOT NULL AUTO_INCREMENT,
--  `date_of_birth` date,
--  `address` varchar(40),
--  `user_id` int,
--  PRIMARY KEY (`id`),
--  FOREIGN KEY (user_id) REFERENCES `user` (`id`)
--);

/*Table structure for table `training_type` */

--DROP TABLE IF EXISTS `training_type`;

CREATE TABLE `training_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `training_type_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
);

/*Table structure for table `trainer` */

--DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specialization` int,
  `user_id` int,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`specialization`) REFERENCES `training_type` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

--/*Table structure for table `trainee_trainer` */
--
----DROP TABLE IF EXISTS `trainee_trainer`;
--
--CREATE TABLE `trainee_trainer` (
--  `id` int NOT NULL AUTO_INCREMENT,
--  `trainee_id` int,
--  `trainer_id` int,
--  PRIMARY KEY (`id`),
--  FOREIGN KEY (`trainee_id`) REFERENCES `trainee` (`id`),
--  FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`)
--);
--
--
--/*Table structure for table `training` */
--
----DROP TABLE IF EXISTS `training`;
--
--CREATE TABLE `training` (
--  `id` int NOT NULL AUTO_INCREMENT,
--  `trainee_id` int,
--  `trainer_id` int,
--  `training_name` varchar(50) NOT NULL,
--  `training_type_id` int,
--  `training_date` date NOT NULL,
--  `training_duration` smallint NOT NULL,
--  PRIMARY KEY (`id`),
--  FOREIGN KEY (`trainee_id`) REFERENCES `trainee` (`id`),
--  FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`),
--  FOREIGN KEY (`training_type_id`) REFERENCES `training_type` (`id`)
--);
