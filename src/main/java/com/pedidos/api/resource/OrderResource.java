package com.pedidos.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Order> getAll() {
		return orderRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order, HttpServletResponse response) {
		Order createdOrder = orderService.save(order);
		publisher.publishEvent(new CreatedResourceEvent(this, response, createdOrder.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}
	
	
}
