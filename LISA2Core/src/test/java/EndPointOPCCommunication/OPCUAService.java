/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EndPointOPCCommunication;

import LISA.ServiceCore.LISAServiceCore;
import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Message;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.transport.security.SecurityMode;

/**
 *
 * @author Linus
 */
public class OPCUAService extends LISAServiceCore {

    UaClient client;

    public OPCUAService(Connection connection, String topicInSub, String topicInPub) {
        super(connection, topicInSub, topicInPub);
    }


    public void onMessage(Message message) {

    }


    public void onStart() {
        try {
            client = new UaClient("opc.tcp://192.168.1.145:26543/NodeOPCUA-Server");
            client.setSecurityMode(SecurityMode.NONE);
            initialize(client);
            client.connect();

        } catch (URISyntaxException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecureIdentityException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean action() {
        try {
            Double d = new Double(3);
            NodeId nodeid = new NodeId(1, "FanSpeed");
            DataValue value = client.readValue(nodeid, d);
            System.out.println(value);
            
            return false;
        } catch (ServiceException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatusException ex) {
            Logger.getLogger(OPCUAService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }


    public void end() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Define a minimal ApplicationIdentity. If you use secure connections, you
     * will also need to define the application instance certificate and manage
     * server certificates. See the SampleConsoleClient.initialize() for a full
     * example of that.
     */
    protected static void initialize(UaClient client)
            throws SecureIdentityException, IOException, UnknownHostException {
        // *** Application Description is sent to the server
        ApplicationDescription appDescription = new ApplicationDescription();
        appDescription.setApplicationName(new LocalizedText("SimpleClient", Locale.ENGLISH));
        // 'localhost' (all lower case) in the URI is converted to the actual
        // host name of the computer in which the application is run
        appDescription.setApplicationUri("urn:localhost:UA:SimpleClient");
        appDescription.setProductUri("urn:prosysopc.com:UA:SimpleClient");
        appDescription.setApplicationType(ApplicationType.Client);

        final ApplicationIdentity identity = new ApplicationIdentity();
        identity.setApplicationDescription(appDescription);
        client.setApplicationIdentity(identity);
    }

}
