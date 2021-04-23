package orderproducer;

import java.util.HashMap;

public interface OrderService {
	public void addOrder(String customerName, String orderID,HashMap<String, Double> order );
	public Order getOrderByOrderId(String orderID);
	public void setStatusByOrderId(String orderID,String s);
	public String getStatusByOrderId(String orderID);
	public void displayAllOrders();
	public HashMap<String, Order> getAllOrders();
}