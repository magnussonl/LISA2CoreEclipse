package LISA.ServiceCore;

import javax.jms.Connection;

import org.osgi.framework.ServiceListener;
import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;

public abstract class LISAHardwareCommunicationService extends LISAServiceCore {
	
	public ServiceTracker serviceTracker;
	
	public LISAHardwareCommunicationService(LISAEndPointCore epIn, Connection connection, String topicIn, ServiceTracker stIn) {
		super(epIn, connection, topicIn);
		this.serviceTracker = stIn;
	}
	
	public LISAHardwareCommunicationService(LISAEndPointCore epIn, Connection connection, String topicInSub,
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

	public abstract void assignCommunicationService();

}
