/*Data for the table `training_type` */

insert  into `training_type`(`training_type_name`) values
('Fitness'),
('Yoga'),
('Zumba'),
('Stretching'),
('Resistance');

/*Data for the table `user` */

insert  into `User`(`first_name`,`last_name`,`username`,`password`,`is_active`) values
('test','test','test','password',1),
('test','test','test1','password',1),
('test','test','test2','password',1),
('test','test','test3','password',1),
('test','test','test4','password',1);

/*Data for the table `trainee` */

--insert  into `trainee`(`id`,`date_of_birth`,`address`,`user_id`) values
--(1,'2000-02-28','Gante 581',NULL),
--(100,'2000-02-28','Gante 581',NULL),
--(105,'2000-02-28','Gante 581',121),
--(106,'2000-02-28','Gante 581',122),
--(107,'2000-02-28','Gante 581',123),
