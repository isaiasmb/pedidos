package com.pedidos.api.service;

import java.io.IOException;
import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pedidos.api.PedidosApiApplication;
import com.pedidos.api.builder.ClientBuilder;
import com.pedidos.api.builder.ItemBuilder;
import com.pedidos.api.builder.OrderBuilder;
import com.pedidos.api.builder.ProductBuilder;
import com.pedidos.api.exception.BadProfitabilityException;
import com.pedidos.api.exception.InvalidMultipleException;
import com.pedidos.api.model.Client;
import com.pedidos.api.model.Item;
import com.pedidos.api.model.Order;
import com.pedidos.api.model.Product;
import com.pedidos.api.repository.ClientRepository;
import com.pedidos.api.repository.ItemRepository;
import com.pedidos.api.repository.OrderRepository;
import com.pedidos.api.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PedidosApiApplication.class)
@WebAppConfiguration
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private Client client;
	private Product product;
	
	@Before
	public void setUp() {
		itemRepository.deleteAll();
		orderRepository.deleteAll();
		productRepository.deleteAll();
		clientRepository.deleteAll();
		
		client = ClientBuilder.oneClient().now();
		clientRepository.save(client);
		
		product = ProductBuilder.oneProduct().now();
		productRepository.save(product);	
	}
	
	@Test(expected = InvalidMultipleException.class)
	public void shouldThrowInvalidMultipleException() throws IOException, Exception {		
		Item item = ItemBuilder.oneItem(product).withAmount(3).withUnitPrice(550000.01).now();
		Order order = OrderBuilder.oneOrder(client, Arrays.asList(item)).now();		
		orderService.save(order);
	}
	
	@Test(expected = BadProfitabilityException.class)
	public void shouldThrowBadProfitabilityException() throws IOException, Exception {		
		Item item = ItemBuilder.oneItem(product).withUnitPrice(100.0).now();
		Order order = OrderBuilder.oneOrder(client, Arrays.asList(item)).now();		
		orderService.save(order);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void shouldThrowConstraintViolationExceptionWhenMoreThenTwoDecimalPlaces() throws IOException, Exception {		
		Item item = ItemBuilder.oneItem(product).withUnitPrice(550000.0112).now();
		Order order = OrderBuilder.oneOrder(client, Arrays.asList(item)).now();		
		orderService.save(order);
	}	
}
