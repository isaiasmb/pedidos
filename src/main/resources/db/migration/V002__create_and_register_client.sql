CREATE TABLE clients (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO clients (name) VALUES ("Darth Vader");
INSERT INTO clients (name) VALUES ("Obi-Wan Kenobi");
INSERT INTO clients (name) VALUES ("Luke Skywalker");
INSERT INTO clients (name) VALUES ("Imperador Palpatine");
INSERT INTO clients (name) VALUES ("Han Solo");