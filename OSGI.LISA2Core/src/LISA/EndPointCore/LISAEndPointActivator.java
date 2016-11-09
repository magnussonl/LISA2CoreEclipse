package LISA.EndPointCore;

import java.security.Timestamp;
import java.sql.Time;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;



public abstract class LISAEndPointActivator extends LISAEndPointCore implements BundleActivator {
	
	public abstract void onStart(BundleContext context);

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		onStart(context);
		System.out.println(this);
		
	}

	public abstract void onStop(BundleContext context);

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		onStop(context);
	}

}
