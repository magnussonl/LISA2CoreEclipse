package osgi.lisa.sim.com.robotacom1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import osgi.lisa.sim.com.robota.communication.RobotAHardwareCommunication;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration reg;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		reg = context.registerService(RobotAHardwareCommunication.class.getName(), new RobotAComOPCUA(), null);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		reg.unregister();
	}

}
