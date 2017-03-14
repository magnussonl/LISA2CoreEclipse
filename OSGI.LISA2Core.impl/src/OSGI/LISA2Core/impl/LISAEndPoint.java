package OSGI.LISA2Core.impl;

import javax.jms.Connection;

import LISA.EndPointCore.LISAEndPointCore;

public class LISAEndPoint extends LISAEndPointCore{
	public LISAEndPoint(){

		Connection connection = createConnection();
		
        LISAService1 s1 = new LISAService1(this, connection, "test", "test");
        LISAService2 s2 = new LISAService2(this, connection, "test", "test");

        services.put(s1.getServiceName(), s1);
        
        services.put(s2.getServiceName(), s2);

        getEndpointThread().start();

	}

}
