package com.pedidos.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.api.exception.BadProfitabilityException;
import com.pedidos.api.exception.InvalidMultipleException;
import com.pedidos.api.exception.ProductNotFound;
import com.pedidos.api.model.Item;
import com.pedidos.api.model.Product;
import com.pedidos.api.model.Profitability;
import com.pedidos.api.repository.ItemRepository;
import com.pedidos.api.repository.ProductRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(rollbackOn = Exception.class)
	public Item save(Item item) throws Exception {
		Product product = productRepository.findOne(item.getProduct().getId());
		if (product == null) {
			throw new ProductNotFound("O produto informado não foi encontrado");
		}
		calculateItemProfitability(item, product);
		verifyProductMultiple(item, product);

		return itemRepository.save(item);
	}

	private void calculateItemProfitability(Item item, Product product) throws BadProfitabilityException {
		double unitPriceProduct = product.getUnitPrice();
		double unitPriceItem = item.getUnitPrice();

		if (unitPriceItem > unitPriceProduct) {
			item.setProfitability(Profitability.GREAT);
		} else if ((unitPriceProduct - unitPriceItem) < (unitPriceProduct * 0.10)) {
			item.setProfitability(Profitability.GOOD);
		} else {
			item.setProfitability(Profitability.BAD);
		}

		if (item.getProfitability().equals(Profitability.BAD)) {
			throw new BadProfitabilityException("O item " + product.getName() + " com valor " + item.getUnitPrice() + " tem uma rentabilidade ruim");
		}
	}

	private void verifyProductMultiple(Item item, Product product) throws InvalidMultipleException {
		if (product.getMultiple() != null) {
			if ((item.getAmount() % product.getMultiple()) != 0) {
				throw new InvalidMultipleException("O produto " + product.getName() + " deve ser vendido em múltiplos de " + product.getMultiple());
			}
		}
	}
}
