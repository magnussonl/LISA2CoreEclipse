package osgi.lisa.servicemonitoring;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.jms.Connection;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.KeyPairValue;

public class LISAServiceMonitoringEP extends LISAEndPointCore {
	
	public static Map<String, LinkedList<KeyPairValue>> mainServices = new HashMap<String, LinkedList<KeyPairValue>>();
	
	public LISAServiceMonitoringEP() {

		Connection connection = createConnection();

		ServiceMonitoringService s1 = new ServiceMonitoringService(this, connection, "test", "test", mainServices);
		
		services.put(s1.getServiceName(), s1);

		getEndpointThread().start();
	}
}
