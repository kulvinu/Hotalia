package menuProducer;

import java.util.HashMap;
import java.util.Map;

public class MenuProducerImpl implements MenuService {

	HashMap<String, Double> menuList = new HashMap<String, Double>();
	
	//method to add menu items to menu list
	public void createMenu(String foodName, Double price) {
		menuList.put(foodName, price);

		System.out.println("Menu Added successFully");
	}
	
	//method to display menu items in the list
	public void getMenu() {
		
		System.out.println("----------------------------------Menu----------------------------------\n");
		System.out.println("ITEM\t\t\t\tPRICE");
		System.out.println("--------------------------------------------------");
		for(Map.Entry<String, Double> menuItem: menuList.entrySet()) {
			String foodname = menuItem.getKey();
			Double price = menuItem.getValue();
			System.out.format("%-30s %-10.2f\n", foodname, price);
		}
	}

	
}
