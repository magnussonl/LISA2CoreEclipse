import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.Utils.Config.Config;
import LISA.Utils.Config.ConfigFunctions;


public class Activator implements BundleActivator {
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		
		Config conf = ConfigFunctions.getConfig();
		
	}

	

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
