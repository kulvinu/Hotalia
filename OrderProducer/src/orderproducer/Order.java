package orderproducer;

import java.util.HashMap;

public class Order {
	private String customerName;
	private String orderID;
	private HashMap<String, Double> orderList;
	private String orderStatus = "Order is not ready";
	
	//initializes Order
	public Order(String customerName, String orderID, HashMap<String, Double> orderList) {
		//super();
		this.customerName = customerName;
		this.orderID = orderID;
		this.orderList = orderList;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public HashMap<String, Double> getOrderList() {
		return orderList;
	}
	public void setOrderList(HashMap<String, Double> orderList) {
		this.orderList = orderList;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	

}
