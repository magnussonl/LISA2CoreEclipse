package EndPointTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import LISA.EndPointCore.*;
import LISA.EndPointCore.LISAEndPointCore;
import javax.jms.Connection;

/**
 *
 * @author Linus
 */
public class LISAEndPoint extends LISAEndPointCore {

    public static void main(String[] args) {

        Connection connection = createConnection();

        LISAService1 s1 = new LISAService1(connection, "test", "test");
        LISAService2 s2 = new LISAService2(connection, "test", "test");

        services.put("s1", s1);
        services.put("s2", s2);

        endpointThread.start();

    }

}
