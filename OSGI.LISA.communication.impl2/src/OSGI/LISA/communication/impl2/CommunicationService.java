package OSGI.LISA.communication.impl2;

import javax.jms.Connection;
import javax.jms.Message;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.osgi.framework.BundleContext;

import LISA.ServiceCore.LISAServiceCore;
import OSGI.LISA.communication.CommunicationServiceTracker;
import OSGI.LISA.communication.HardwareCommunication;

public class CommunicationService extends LISAServiceCore {
	
	HardwareCommunication comService;
	CommunicationServiceTracker serviceTracker;
	BundleContext context;
	
	public CommunicationService(Connection connection, String topicInSub, String topicInPub, BundleContext context) {
        super(connection, topicInSub, topicInPub);
        this.context = context;
    }

	@Override
	public void onStart() {
		serviceTracker = new CommunicationServiceTracker(context);
		serviceTracker.open();
		comService = (HardwareCommunication) serviceTracker.getService();
		comService.connect("opc.tcp://DESKTOP-OHVUEFM:53530/OPCUA/SimulationServer");
		
	}

	@Override
	public boolean action() {
		DataValue value = comService.readValue(2, "MyLevel");
		System.out.println(value);
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
