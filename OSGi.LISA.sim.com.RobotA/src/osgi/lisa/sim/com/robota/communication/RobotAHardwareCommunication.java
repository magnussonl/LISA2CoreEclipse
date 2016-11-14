package osgi.lisa.sim.com.robota.communication;

import org.opcfoundation.ua.builtintypes.DataValue;

public interface RobotAHardwareCommunication {
	
	public boolean connect(String url);

	public DataValue readValue(int lvl, String name);

	public boolean setInt(int lvl, String name, int value);
}
