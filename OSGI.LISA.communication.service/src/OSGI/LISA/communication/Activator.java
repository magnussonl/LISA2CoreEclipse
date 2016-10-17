package OSGI.LISA.communication;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {
	ServiceRegistration comServiceRegistration;
    public void start(BundleContext context) throws Exception {
        CommunicationServiceFactory comServiceFactory = new CommunicationServiceFactory();
        comServiceRegistration =context.registerService(HardwareCommunication.class.getName(), comServiceFactory, null);
    }
    public void stop(BundleContext context) throws Exception {
    	comServiceRegistration.unregister();
    }
}