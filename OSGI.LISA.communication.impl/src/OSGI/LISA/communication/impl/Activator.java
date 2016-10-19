package OSGI.LISA.communication.impl;

import org.opcfoundation.ua.builtintypes.DataValue;
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
		
		comService.connect("opc.tcp://DESKTOP-OHVUEFM:53530/OPCUA/SimulationServer");
		DataValue value = comService.readValue(2, "MyLevel");
		System.out.println(value);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		serviceTracker.close();
	}

}