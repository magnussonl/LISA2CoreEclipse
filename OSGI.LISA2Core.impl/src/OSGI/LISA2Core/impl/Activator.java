package OSGI.LISA2Core.impl;

import javax.jms.Connection;


import org.osgi.framework.BundleContext;

import LISA.EndPointCore.LISAEndPointActivator;


public class Activator extends LISAEndPointActivator {

	@Override
	public void onStart(BundleContext context) {
		// TODO Auto-generated method stub
		Connection connection = createConnection();

        LISAService1 s1 = new LISAService1(connection, "test", "test");
        LISAService2 s2 = new LISAService2(connection, "test", "test");
        TestOfFoldService s3 = new TestOfFoldService(connection, "test", "test");

        services.put("s1", s1);
        services.put("s2", s2);
        services.put("FoldService", s3);

        endpointThread.start();
		
	}

	@Override
	public void onStop(BundleContext context) {
		// TODO Auto-generated method stub
		
	}



}
