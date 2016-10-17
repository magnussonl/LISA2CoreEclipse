package OSGI.LISA2Core.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import LISA.Utils.Config.Config;
import LISA.Utils.Config.ConfigFunctions;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		Test1 test1 = new Test1();
		test1.method1("printing");
		Config testConf = new Config();
		testConf.setIp("12311");
		testConf.setPassword("test");
		testConf.setPort(22);
		testConf.setUsername("asdasd");
		ConfigFunctions.createConfigFile("12311", 22);
		
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
