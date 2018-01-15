CREATE TABLE items (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_product BIGINT(20),
	id_order BIGINT(20),
	amount INT(10),
	unit_price DOUBLE,
	KEY fk_order_item (id_order),
	CONSTRAINT fk_order_item FOREIGN KEY (id_order) REFERENCES orders(id),
    KEY fk_product_item (id_product),
    CONSTRAINT fk_product_item FOREIGN KEY (id_product) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;