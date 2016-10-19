package OSGI.LISA.communication;

import org.opcfoundation.ua.builtintypes.DataValue;

public interface HardwareCommunication {
	public boolean connect(String url);
	public DataValue readValue(int lvl, String name);
	public boolean setInt(int lvl, String name, int value);
}