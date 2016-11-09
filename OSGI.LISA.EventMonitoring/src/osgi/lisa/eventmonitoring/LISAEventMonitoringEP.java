package osgi.lisa.eventmonitoring;

import java.util.LinkedList;

import javax.jms.Connection;

import LISA.EndPointCore.LISAEndPointCore;

public class LISAEventMonitoringEP extends LISAEndPointCore {

	public static LinkedList<String> mainEvents = new LinkedList<String>();
	
	public LISAEventMonitoringEP() {
		Connection connection = createConnection();
		
		EventMonitoringService s1 = new EventMonitoringService(this, connection, "test", "test", mainEvents);
		
		services.put(s1.getServiceName(), s1);

		getEndpointThread().start();
	}
	
}
