package org.cache;

import java.util.List;

import org.model.Order;


public interface IOrderCache {
	void reset();
	
	void save(Order order);

	List<Order> getTopOrder();

	List<String> getTopAccount();


}
