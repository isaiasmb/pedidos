package com.pedidos.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.api.event.CreatedResourceEvent;
import com.pedidos.api.model.Product;
import com.pedidos.api.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product, HttpServletResponse response) throws Exception {
		Product createdProduct = productRepository.save(product);
		publisher.publishEvent(new CreatedResourceEvent(this, response, createdProduct.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

}
