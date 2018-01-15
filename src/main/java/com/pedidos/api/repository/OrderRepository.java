package com.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
