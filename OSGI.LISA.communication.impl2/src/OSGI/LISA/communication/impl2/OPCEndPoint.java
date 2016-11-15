package OSGI.LISA.communication.impl2;

import javax.jms.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.EndPointCore.LISAEndPointCore;
import OSGI.LISA.communication.HardwareCommunication;

public class OPCEndPoint extends LISAEndPointCore {
	public OPCEndPoint() {

		Connection connection = createConnection();

		CommunicationService s = new CommunicationService(this, connection, "test", "test");
		CommunicationService2 s2 = new CommunicationService2(this, connection, "test", "test");

		services.put("OPCService", s);
		services.put("OPCService2", s2);

		getEndpointThread().start();

	}

}
