package OSGI.LISA2Core.impl;

import javax.jms.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.EndPointCore.LISAEndPointCore;


public class Activator extends LISAEndPointCore implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {

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
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
