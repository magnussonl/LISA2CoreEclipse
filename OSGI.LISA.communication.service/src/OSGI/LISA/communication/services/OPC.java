package OSGI.LISA.communication.services;

import OSGI.LISA.communication.HardwareCommunication;

public class OPC implements HardwareCommunication {

	@Override
	public boolean getConnection(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean readInt(int lvl, String name) {
		System.out.println(lvl + " name: " + name + " OPC");
		return false;
	}

	@Override
	public void setInt(int lvl, String name, int value) {
		// TODO Auto-generated method stub
		
	}

}