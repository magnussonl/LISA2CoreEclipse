package OSGI.LISA.communication.impl2;

import javax.jms.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.EndPointCore.LISAEndPointCore;
import OSGI.LISA.communication.HardwareCommunication;


public class OPCEndPoint extends LISAEndPointCore implements BundleActivator {
	


	@Override
	public void start(BundleContext context) throws Exception {
		
		Connection connection = createConnection();
		
		CommunicationService s = new CommunicationService(connection, "test","test", context);
		
		services.put("OPCService", s);
		
		endpointThread.start();
		
		
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
