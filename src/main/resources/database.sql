CREATE DATABASE person_api;

create table Person (
	`id` varchar(30),
    `name` varchar(30),
    `age` int(5),
    `locale` varchar(30),
    primary key(`id`)
);

INSERT INTO `person_api`.`person` (`id`, `name`, `age`, `locale`) VALUES ('p001', 'Sid', '27', 'Boston');
INSERT INTO `person_api`.`person` (`id`, `name`, `age`, `locale`) VALUES ('p002', 'Yash', '22', 'Boston');
INSERT INTO `person_api`.`person` (`id`, `name`, `age`, `locale`) VALUES ('p003', 'Sai', '25', 'SF');
INSERT INTO `person_api`.`person` (`id`, `name`, `age`, `locale`) VALUES ('p004', 'Rudy', '26', 'Houston');
INSERT INTO `person_api`.`person` (`id`, `name`, `age`, `locale`) VALUES ('p005', 'Prabeen', '25', 'Kerela');
//jdbc:mysql://localhost:3306/person_api?autoReconnect=true&useSSL=false