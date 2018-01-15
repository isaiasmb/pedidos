package com.pedidos.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.api.model.Client;
import com.pedidos.api.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

}
