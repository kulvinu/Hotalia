package clientaplication;

import bill_producer.BillService;
import bill_producer.BillServiceImpl;
import menuProducer.MenuProducerImpl;
import menuProducer.MenuService;
import orderproducer.Order;
import orderproducer.OrderService;
import orderproducer.OrderServiceImpl;

import java.util.HashMap;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;



public class Activator implements BundleActivator {
	
	private boolean backToMenu = false;//determines whether to return to Portal options or to General portal options
	private boolean backToHome = false;//determines whether to exit or return to General portal options
	private Scanner sc;
	private String userResult = null;
	private OrderService orderservice = null;
	private MenuService menuservice = null;
	private BillService billservice = null;

	ServiceReference orderserviceref;
	ServiceReference menuserviceref;
	ServiceReference billserviceref;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Consumer Started");	
		orderserviceref = bundleContext.getServiceReference(OrderService.class.getName());
		OrderService orderservice = (OrderService) bundleContext.getService(orderserviceref);
		menuserviceref = bundleContext.getServiceReference(MenuService.class.getName());
		MenuService menuservice = (MenuService) bundleContext.getService(menuserviceref);
		billserviceref = bundleContext.getServiceReference(BillService.class.getName());
		BillService billservice = (BillService) bundleContext.getService(billserviceref);
		
		publish(orderservice, menuservice, billservice);
	}
	
	public void publish(OrderService order, MenuService menu, BillService bill) {
		// initializing
		  this.orderservice = order; 
		  this.menuservice = menu; 
		  this.billservice = bill;		 
		
		do {
			switch (generalPortal()) {
			case 1:
				genManagerPortal();
				break;
			case 2:
				supervisorPortal();
				break;
			case 3:
				cashierPortal();
				break;
			case 0:
				System.exit(0);
				break;
			default:System.out.println("Select valid option from the list !");
					break;
			}
		} while (backToHome);
		
	}
	
	//method to determine role of user
		public int generalPortal() {
			
			System.out.println("\nWELCOME TO THE RESTUARANT MANAGEMENT SYSTEM!\n");
			System.out.println("1: Admin \n2: Kitchen Manager \n3: Restuarant Manager \n0: Exit");
			System.out.print("Choose who you are entering as: ");
			
			sc = new Scanner(System.in);
			int userInput = sc.nextInt();
			return userInput;
		}
		

		//method to determine options for operations of Restuarant Manager
		public void cashierPortal() {
			
			System.out.println("------------------------------Order Portal------------------------------");
			do {
				System.out.println("\n1: Add Order  \n2: View Orders \n3: Calculate Bill \n0: Exit");
				System.out.print("Please enter an option: ");
				sc = new Scanner(System.in);
				int userSelection = sc.nextInt();
				switch (userSelection) {
				case 1:
					backToMenu = addOrder();
					break;
				case 2:
					backToMenu = getOrder();
					break;
				case 3:
					backToMenu = generateBill();
					break;
				case 0:
					backToMenu = false;
					backToHome = true;
					break;
				default:
					System.out.println("Select valid option from the list !");
					backToMenu = true;
					break;
				}
			} while (backToMenu);
		}

		//method to determine the options for the operations of the Admin
		public void genManagerPortal() {
			
			System.out.println("------------------------------Admin Portal------------------------------");
			do {
				System.out.println("\n1: Add Menu Item  \n2: View Current Menu  \n0: Exit");
				System.out.print("Please enter an option: ");
				sc = new Scanner(System.in);
				int userSelection = sc.nextInt();
				switch(userSelection) {
					case 1: backToMenu = addMenu();
							break;
					case 2: backToMenu = getMenus();
							break;
					case 0: backToMenu = false;
							backToHome = true;
							break;
					default:System.out.println("Select valid option from the list !");
							backToMenu = true;
							break;
			}
				
			}while(backToMenu);
		}

		//method to determine the options for the operaions of Kitchen Manager
		public void supervisorPortal() {
			
			System.out.println("------------------------------Kitchen Portal------------------------------");
			do {
				System.out.println("\n1: View Orders \n2: Check Order Status \n3: Update Order Status \n0: Exit\n");
				System.out.print("Please enter an option: ");
				sc = new Scanner(System.in);
				int userSelection = sc.nextInt();
				switch (userSelection) {
				case 1:
					backToMenu = getOrder();
					break;
				case 2:
					backToMenu = getOrderStatus();
					break;
				case 3:
					backToMenu = setOrderStatus();
					break;
				case 0:
					backToMenu = false;
					backToHome = true;
					break;
				default:
					System.out.println("Select valid option from the list !");
					backToMenu = true;
					break;
				}
			} while (backToMenu);
		}
		

		//method allow orders to be fetched
		public boolean getOrder() {
			
			System.out.println("\tOrders List");
			System.out.println("----------------------------");
			
			orderservice.displayAllOrders();
			return true;
		}

		//method allows the orders to be added 
		public boolean addOrder() {
			
			HashMap<String, Double> orderList = new HashMap<String, Double>();
			menuservice.getMenu();
			sc.nextLine();//skips line
			
			System.out.print("Enter Customer Name: ");
			String customer = sc.nextLine();
			System.out.print("Enter Order Number: ");
			String orderNum = sc.nextLine();
			
			do {
				System.out.print("\nMenu Id: ");
				String menuItem = sc.nextLine();
				System.out.print("Price: ");
				Double portion = sc.nextDouble();
				orderList.put(menuItem, portion);
				sc.nextLine();// skips new line
				System.out.print("Enter y to confirm your order else press any other key to continue :");
				userResult = sc.nextLine();
			} while (!(userResult.equals("y")));
			
			orderservice.addOrder(customer, orderNum, orderList);
			return true;
		}

		//method takes order id as user input and uses orderservice to get order status. 
		public boolean getOrderStatus() {
			
			sc.nextLine();//skips line
			System.out.println("Enter Order Id:");
			String orderIds = sc.nextLine();
			
			Order order = orderservice.getOrderByOrderId(orderIds);
			System.out.println("Status of Order " + orderIds + " : " + order.getOrderStatus() );
			return true;
		}

		//method takes in item name and price and uses menuservice to add them to the menu list
		public boolean addMenu() {
			
			sc.nextLine();//skips line
			System.out.print("\nEnter Item Name: ");
			String item = sc.nextLine();
			System.out.print("Enter Item Price: ");
			double price = sc.nextDouble();
			sc.nextLine();
			
			menuservice.createMenu(item, price);
			return true;
		}

		//method uses menuservice to retrieve menu list
		public boolean getMenus() {
			// TODO Auto-generated method stub
			menuservice.getMenu();
			return true;
		}


		//method displays orders list and uses orderservice to generate bill using order id as input
		public boolean generateBill() {
			
			System.out.println("\tOrders List");
			System.out.println("----------------------------");
			
			orderservice.displayAllOrders();
			
			System.out.println("Enter Order Id to Generate Bill:");
			sc.nextLine();
			String orderIds = sc.nextLine();
			
			Order order = orderservice.getOrderByOrderId(orderIds);
			billservice.generateBill(order);
			return true;
		}

		//method allows access for the client to update the status of order
		public boolean setOrderStatus() {
			
			sc.nextLine();
			System.out.print("Enter Order Id: ");
			String orderId = sc.nextLine();
			System.out.print("Enter new status: ");
			String status = sc.nextLine();
			
			orderservice.setStatusByOrderId(orderId, status);
			return true;
		}
	//lifecycle method stop
	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Consumer Stopped");
		
		bundleContext.ungetService(orderserviceref);
		bundleContext.ungetService(menuserviceref);
		bundleContext.ungetService(billserviceref);
	}

}
