/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EndPointOPCCommunication;

import LISA.EndPointCore.LISAEndPointCore;
import javax.jms.Connection;

/**
 *
 * @author Linus
 */
public class EndPointOPCUA extends LISAEndPointCore {
    
    

    public static void main(String[] args) {
        Connection connection = createConnection();
        
        OPCUAService s1 = new OPCUAService(connection, "test", "test");
        
        services.put("OPCUAService", s1);
        
        endpointThread.start();
        
        
    }
}
