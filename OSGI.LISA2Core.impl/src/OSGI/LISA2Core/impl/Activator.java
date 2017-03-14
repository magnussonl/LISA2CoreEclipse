package OSGI.LISA2Core.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.EndPointCore.LISAEndPointCore;


public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
		new LISAEndPoint();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
