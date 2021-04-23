package bill_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class BillActivator implements BundleActivator {

	ServiceRegistration billServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Bill Producer Start");
		BillService serviceBill = new BillServiceImpl();
		
		billServiceRegistration = context.registerService(BillService.class.getName(), serviceBill, null);
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Bill Producer stop");
		billServiceRegistration.unregister();
	}

}
