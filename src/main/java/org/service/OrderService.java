package org.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.model.Order;
import org.springframework.beans.factory.annotation.Value;

@Path("/orderservice")
public class OrderService {
	
	@Value("${pathData}")
	private String pathData="/Users/trungtd/Desktop/orderdata.txt";
	
	public void setPathData(String pathData) {
		this.pathData = pathData;
	}

	private Validator validator;
	
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
		String output = "";
		Order order = new Order(account, symbol, price, quantity, orderType);		
		if (validator.isOrder(order)) {
			// dat lenh vao backend
			output = "dat lenh thanh cong";
			//save order to file
			saveOrderDataToFile(order);
			return Response.status(200).entity(output).build();
		} else {
			output = "lenh khong hop le.";
			return Response.status(505).entity(output).build();
		}
	}
	
	private void saveOrderDataToFile(Order order) {
		String content = order.getAccount() + "|" +
						 order.getSymbol() + "|" +
						 order.getPrice() + "|" + 
						 order.getQuantity()  + "|" + 
						 order.getOrderType() ;
		File file = new File(pathData);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(content);
			bw.close();
		} catch (Exception e) {
		}
	}
	
	@GET
	@Path("/cancelorder")
	public Response cancelOrder(@QueryParam("account") String account, 
			@QueryParam("symbol") String symbol,
			@QueryParam("price") double price,
			@QueryParam("quantity") double quantity,
			@QueryParam("orderType") String orderType) {
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
		System.out.println("Path Data: "+pathData);
		List<Order> listOrder = readOrderDataInFile();
//		tinh top 10 order theo price*quantity
		return Response.status(200).entity("top10order").build();
	}
	
	@GET
	@Path("top10account/")
	public Response top10Account() {
		System.out.println("Path Data: "+pathData);
		List<Order> listOrder = readOrderDataInFile();
		for (Order order : listOrder) {
			//tinh top 10 order account lon nhat
		}
		return Response.status(200).entity("top10account").build();
	}
	

	private List<Order> readOrderDataInFile() {
		BufferedReader br = null;
		List<Order> listOrder = new ArrayList<Order>();
		try {
			FileReader file = new FileReader(pathData);
			String sCurrentLine;
			br = new BufferedReader(file);
			while ((sCurrentLine = br.readLine()) != null) {
				Order order = convertStringDataToOrderObject(sCurrentLine);
				if (order != null) {
					listOrder.add(order);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return listOrder;
	}

	private Order convertStringDataToOrderObject(String str) {
		Order order = null;
		String[] splStr = str.split("\\|");
		if (str.length() > 4) {
			order = new Order(splStr[0], splStr[1], 
					Double.valueOf(splStr[2]), Double.valueOf(splStr[3]), splStr[4]);
		}
		return order;
	}
}
