CREATE TABLE products (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	unit_price DOUBLE,
	multiple INT(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO products (name, unit_price) VALUES ("Millenium Falcon", 550000);
INSERT INTO products (name, unit_price, multiple) VALUES ("X-Wing", 60000, 2);
INSERT INTO products (name, unit_price) VALUES ("Super Star Destroyer", 4570000);
INSERT INTO products (name, unit_price, multiple) VALUES ("TIE Fighter", 75000, 2);
INSERT INTO products (name, unit_price, multiple) VALUES ("Lightsaber", 6000, 5);
INSERT INTO products (name, unit_price) VALUES ("DLT-19 Heavy Blaster Rifle", 5800);
INSERT INTO products (name, unit_price, multiple) VALUES ("DL-44 Heavy Blaster Pistol", 1500, 10);