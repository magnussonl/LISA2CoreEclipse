package osgi.lisa.sim.com.robota.communication;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import LISA.Utils.HardwareCommunication;



public class RobotAServiceTracker extends ServiceTracker {

	public RobotAServiceTracker(BundleContext context) {
		super(context, HardwareCommunication.class.getName(), null);
	}

	public Object addingService(ServiceReference reference) {
		return super.addingService(reference);
	}

	public void removedService(ServiceReference reference, Object service) {

		super.removedService(reference, service);
	}
	
}
