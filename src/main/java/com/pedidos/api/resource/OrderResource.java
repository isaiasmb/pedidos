package com.pedidos.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.api.model.Order;
import com.pedidos.api.repository.OrderRepository;
import com.pedidos.api.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Order> getAll() {
		return orderRepository.findAll();
	}
	
	@PostMapping
	public void create(@RequestBody Order order) {
		orderService.save(order);
	}

}
