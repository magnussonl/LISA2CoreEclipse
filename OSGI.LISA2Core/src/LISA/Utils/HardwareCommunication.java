package LISA.Utils;


public interface HardwareCommunication {
	
	public boolean connect(String url);
	
	public void addItemToRead(String itemId);
	
	public boolean checkValue(String itemId);

	public String readValue(String itemId);

	public boolean setInt(int lvl, String name, int value);
}
