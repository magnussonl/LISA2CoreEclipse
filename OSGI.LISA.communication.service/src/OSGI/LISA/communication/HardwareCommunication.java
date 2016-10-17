package OSGI.LISA.communication;
public interface HardwareCommunication {
	public boolean getConnection(String url);
	public boolean readInt(int lvl, String name);
	public void setInt(int lvl, String name, int value);
}