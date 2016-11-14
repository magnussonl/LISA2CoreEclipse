package osgi.lisa.sim.com.robota;

import javax.jms.Connection;

import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;

public class RobotAEndPoint extends LISAEndPointCore {
	
	public RobotAEndPoint(ServiceTracker sT) {
		
		Connection connection = createConnection();
		
		RobotAReaderService s1 = new RobotAReaderService(this, connection, "test", sT);
		
		services.put(s1.getServiceName(), s1);
		
		getEndpointThread().start();
		
		
	}
	
}
