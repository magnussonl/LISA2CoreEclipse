package osgi.lisa.sim.com.robota;

import javax.jms.Connection;
import javax.jms.Message;

import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.ServiceCore.LISAHardwareCommunicationService;
import osgi.lisa.sim.com.robota.communication.RobotAHardwareCommunication;

public class RobotAReaderService extends LISAHardwareCommunicationService {
	
	RobotAHardwareCommunication hardCom;

	public RobotAReaderService(LISAEndPointCore epIn, Connection connection, String topicIn, ServiceTracker st) {
		super(epIn, connection, topicIn, st);
		
	}

	@Override
	public void onStart() {

		
		setExecuteFrequency(1000);

	}

	@Override
	public boolean action() {
		
		Long time = System.nanoTime();
		
		System.out.println(time);
		
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

	@Override
	public void assignCommunicationService() {
		hardCom = (RobotAHardwareCommunication) serviceTracker.getService();
		
	}
	
	
	
}
