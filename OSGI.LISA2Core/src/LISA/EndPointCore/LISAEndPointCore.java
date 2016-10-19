/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.EndPointCore;

import LISA.ServiceCore.LISAServiceCore;
import LISA.ServiceCore.LISAServiceCore.ServiceState;
import LISA.Utils.Config.Config;
import LISA.Utils.Config.ConfigFunctions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
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
public class LISAEndPointCore implements Runnable {

    private static LISAEndPointCore instance = null;
    private static String url;
    private static String username;
    private static String password;
    private static ActiveMQConnectionFactory connectionFactory = null;
    private static Connection connection = null;
    private static Config config = null;
    public static final Thread endpointThread = new Thread(new LISAEndPointCore());
    public static HashMap<String, LISAServiceCore> services = new HashMap<String, LISAServiceCore>();
    public static ListMultimap<String, String> dataMapping = ArrayListMultimap.create();

    protected LISAEndPointCore() {
        config = ConfigFunctions.getConfig();
        setUrl("tcp://" + config.getIp() + ":" + config.getPort());
        setUsername(config.getUsername());
        setPassword(config.getPassword());
    }

    public static LISAEndPointCore getInstanceCore() {
        if (instance == null) {
            synchronized (LISAEndPointCore.class) {
                instance = new LISAEndPointCore();
            }
        }
        return instance;
    }

    public static void setUrl(String url) {
        LISAEndPointCore.url = url;
    }

    public static void setUsername(String username) {
        LISAEndPointCore.username = username;
    }

    public static void setPassword(String password) {
        LISAEndPointCore.password = password;
    }
    
    
    

    public static Connection createConnection() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(username, password, url);
            connection = connectionFactory.createConnection();
            connection.start();

        } catch (JMSException ex) {
            Logger.getLogger(LISAEndPointCore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }


    public void run() {
        while (true) {
            for (Entry<String, LISAServiceCore> entry : services.entrySet()) {

                String key = entry.getKey();
                LISAServiceCore s = entry.getValue();
                ServiceState state = s.getState();

                if (state.equals(ServiceState.SETUP)) {
                    s.serviceSetup();
                    s.setDataMap(dataMapping);
                    s.setServiceID(key);
                    s.setState(ServiceState.STARTUP);
                }
                if (state.equals(ServiceState.STARTUP)) {
                    s.onStart();
                    s.setState(ServiceState.ACTION);
                }
                if (state.equals(ServiceState.ACTION)) {
                	if(System.nanoTime()-s.getExecutionTimestamp() > s.getExecuteFrequency()*1000000) {
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
