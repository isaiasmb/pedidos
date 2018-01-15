package com.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos.api.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
