package com.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
