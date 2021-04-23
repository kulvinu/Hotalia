package orderproducer;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderServiceImpl implements OrderService {

	private HashMap<String, Order> orderList =  new HashMap<String, Order>();
	
	//method adds an order 
	@Override
	public void addOrder(String customerName, String orderID, HashMap<String, Double> itemList) {
		//create a new Order
		Order order =  new Order(customerName, orderID, itemList);
				
		//add order to order list
		orderList.put(orderID, order);
		System.out.println("Order no : " + orderID + " has been added successfully\n ");
	}

	//method returns order when order id is given 
	@Override
	public Order getOrderByOrderId(String orderID) {
		// TODO Auto-generated method stub
		Order requestedOrder = orderList.get(orderID);
		return requestedOrder;
	}

	//method updates the order status 
	@Override
	public void setStatusByOrderId(String orderID, String orderStatus) {
		// TODO Auto-generated method stub
		Order requestedOrder = orderList.get(orderID);
		requestedOrder.setOrderStatus(orderStatus);
		
	}

	//method returns Status of an order given the order id
	@Override
	public String getStatusByOrderId(String orderID) {
		// TODO Auto-generated method stub
		Order requestedOrder = orderList.get(orderID);
		return requestedOrder.getOrderStatus();
	}

	//method displays all orders
	@Override
	public void displayAllOrders() {
		// TODO Auto-generated method stub
		if(orderList.isEmpty()) {
			System.out.println("There is no orders in the list");
		}else {
			for(String orderId : orderList.keySet()) {
				Order currentOrder = orderList.get(orderId);
				System.out.println("|| " + orderId +" || " + currentOrder.getCustomerName() + " || "  + currentOrder.getOrderStatus());
			}
			System.out.println("");
		}
	}
	
	public HashMap<String, Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderList;
	}
}

