package OSGI.LISA.communication;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import OSGI.LISA.communication.services.OPC;
import OSGI.LISA.communication.services.OPCUA;



public class CommunicationServiceFactory implements ServiceFactory {

	@Override
	public Object getService(Bundle bundle, ServiceRegistration arg1) {
		HardwareCommunication comService;
		
		System.out.println(bundle.getSymbolicName());
		if (bundle.getSymbolicName().equals("OSGI.LISA.communication.impl")) {
			comService = new OPCUA();
		} else if(bundle.getSymbolicName().equals("OSGI.LISA.communication.impl2")) {
			comService = new OPCUA();
		} else {
			comService = null;
		}
		

		return comService;
	}

	@Override
	public void ungetService(Bundle arg0, ServiceRegistration arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

}