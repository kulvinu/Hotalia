package bill_producer;

import java.util.HashMap;
import orderproducer.Order;

public interface BillService {
	public void generateBill(Order order);
}
