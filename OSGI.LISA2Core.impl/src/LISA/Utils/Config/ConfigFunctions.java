/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.Utils.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.osgi.framework.BundleContext;

/**
 *
 * @author Linus
 */
public class ConfigFunctions {

    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;
    private static JAXBContext jc = null;
    private static String file = "application.conf";

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    private static void setJAXBContext() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        
            try {
				jc =  AccessController.doPrivileged(new PrivilegedExceptionAction<JAXBContext>() {

				    public JAXBContext run() throws JAXBException {

				        // needs to run here otherwise throws AccessControlException
				        return JAXBContext.newInstance(Config.class);
				    }
				});
			} catch (PrivilegedActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    private static void setMarshaller() {
        try {
            marshaller = jc.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            Logger.getLogger(ConfigFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setUnmarshaller() {
        try {
            unmarshaller = jc.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        } catch (JAXBException ex) {
            Logger.getLogger(ConfigFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Config getConfig() {
        Config retConf = new Config();
       
//            setJAXBContext();
//            setUnmarshaller();
//            
//            String configIn = "{\"config\" : { \"ip\" : \"localhost\", \"username\" : \"karaf\", \"password\" : \"karaf\", \"port\" : 61616  }}";
//            
//            //URL configURL = context.getBundle().getEntry(file);
//            //InputStream input = configURL.openStream();
//            
//            StreamSource json = new StreamSource(configIn);
//            
//            try {
//				retConf = unmarshaller.unmarshal(json, Config.class).getValue();
//			} catch (JAXBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
        retConf.setIp("localhost");
        retConf.setUsername("karaf");
        retConf.setPassword("karaf");
        retConf.setPort(61616);
            
        
        return retConf;
    }
    //</editor-fold>

    public static void createConfigFile(String ip, Integer port) {

        try {
            setJAXBContext();
            setMarshaller();
            Config configToFile = new Config();
            configToFile.setIp(ip);
            configToFile.setPort(port);

            FileWriter fw = new FileWriter(file);
            StringWriter sw = new StringWriter();

            marshaller.marshal(configToFile, sw);
            
            fw.write(sw.toString());
            fw.close();
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(ConfigFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
