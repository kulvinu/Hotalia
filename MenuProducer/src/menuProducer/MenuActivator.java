package menuProducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class MenuActivator implements BundleActivator {

	//private static BundleContext context;
	ServiceRegistration ProducerServiceRegistration;

//	static BundleContext getContext() {
//		return context;
//	}

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Producer Start");
		MenuService menuPublishService = new MenuProducerImpl();
		
		ProducerServiceRegistration = bundleContext.registerService(MenuService.class.getName(), menuPublishService, null);
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Producer Stop");
		ProducerServiceRegistration.unregister();
	}

}
