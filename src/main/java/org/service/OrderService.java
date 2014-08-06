package org.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.cache.OrderCacheImpl;
import org.main.MockApplicationListener;
import org.model.Order;

@Path("/orderservice")
public class OrderService {

	private MockApplicationListener mockApp = new MockApplicationListener();
	
	private Validator validator;
	
	private OrderCacheImpl orderCache;
	
	{
		mockApp.init();
	}
	
	public void setOrderCache(OrderCacheImpl orderCache) {
		this.orderCache = orderCache;
	}

	public OrderService() {
		validator = new Validator();
	}
	
	@GET
	@Path("/placeorder")
	public Response placeOrder(@QueryParam("account") String account, 
			@QueryParam("symbol") String symbol,
			@QueryParam("price") double price,
			@QueryParam("quantity") double quantity,
			@QueryParam("orderType") String orderType) {
		if (!mockApp.isMaster()) {
			return Response.status(404).build();
		}

		String output = "";
		Order order = new Order(account, symbol, price, quantity, orderType);		
		if (validator.isOrder(order)) {
			// dat lenh vao backend
			output = "dat lenh thanh cong";
			//save order
			orderCache.save(order);
			return Response.status(200).entity(output).build();
		} else {
			output = "lenh khong hop le.";
			return Response.status(505).entity(output).build();
		}
	}
	
	
	@GET
	@Path("/cancelorder")
	public Response cancelOrder(@QueryParam("account") String account, 
			@QueryParam("symbol") String symbol,
			@QueryParam("price") double price,
			@QueryParam("quantity") double quantity,
			@QueryParam("orderType") String orderType) {
		if (!mockApp.isMaster()) {
			return Response.status(404).build();
		}
		String output = "";
		Order order = new Order(account, symbol, price, quantity, orderType);		
		if (validator.isOrder(order)) {
			// dat lenh vao backend
			output = "huy lenh thanh cong";
			return Response.status(200).entity(output).build();
		} else {
			output = "lenh huy khong hop le.";
			return Response.status(505).entity(output).build();
		}
	}
	
	@GET
	@Path("/replaceorder")
	public Response replaceOrder(@QueryParam("account") String account, 
			@QueryParam("symbol") String symbol,
			@QueryParam("price") double price,
			@QueryParam("quantity") double quantity,
			@QueryParam("orderType") String orderType) {
		if (!mockApp.isMaster()) {
			return Response.status(404).build();
		}
		String output = "";
		Order order = new Order(account, symbol, price, quantity, orderType);		
		if (validator.isOrder(order)) {
			// dat lenh vao backend
			output = "sua lenh thanh cong";
			return Response.status(200).entity(output).build();
		} else {
			output = "lenh sua khong hop le.";
			return Response.status(505).entity(output).build();
		}
	}
	
	@GET
	@Path("top10order/")
	public Response top10Order() {
		if (!mockApp.isMaster()) {
			return Response.status(404).build();
		}
		orderCache.getTopOrder();
		return Response.status(200).entity("top10order").build();
	}
	
	@GET
	@Path("top10account/")
	public Response top10Account() {
		if (!mockApp.isMaster()) {
			return Response.status(404).build();
		}
		return Response.status(200).entity("top10account").build();
	}
}
