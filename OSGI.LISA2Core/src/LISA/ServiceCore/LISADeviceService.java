package LISA.ServiceCore;

import javax.jms.Connection;

import org.osgi.framework.ServiceListener;
import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;
//import osgi.lisa.sim.com.robota.communication.HardwareCommunication;
import LISA.Utils.HardwareCommunication;

public abstract class LISADeviceService extends LISAServiceCore {
	
	public HardwareCommunication hardwareConnection;
	
	public ServiceTracker serviceTracker;

	
	public LISADeviceService(LISAEndPointCore epIn, Connection connection, String topicIn, ServiceTracker stIn) {
		super(epIn, connection, topicIn);
		this.serviceTracker = stIn;
	}
	
	public LISADeviceService(LISAEndPointCore epIn, Connection connection, String topicInSub,
			String topicInPub, ServiceTracker stIn) {
		super(epIn, connection, topicInSub, topicInPub);
		this.serviceTracker = stIn;
	}
	
	public ServiceTracker getServiceTracker() {
		return serviceTracker;
	}

	public void setServiceTracker(ServiceTracker serviceTracker) {
		this.serviceTracker = serviceTracker;
	}

	public abstract void getCommunicationService();

}
