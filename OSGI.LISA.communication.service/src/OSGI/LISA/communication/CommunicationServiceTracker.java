package OSGI.LISA.communication;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;




public class CommunicationServiceTracker extends ServiceTracker {
    public CommunicationServiceTracker(BundleContext context) {
        super(context, HardwareCommunication.class.getName(),null);
    }
    public Object addingService(ServiceReference reference) {
        
        return super.addingService(reference);
    }
    public void removedService(ServiceReference reference, Object service) {
       
        super.removedService(reference, service);
    }
}