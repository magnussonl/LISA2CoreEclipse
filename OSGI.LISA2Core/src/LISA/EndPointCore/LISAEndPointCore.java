/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.EndPointCore;

import LISA.ServiceCore.LISAHardwareCommunicationService;
import LISA.ServiceCore.LISAServiceCore;
import LISA.ServiceCore.LISAServiceCore.ServiceState;
import LISA.Utils.Config.Config;
import LISA.Utils.Config.ConfigFunctions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Linus
 */
public abstract class LISAEndPointCore implements Runnable {

	private String url;
	private String username;
	private String password;
	private ActiveMQConnectionFactory connectionFactory = null;
	private Connection connection = null;
	private Config config = null;
	private Thread endpointThread;
	public HashMap<String, LISAServiceCore> services = new HashMap<String, LISAServiceCore>();
	public ListMultimap<String, String> dataMapping = ArrayListMultimap.create();
	private String ipAdress;
	private String endpointName = this.getClass().getName();
	private String endpointDescription = "";
	
	

	public LISAEndPointCore() {
		config = ConfigFunctions.getConfig();
		setUrl("tcp://" + config.getIp() + ":" + config.getPort());
		setUsername(config.getUsername());
		setPassword(config.getPassword());
		endpointThread =  new Thread(this);
		try {
			this.setIpAdress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEndpointName() {
		return endpointName;
	}

	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}

	public String getIpAdress() {
		return ipAdress;
	}


	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}


	public Thread getEndpointThread() {
		return endpointThread;
	}


	public void setEndpointThread(Thread endpointThread) {
		this.endpointThread = endpointThread;
	}
	

	public String getEndpointDescription() {
		return endpointDescription;
	}


	public void setEndpointDescription(String endpointDescription) {
		this.endpointDescription = endpointDescription;
	}


	public Connection createConnection() {
		try {
			this.connectionFactory = new ActiveMQConnectionFactory(username, password, url);
			this.connection = connectionFactory.createConnection();
			this.connection.start();

		} catch (JMSException ex) {
			Logger.getLogger(LISAEndPointCore.class.getName()).log(Level.SEVERE, null, ex);
		}
		return this.connection;
	}

	public void run() {
		while (true) {
			for (Entry<String, LISAServiceCore> entry : services.entrySet()) {

				String key = entry.getKey();
				LISAServiceCore s = entry.getValue();
				ServiceState state = s.getState();

				if (s.getClass().getSuperclass().getName().equals("LISA.ServiceCore.LISAHardwareCommunicationService") && s.isStarted()) {
					LISAHardwareCommunicationService hcs = (LISAHardwareCommunicationService) s;
					hcs.assignCommunicationService();
					if (hcs.getServiceTracker().getService() == null) {
						s.setState(state.WAITING);
						state = s.getState();
					} else {
						s.setState(state.ACTION);
						state = s.getState();
					}
				}
				
				if (state.equals(ServiceState.SETUP)) {
					s.serviceSetup();
					s.setDataMap(dataMapping);
					s.setServiceID(key);
					s.setState(ServiceState.STARTUP);
				}
				if (state.equals(ServiceState.STARTUP)) {
					s.onStart();
					s.setStarted(true);
					s.setState(ServiceState.ACTION);
				}
				if (state.equals(ServiceState.ACTION)) {
					if (System.nanoTime() - s.getExecutionTimestamp() > s.getExecuteFrequency() * 1000000) {
						if (s.action()) {
							s.setState(ServiceState.END);
						} else {
							s.setExecutionTimestamp(System.nanoTime());
						}
					}

				}
				if (state.equals(ServiceState.END)) {
					s.end();
					s.setState(ServiceState.WAITING);
				}

			}
		}
	}

	

	
}
