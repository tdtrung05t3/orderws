package org.service;

import org.model.Order;
import org.model.OrderType;

public class Validator {
	
	public boolean isOrder(Order order) {
		if (isAccount(order.getAccount()) && isSymbol(order.getSymbol()) 
				&& isPrice(order.getPrice()) && isQuantity(order.getQuantity())
				&& isOrderType(order.getOrderType())) {
			return true;
		}
		return false;
	}

	private boolean isOrderType(String orderType) {
		for (OrderType ot : OrderType.values()) {
			if (ot.toString().equalsIgnoreCase(orderType)) {
				return true;
			}
		}
		return false;
	}

	private boolean isQuantity(double quantity) {
		if (quantity >0) {
			return true;
		}
		return false;
	}

	private boolean isPrice(double price) {
		if (price > 0) {
			return true;
		}
		return false;
	}

	private boolean isSymbol(String symbol) {
		if (symbol != null) {
			return true;
		}
		return false;
	}

	private boolean isAccount(String account) {
		if (account != null) {
			return true;
		}
		return false;
	}
}
