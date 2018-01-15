package com.pedidos.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.api.model.Product;
import com.pedidos.api.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
