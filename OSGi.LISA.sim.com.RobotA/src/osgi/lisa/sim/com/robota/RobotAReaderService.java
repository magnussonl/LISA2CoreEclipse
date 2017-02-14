package osgi.lisa.sim.com.robota;

import java.awt.List;
import java.util.LinkedList;

import javax.jms.Connection;
import javax.jms.Message;

import org.osgi.util.tracker.ServiceTracker;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.ServiceCore.LISADeviceService;
import LISA.Utils.HardwareCommunication;


public class RobotAReaderService extends LISADeviceService {
	

	LinkedList<String> myList = new LinkedList<String>();
	
	public RobotAReaderService(LISAEndPointCore epIn, Connection connection, String topicIn, ServiceTracker st) {
		super(epIn, connection, topicIn, st);
		
	}

	@Override
	public void onStart() {
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCommunicationService();
		hardwareConnection.addItemToRead("PLC1.Application.GVL.antal_puckar");

	}

	@Override
	public boolean action() {
		
		if(hardwareConnection.checkValue("PLC1.Application.GVL.antal_puckar")) {
			String v = hardwareConnection.readValue("PLC1.Application.GVL.antal_puckar");
			System.out.println(v);
			myList.add(v);
		}
		
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
	public void getCommunicationService() {
		hardwareConnection = (HardwareCommunication) serviceTracker.getService();
		
	}


	
	
	
}
