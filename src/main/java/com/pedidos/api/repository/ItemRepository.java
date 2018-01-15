package com.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
