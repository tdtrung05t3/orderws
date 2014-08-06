package org.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.model.Order;

public class OrderCacheImpl implements IOrderCache {
	
	List<Order> listOrders;
	
	private int numberReport;
	
	public OrderCacheImpl() {
		listOrders = new ArrayList<Order>();
	}

	@Override
	public void reset() {
		listOrders.clear();
	}

	@Override
	public void save(Order order) {
		listOrders.add(order);
	}

	@Override
	public List<Order> getTopOrder() {
		List<Order> list = sortListOrder(listOrders);
		int numberStatisticReal = 0;
		int sizeList = list.size();
		if ( sizeList > numberReport) {
			numberStatisticReal = numberReport;
		} else {
			numberStatisticReal = sizeList - 1;
		}
		return list.subList(0, numberStatisticReal);
	}

	private List<Order> sortListOrder(List<Order> list) {
		Collections.sort(list, new Comparator<Order>() {
	        @Override
	        public int compare(Order  order1, Order  order2)
	        {
	        	Double priceQuantity1 = order1.getPrice() * order1.getQuantity();
	        	Double priceQuantity2 = order2.getPrice() * order2.getQuantity();
	            return  priceQuantity2.compareTo(priceQuantity1);
	        }
	    });
		return list;
	}

	@Override
	public List<String> getTopAccount() {
		return null;
	}

	public void setNumberReport(int numberReport) {
		this.numberReport = numberReport;
	}
}
