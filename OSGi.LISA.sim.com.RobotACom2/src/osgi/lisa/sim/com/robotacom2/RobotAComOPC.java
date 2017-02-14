package osgi.lisa.sim.com.robotacom2;




import LISA.Utils.HardwareCommunication;
import OPCAccess.OPCAccess;

public class RobotAComOPC implements HardwareCommunication {

	private OPCAccess opcAccess;
	
	public RobotAComOPC() {
		opcAccess = new OPCAccess("localhost", "DESKTOP-OHVUEFM", "linusmagnusson@live.se", "Volvo-08", "137BB965-84BB-11D5-9FF1-00105A4AB1C6");
		
	}

	@Override
	public boolean connect(String url) {
		
		return false;
	}
	
	

	@Override
	public String readValue(String itemId) {
		return opcAccess.getItemValue(itemId);
	}

	@Override
	public boolean setInt(int lvl, String name, int value) {
		System.out.println("setting Int");
		return false;
	}

	@Override
	public void addItemToRead(String itemId) {
		opcAccess.addItemAccessBase(itemId, 100);
		opcAccess.startReading(itemId);
		
	}

	@Override
	public boolean checkValue(String itemId) {
		// TODO Auto-generated method stub
		return opcAccess.checkNewValue(itemId);
	}
	
	
	
}
