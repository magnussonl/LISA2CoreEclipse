package osgi.lisa.sim.com.robotacom1;


import org.opcfoundation.ua.builtintypes.DataValue;

import LISA.Utils.HardwareCommunication;



public class RobotAComOPCUA implements HardwareCommunication {

	@Override
	public boolean connect(String url) {
		System.out.println("connected");
		return false;
	}

	@Override
	public DataValue readValue(int lvl, String name) {
		System.out.println("reading value in OPCUA");
		return null;
	}

	@Override
	public boolean setInt(int lvl, String name, int value) {
		System.out.println("setting Int");
		return false;
	}
	
	
	
}
