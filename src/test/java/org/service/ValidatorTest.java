package org.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.model.Order;
import org.model.OrderType;
import org.service.Validator;

public class ValidatorTest {
	
	private Order orderValid;
	private Order orderInValidAccount;
	private Order orderInValidSymbol;
	private Order orderInValidPrice;
	private Order orderInValidQuantity;
	private Order orderInValidOrderType;
	private Validator validator;
	
	
	private void setOrderValid() {
		orderValid = new Order();
		orderValid.setAccount("0001002003");
		orderValid.setSymbol("HAG");
		orderValid.setPrice(5000);
		orderValid.setQuantity(100);
		orderValid.setOrderType(OrderType.MOK.toString());
	}
	
	private void setOrderInvalidAccount() {
		orderInValidAccount = new Order();
		orderInValidAccount.setSymbol("HAG");
		orderInValidAccount.setPrice(5000);
		orderInValidAccount.setQuantity(100);
		orderInValidAccount.setOrderType(OrderType.MOK.toString());
	}
	
	private void setOrderInvalidSymbol() {
		orderInValidSymbol = new Order();
		orderInValidSymbol.setAccount("0001002003");
		orderInValidSymbol.setPrice(5000);
		orderInValidSymbol.setQuantity(100);
		orderInValidSymbol.setOrderType(OrderType.MOK.toString());
	}
	
	private void setOrderInvalidPrice() {
		orderInValidPrice = new Order();
		orderInValidPrice.setAccount("0001002003");
		orderInValidPrice.setSymbol("HAG");
		orderInValidPrice.setQuantity(100);
		orderInValidPrice.setOrderType(OrderType.MOK.toString());
	}
	
	private void setOrderInvalidQuantity() {
		orderInValidQuantity = new Order();
		orderInValidQuantity.setAccount("0001002003");
		orderInValidQuantity.setSymbol("HAG");
		orderInValidQuantity.setPrice(5000);
		orderInValidQuantity.setOrderType(OrderType.MOK.toString());
	}
	
	private void setOrderInvalidOrderType() {
		orderInValidOrderType = new Order();
		orderInValidOrderType.setAccount("0001002003");
		orderInValidOrderType.setSymbol("HAG");
		orderInValidOrderType.setPrice(5000);
		orderInValidOrderType.setQuantity(1000);
	}
	
	@Before
	public void init() {
		validator = new Validator();
	}
	
	@Test
	public void testValidOrder() {
		setOrderValid();
		Assert.assertTrue(validator.isOrder(orderValid));
	}
	
	@Test
	public void testInvalidOrderWithAccountIsNull() {
		setOrderInvalidAccount();
		Assert.assertFalse(validator.isOrder(orderInValidAccount));
	}
	
	@Test
	public void testInvalidOrderWithSymbolIsNull() {
		setOrderInvalidSymbol();
		Assert.assertFalse(validator.isOrder(orderInValidSymbol));
	}
	
	@Test
	public void testInvalidOrderWithPriceIsNull() {
		setOrderInvalidPrice();
		Assert.assertFalse(validator.isOrder(orderInValidPrice));
	}

	@Test
	public void testInvalidOrderWithQuantityIsNull() {
		setOrderInvalidQuantity();
		Assert.assertFalse(validator.isOrder(orderInValidQuantity));
	}
	
	@Test
	public void testInvalidOrderWithOrderTypeIsNull() {
		setOrderInvalidOrderType();
		Assert.assertFalse(validator.isOrder(orderInValidOrderType));
	}
}
