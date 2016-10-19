package OSGI.LISA.communication.services;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.UaClient;

import OSGI.LISA.communication.HardwareCommunication;

public class OPC implements HardwareCommunication {

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataValue readValue(int lvl, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInt(int lvl, String name, int value) {
		// TODO Auto-generated method stub
		return false;
	}




}