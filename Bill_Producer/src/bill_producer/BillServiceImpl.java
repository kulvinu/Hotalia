package bill_producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import orderproducer.Order;

public class BillServiceImpl implements BillService {

	private double total=0;
	private HashMap<String, Double> foodItemList ;

	//method generates the bill for an order
	@Override
	public void generateBill(Order order) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		foodItemList = new HashMap<String, Double>();
		foodItemList = order.getOrderList();
		calcualteTotal(order);
		
		System.out.println("Order ID      :\t\t\t\t" + order.getOrderID());
		System.out.println("Customer Name :\t\t\t\t" + order.getCustomerName());
		System.out.println("Order Date    :\t\t\t\t" + dtf.format(now));
		System.out.println("\n\n\n");
		
		System.out.println("ITEM\t\t\t\tPRICE");
		//String leftAlignFormat = "| %-15s | %-4d |%n";

		/*
		 * System.out.format("+-----------------+------+%n");
		 * System.out.format("| FOOD ITEM     | PRICE  |%n");
		 * System.out.format("+-----------------+------+%n");
		 */
		for(String food : foodItemList.keySet()) {
			
			//System.out.println(food+"\t\t\t\t\t"+foodItemList.get(food));
			System.out.format("%-30s %-10.2f\n", food, foodItemList.get(food));
			//System.out.format(leftAlignFormat, " "+ food, foodItemList.get(food));
			
		}
		System.out.println("=========================================");
		System.out.println("Total Price                    =  " + total);
		
		System.out.println("\nThank you !!!!\n\n-------Next Customer------\n");
		
		
	}
	
	//method calculates total price of items in an order
	public void calcualteTotal(Order order) {
		for(String food : foodItemList.keySet()) {
			total = total + foodItemList.get(food);
		}
	};
	

}
