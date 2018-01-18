package com.pedidos.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pedidos.api.PedidosApiApplication;
import com.pedidos.api.builder.ClientBuilder;
import com.pedidos.api.builder.ItemBuilder;
import com.pedidos.api.builder.OrderBuilder;
import com.pedidos.api.builder.ProductBuilder;
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
public class OrderResourceTest extends ResourceTest {
	
	final String BASE_PATH = "http://localhost:8080/orders";
	
	@MockBean
	private OrderResource orderResource;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private Product product;
	private Client client;
	private Order order;
	private Item item;
		
	private MockMvc mvc;
	
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
		
		item = ItemBuilder.oneItem(product).now();		
		order = OrderBuilder.oneOrder(client, Arrays.asList(item)).now();
		orderRepository.save(order);
		
		mvc = MockMvcBuilders.standaloneSetup(orderResource).build();
	}
	
	@Test
	public void shouldCreateOrder() throws Exception {		
		Order newOrder = OrderBuilder.oneOrder(client, Arrays.asList(item)).now();
		
		mvc.perform(post(BASE_PATH)
				.content(json(newOrder))
				.contentType(contentType))
		.andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetOrder() throws Exception {
		mvc.perform(get(BASE_PATH + "/" + order.getId())
				.contentType(contentType))
		.andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetAllOrders() throws Exception {
		mvc.perform(get(BASE_PATH)
				.contentType(contentType))
		.andExpect(status().isOk());
	}
	
	@Test
	public void shouldDeleteOrder() throws Exception {
		mvc.perform(delete(BASE_PATH + "/" + order.getId())
				.contentType(contentType))
		.andExpect(status().isNoContent());
	}
}
