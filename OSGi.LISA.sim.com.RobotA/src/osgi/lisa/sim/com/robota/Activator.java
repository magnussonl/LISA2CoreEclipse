package osgi.lisa.sim.com.robota;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import osgi.lisa.sim.com.robota.communication.RobotAServiceTracker;

public class Activator implements BundleActivator {

	private static BundleContext context;
	ServiceTracker serviceTrack;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		serviceTrack = new RobotAServiceTracker(bundleContext);
		serviceTrack.open();
		new RobotAEndPoint(serviceTrack);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
