package com.pedidos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.api.model.Item;
import com.pedidos.api.model.Order;
import com.pedidos.api.repository.ItemRepository;
import com.pedidos.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Order save(Order order) {
		Order newOrder = orderRepository.save(order);
		
		for (Item item : order.getItems()) {
			item.setOrder(order);
			itemRepository.save(item);
		}
		return newOrder;
	}
	
}
