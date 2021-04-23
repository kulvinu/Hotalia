package orderproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class OrderProducerActivator implements BundleActivator {

	ServiceRegistration orderProducerServiceRegistration;
	
	//lifecycle method start
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Order Producer Started");
		OrderService orderService = new OrderServiceImpl();
		orderProducerServiceRegistration = bundleContext.registerService(OrderService.class.getName(), orderService, null);
		
		
	}

	//lifecycle method stop
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Order producer stopped");
		orderProducerServiceRegistration.unregister();
	}

}
