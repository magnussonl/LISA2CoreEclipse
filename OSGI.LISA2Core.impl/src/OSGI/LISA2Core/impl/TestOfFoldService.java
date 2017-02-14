package OSGI.LISA2Core.impl;

import java.util.List;

import javax.jms.Connection;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAFoldService;

public class TestOfFoldService extends LISAFoldService {

	public TestOfFoldService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub) {
		super(epIn, connection, topicInSub, topicInPub);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onStart() {
		setMaxSize(200);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void fold() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMsgToList(LISAMessage msgIn) {
		// TODO Auto-generated method stub
		
	}




}