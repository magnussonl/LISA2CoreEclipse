package osgi.lisa.sim.com.robota;

import javax.jms.Connection;
import javax.jms.Message;

import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.ServiceCore.LISAServiceCore;
import osgi.lisa.sim.com.robota.communication.RobotAHardwareCommunication;
import osgi.lisa.sim.com.robota.communication.RobotAServiceTracker;

public class RobotAReaderService extends LISAServiceCore {
	
	ServiceTracker serviceTracker;
	RobotAHardwareCommunication hardCom;

	public RobotAReaderService(LISAEndPointCore epIn, Connection connection, String topicIn, ServiceTracker st) {
		super(epIn, connection, topicIn);
		this.serviceTracker = st;
	}

	@Override
	public void onStart() {
		hardCom = (RobotAHardwareCommunication) serviceTracker.getService();
		
		setExecuteFrequency(1000);
		
	}

	@Override
	public boolean action() {
		
		hardCom.readValue(1, "a");
		return false;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
