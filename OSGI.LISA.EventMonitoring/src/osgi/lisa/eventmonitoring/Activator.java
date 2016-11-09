package osgi.lisa.eventmonitoring;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {


	public void start(BundleContext bundleContext) throws Exception {
		new LISAEventMonitoringEP();
	}

	public void stop(BundleContext bundleContext) throws Exception {

	}

}
