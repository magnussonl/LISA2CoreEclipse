package OSGI.LISA.communication.impl;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import OSGI.LISA.communication.CommunicationServiceTracker;
import OSGI.LISA.communication.HardwareCommunication;

public class Activator implements BundleActivator {
	CommunicationServiceTracker serviceTracker;
	@Override
	public void start(BundleContext context) throws Exception {
		serviceTracker = new CommunicationServiceTracker(context);
		serviceTracker.open();
		HardwareCommunication comService = (HardwareCommunication) serviceTracker.getService();
		comService.readInt(2, "test");

		Class aClass = Class.forName("OSGI.LISA.communication.HardwareCommunication");
		Method[] ms = aClass.getMethods();
		for(Method m : ms) {
			System.out.println(m.getName());
		}
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		serviceTracker.close();
	}

}