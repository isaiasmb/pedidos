package com.pedidos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedidos.api.model.Item;
import com.pedidos.api.model.Order;
import com.pedidos.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemService itemService;
	
	@Transactional(rollbackFor = Exception.class)
	public Order save(Order order) throws Exception {
		Order newOrder = orderRepository.save(order);
		
		if (newOrder != null && newOrder.getId() != null) {
			for (Item item : order.getItems()) {
				item.setOrder(order);
				itemService.save(item);
			}
		}
		return newOrder;
	}
	
	public Order update(Long id, Order order) throws Exception {
		Order savedOrder = orderRepository.findOne(id);
		if (savedOrder == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(order, savedOrder, "id");
		
		if (savedOrder != null && savedOrder.getId() != null) {
			for (Item item : savedOrder.getItems()) {
				item.setOrder(savedOrder);
				itemService.save(item);
			}
		}	
		
		return orderRepository.save(savedOrder);		
	}
}
