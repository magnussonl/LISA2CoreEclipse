package osgi.lisa.sim.com.robotacom2;



import org.opcfoundation.ua.builtintypes.DataValue;

import osgi.lisa.sim.com.robota.communication.RobotAHardwareCommunication;

public class RobotAComOPC implements RobotAHardwareCommunication {

	@Override
	public boolean connect(String url) {
		System.out.println("connected");
		return false;
	}

	@Override
	public DataValue readValue(int lvl, String name) {
		System.out.println("reading value in OPC");
		return null;
	}

	@Override
	public boolean setInt(int lvl, String name, int value) {
		System.out.println("setting Int");
		return false;
	}
	
	
	
}
