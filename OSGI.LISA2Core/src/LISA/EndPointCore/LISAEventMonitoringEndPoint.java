package LISA.EndPointCore;

import java.util.HashMap;
import java.util.LinkedList;

public class LISAEventMonitoringEndPoint extends LISAEndPointCore {
	
	HashMap<String, LinkedList<String>> endpoints = new HashMap<String, LinkedList<String>>();

	public LISAEventMonitoringEndPoint() {
		setEndpointName("EventMonitoringEndPoint");
	}

	public void registerEndpoint(String name, LinkedList<String> services) {
		
		endpoints.put(name, services);
		
	}
	
	public void unregisterEndpoint(String name) {
		endpoints.remove(name);
	}
	
}
